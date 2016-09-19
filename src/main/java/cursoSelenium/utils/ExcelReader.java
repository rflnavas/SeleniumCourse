package cursoSelenium.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * The Class ExcelReader.
 */
public class ExcelReader {
	
	/** The workbook. */
	private XSSFWorkbook workbook;
	
	/** The Constant BEGIN_LABEL. */
	private static final String BEGIN_LABEL = "##";
	
	/** The Constant END_LABEL. */
	private static final String END_LABEL = "##end-";
	
	/**
	 * Instantiates a new excel reader.
	 *
	 * @param path the path
	 */
	public ExcelReader(String path) {
		try {
			workbook = new XSSFWorkbook(new FileInputStream(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Gets the sheet.
	 *
	 * @param sheet the sheet
	 * @return the sheet
	 */
	public final XSSFSheet getSheet(String sheet){
		return workbook.getSheet(sheet);
	}

	/**
	 * Gets the workbook.
	 *
	 * @return the workbook
	 */
	public final XSSFWorkbook getWorkbook() {
		return workbook;
	}

	/**
	 * Gets the data set.
	 *
	 * @param sheetName the sheet name
	 * @param rowFrom the row from
	 * @param rowTo the row to
	 * @return the data set
	 */
	public final Object[][] getDataSet(final String sheetName, int rowFrom, int rowTo) {

		rowFrom = rowFrom < 0 ? 0 : rowFrom;
		rowTo = rowTo > Integer.MAX_VALUE - 1 ? 0 : rowTo;
		int numberRows = rowTo - rowFrom;
		Object[][] data = new Object[numberRows][];
		for (int i = rowFrom; i < rowTo; i++) {
			data[i - rowFrom] = getDataColsFromRow(sheetName, i);
		}
		return data;
	}
	
	/**
	 * Gets the data from label.
	 *
	 * @param sheetName the sheet name
	 * @param label the label
	 * @return the data from label
	 */
	public final Object[][] getDataFromLabel(final String sheetName, final String label){
		
		int [] indexes = this.indexesOf(sheetName, BEGIN_LABEL + label);
		int [] indexesEnd = this.indexesOf(sheetName, END_LABEL + label, indexes[0] + 1);
		final int ROWS_LIMIT = indexesEnd[0] - indexes[0] - 1;
		Object[][] data = new Object[ROWS_LIMIT][];
		int rowIndex = indexes[0];
		int colIndex = indexes[1];
		XSSFSheet sheet = this.getSheet(sheetName);
		
		for (int i = rowIndex + 1; i <= ROWS_LIMIT ; i++) {
			XSSFRow row = sheet.getRow(i);
			final int COLS_LIMIT = row.getLastCellNum() - colIndex;
			Object[] dataRow = new Object[COLS_LIMIT];
			for (int j = colIndex; j <= COLS_LIMIT; j++) {
				dataRow[j - colIndex] = ExcelHelper.getCellData(row.getCell(j));
			}
			data[i - rowIndex - 1] = dataRow;
		}
		return data;
	}
	
	/**
	 * Indexes of.
	 *
	 * @param sheetName the sheet name
	 * @param value the value
	 * @return the int[]
	 */
	public final int[] indexesOf(String sheetName, Object value) {
		return indexesOf(sheetName, value, 0);
	}
	
	/**
	 * Indexes of.
	 *
	 * @param sheetName the sheet name
	 * @param value the value
	 * @param rowOffSet the row off set
	 * @return the int[]
	 */
	public final int[] indexesOf(String sheetName, Object value, int rowOffSet){
		XSSFSheet sheet = this.getSheet(sheetName);
		final int LIMIT_ROWS =sheet.getPhysicalNumberOfRows();
		boolean found = false;
		int row = -1;
		int col = -1;
		for (int i = rowOffSet; i < LIMIT_ROWS && !found; i++) {
			col = indexOfInRow(sheet.getRow(i), value);
			if (col != -1) {
				row = i;
				found = true;
			}
		}
		return new int[]{row, col};
	}
	
	/**
	 * Index of in row.
	 *
	 * @param row the row
	 * @param value the value
	 * @return the int
	 */
	public static int indexOfInRow(final XSSFRow row, final Object value){
		if(row != null){
			final int COL_LENGTH = row.getPhysicalNumberOfCells();
			for (int i = 0; i < COL_LENGTH; i++) {
				Object cellData = ExcelHelper.getCellData(row.getCell(i));
				if (value.equals(cellData)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Gets the data cols from row.
	 *
	 * @param sheetName the sheet name
	 * @param rowIndex the row index
	 * @return the data cols from row
	 */
	public final Object [] getDataColsFromRow(final String sheetName, final int rowIndex){
		return getDataColsFromRow(sheetName, rowIndex,  Short.MIN_VALUE,  Short.MAX_VALUE);
	}
	
	/**
	 * Gets the data cols from row.
	 *
	 * @param sheetName the sheet name
	 * @param rowIndex the row index
	 * @param colFrom the col from
	 * @param colTo the col to
	 * @return the data cols from row
	 */
	public final Object [] getDataColsFromRow(final String sheetName, final Integer rowIndex, short colFrom, short colTo){
		
		if (colFrom > colTo) {
			throw new IllegalArgumentException("Invalid interval. colFrom:"
					+ colFrom + "colTo:" + colTo);
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) { // If row contains no data then an empty-sized array
							// will be returned.
			final String[] EMPTY_STRING_ARRAY = new String[] {};
			System.out.println(Arrays.toString(EMPTY_STRING_ARRAY));
			return EMPTY_STRING_ARRAY;
		}
		// gives you the index of last column
		Short cells = row.getLastCellNum();
		Object[] rowData = new Object[cells];

		if ((colFrom != Short.MIN_VALUE && colFrom < cells)
				&& colTo == Short.MAX_VALUE) {
			for (int i = colFrom; i < rowData.length; i++) {
				rowData[i] = ExcelHelper.getCellData(row.getCell(i));
			}
		} else if (colFrom == Short.MIN_VALUE
				&& (colTo != Short.MAX_VALUE && colTo < cells)) {
			for (int i = 0; i < colTo; i++) {
				rowData[i] = ExcelHelper.getCellData(row.getCell(i));
			}
		} else if (colFrom != Short.MIN_VALUE && colTo != Short.MAX_VALUE) {
			colTo = colTo > cells ? cells : colTo;
			for (int i = colFrom; i < colTo; i++) {
				rowData[i - colFrom] = ExcelHelper.getCellData(row.getCell(i));
			}
		} else {
			for (int i = 0; i < cells; i++) {
				rowData[i] = ExcelHelper.getCellData(row.getCell(i));
			}
		}
		System.out.println(Arrays.toString(rowData));
		return rowData;
	}
	
}
