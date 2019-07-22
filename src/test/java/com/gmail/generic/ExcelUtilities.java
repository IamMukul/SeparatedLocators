package com.gmail.generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtilities {
	public static List<ElementInfo> readElementsDataFromExcel(String filePath, String sheetName) {
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		List<ElementInfo> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int rowNum = 1;
			while (rowNum <= sheet.getLastRowNum()) {
				ElementInfo elementInfo = new ElementInfo();
				Row row = sheet.getRow(rowNum);
				Cell cell;
				cell = row.getCell(0);
				elementInfo.elementName = cell.getStringCellValue();
				cell = row.getCell(1);
				elementInfo.locatorType = cell.getStringCellValue();
				cell = row.getCell(2);
				elementInfo.locator = cell.getStringCellValue();
				rowNum++;
				list.add(elementInfo);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
