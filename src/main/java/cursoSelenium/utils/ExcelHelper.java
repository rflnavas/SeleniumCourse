package cursoSelenium.utils;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * The Class ExcelHelper.
 */
public class ExcelHelper {

	
	private ExcelHelper() {
	}
	
	/**
	 * Gets the cell data.
	 *
	 * @param cell the cell
	 * @return the cell data
	 */
	public static Object getCellData(Cell cell) {
		if (cell == null) {
			return null;
		}
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				return dateFormat.format(cell.getDateCellValue());
			} else {
				return cell.getNumericCellValue();
			}
		case XSSFCell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case XSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case XSSFCell.CELL_TYPE_BLANK:

		default:
			return null;
		}
	}

	/**
	 * Gets the cell data to string.
	 *
	 * @param cell the cell
	 * @return the cell data to string
	 */
	public static String getCellDataToString(final Cell cell) {
		Object object = getCellData(cell);
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	/**
	 * Array to map.
	 *
	 * @param data the data
	 * @return the map
	 */
	public static Map<String, Object> arrayToMap(Object[][] data) {
		Map<String, Object> map = new LinkedHashMap<>();
		for (int i = 0; i < data.length; i++) {
			map.put(data[i][0].toString(), data[i][1]);
		}
		return map;
	}

	/**
	 * Sets the data cell.
	 *
	 * @param sheet the sheet
	 * @param value the value
	 * @param rownum the rownum
	 * @param colnum the colnum
	 */
	public static void setDataCell(final XSSFSheet sheet, final String value,
			final int rownum, final int colnum) {
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(colnum);
		if (cell == null) {
			cell = row.createCell(colnum);
			cell.setCellValue(value);
		} else {
			cell.setCellValue(value);
		}
		cell.setCellValue(value);

		try (FileOutputStream fileOut = new FileOutputStream(
				Constants.XLS_DATA_PATH_2.toString())) {
			sheet.getWorkbook().write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the test data.
	 *
	 * @param tableName the table name
	 * @param sheet the sheet
	 * @return the test data
	 */
	public static String[][] getTestData(final String tableName, final XSSFSheet sheet) {
		String[][] testData = null;

		try {
			// Handle numbers and strings
			// DataFormatter formatter = new DataFormatter();
			XSSFCell[] boundaryCells = findCells(tableName, sheet);
			XSSFCell startCell = boundaryCells[0];

			XSSFCell endCell = boundaryCells[1];

			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					// testData[i-startRow][j-startCol] =
					// ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					Cell cell = sheet.getRow(i).getCell(j);
					testData[i - startRow][j - startCol] = getCellDataToString(cell);
					// testData[i - startRow][j - startCol] =
					// formatter.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	/**
	 * Find cells.
	 *
	 * @param tableName the table name
	 * @param sheet the sheet
	 * @return the XSSF cell[]
	 */
	public static XSSFCell[] findCells(final String tableName, final XSSFSheet sheet) {
		DataFormatter formatter = new DataFormatter();
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];

		for (Row row : sheet) {
			for (Cell cell : row) {
				// if (tableName.equals(cell.getStringCellValue())) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (XSSFCell) cell;
					}
				}
			}
		}
		return cells;
	}
}
