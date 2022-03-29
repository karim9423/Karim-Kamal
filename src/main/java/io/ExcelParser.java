package io;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelParser {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    public static void setExcelFile(String excelFilePath, String sheetName) throws IOException {
	// Create an object of File class to open xlsx file
	File file = new File(excelFilePath);
	try (FileInputStream inputStream = new FileInputStream(file)) {
	    // creating workbook instance that refers to .xlsx file
	    workbook = new XSSFWorkbook(inputStream);
	    // creating a Sheet object
	    sheet = workbook.getSheet(sheetName);
	}
    }

    public static String getCellData(int rowNumber, int cellNumber) {
	// getting the cell value from rowNumber and cell Number
 if (sheet.getRow(rowNumber).getCell(cellNumber).getCellType()== CellType.NUMERIC) {
     return String.valueOf((int)sheet.getRow(rowNumber).getCell(cellNumber).getNumericCellValue());
 }
	return sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();

    }

    public static int getRowCountInSheet() {
	return sheet.getLastRowNum() - sheet.getFirstRowNum();

    }

    public void setCellValue(int rowNum, int cellNum, String cellValue, String excelFilePath) throws IOException {
	// creating a new cell in row and setting value to it
	sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);
	FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	workbook.write(outputStream);
    }
}
