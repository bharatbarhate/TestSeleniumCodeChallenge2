package ExcelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;


public class ExcelReader {

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// Specify the path of file
			File src = new File("./src/test/resources/Data/TestExcel.xlsx");

			// load file
			FileInputStream fis = new FileInputStream(src);

			// Load workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			// Load sheet- Here we are loading first sheetonly
			XSSFSheet sh1 = wb.getSheetAt(0);

			// getRow() specify which row we want to read.

			// and getCell() specify which column to read.
			// getStringCellValue() specify that we are reading String data.

			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());

			System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());

			System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());

			System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());

			System.out.println(sh1.getRow(2).getCell(0).getStringCellValue());

			System.out.println(sh1.getRow(2).getCell(1).getStringCellValue());

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

	}
*/
	
	  public static final String currentDir = System.getProperty("user.dir");
	  
	    //Location of Test data excel file
	    public static String testDataExcelPath = null;
	    
	    public static String testDataExcelFileName = null;
	    //Excel WorkBook
	    private static XSSFWorkbook excelWBook;
	 
	    //Excel Sheet
	    private static XSSFSheet excelWSheet;
	 
	    //Excel cell
	    private static XSSFCell cell;
	 
	    //Excel row
	    private static XSSFRow row;
	 
	    //Row Number
	    public static int rowNumber;
	 
	    //Column Number
	    public static int columnNumber;
	 
	    //Setter and Getters of row and columns
	    public static void setRowNumber(int pRowNumber) {
	        rowNumber = pRowNumber;
	    }
	 
	    public static int getRowNumber() {
	        return rowNumber;
	    }
	 
	    public static void setColumnNumber(int pColumnNumber) {
	        columnNumber = pColumnNumber;
	    }
	 
	    public static int getColumnNumber() {
	        return columnNumber;
	    }
	 
	    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
	    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
	    public static void setExcelFileSheet(String sheetName) throws Exception {
	        //MAC or Windows Selection for excel path
	        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
	            testDataExcelPath = currentDir + "//src//test//java//resources//data//";
	        } else if (Platform.getCurrent().toString().contains("WIN")) {
	            testDataExcelPath = currentDir + "\\src\\test\\resources\\Data\\";
	        }
	        try {
	            // Open the Excel file
	            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
	            excelWBook = new XSSFWorkbook(ExcelFile);
	            excelWSheet = excelWBook.getSheet(sheetName);
	        } catch (Exception e) {
	            try {
	                throw (e);
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	 
	    //This method reads the test data from the Excel cell.
	    //We are passing row number and column number as parameters.
	    public static String getCellData(int RowNum, int ColNum) {
	        try {
	            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
	            DataFormatter formatter = new DataFormatter();
	            String cellData = formatter.formatCellValue(cell);
	            return cellData;
	        } catch (Exception e) {
	            throw (e);
	        }
	    }
	 
	    //This method takes row number as a parameter and returns the data of given row number.
	    public static XSSFRow getRowData(int RowNum) {
	        try {
	            row = excelWSheet.getRow(RowNum);
	            return row;
	        } catch (Exception e) {
	            throw (e);
	        }
	    }
	 
	    //This method gets excel file, row and column number and set a value to the that cell.
	    public static void setCellData(String value, int RowNum, int ColNum) {
	        try {
	            row = excelWSheet.getRow(RowNum);
	            cell = row.getCell(ColNum);
	            if (cell == null) {
	                cell = row.createCell(ColNum);
	                cell.setCellValue(value);
	            } else {
	                cell.setCellValue(value);
	            }
	            // Constant variables Test Data path and Test Data file name
	            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + "TestExcel.xlsx");
	            excelWBook.write(fileOut);
	            fileOut.flush();
	            fileOut.close();
	        } catch (Exception e) {
	            try {
	                throw (e);
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }	
	
}
