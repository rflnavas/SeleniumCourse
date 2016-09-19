package cursoSelenium.data;

import java.nio.file.Paths;
import java.util.Map;

import cursoSelenium.utils.ExcelHelper;
import cursoSelenium.utils.ExcelReader;

public class ReadXls {

	public static void main(String[] args) {
		
//		XSSFWorkbook excelWBook;
//		XSSFSheet excelSheet;
//		XSSFCell cell;
//		String path = "D:\\MisProgramas\\Desarrollos\\SeleniumCourse\\cursoSelenium\\src\\main\\resources\\data.xlsx";
		String sheetName = "Hoja1";
		try {
//			excelWBook = new XSSFWorkbook(new FileInputStream(path));
//			excelSheet = excelWBook.getSheet(sheetName);
//			cell = excelSheet.getRow(0).getCell(0);
//			String cellData = cell.getStringCellValue();
//			
//			System.out.println(cellData);
			
			ExcelReader xlr = new ExcelReader(Paths.get("D:\\", "MisProgramas", "Desarrollos", "SeleniumCourse", 
					"cursoSelenium", "src", "main", "resources", "data.xlsx").toString());
			
			xlr.getDataSet(sheetName, 1, 4);
			
			Map<String, Object> data = ExcelHelper.arrayToMap(xlr.getDataFromLabel(sheetName, "searchpage"));
			
			System.out.println(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
