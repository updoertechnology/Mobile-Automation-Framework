package com.mobile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static List<Hashtable<String, String>> readFile(String filePath, String sheetname, String primarykey) throws Exception{
		List<Hashtable<String, String>> datalist = new ArrayList<Hashtable<String,String>>();
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetname);
		List<Integer> rowNum = new ArrayList<>();
		int rowcount = sheet.getLastRowNum();
		for(int i = 0; i<rowcount; i++){
			if(sheet.getRow(i).getCell(0).getStringCellValue().trim().toLowerCase().contains(primarykey.toLowerCase())){
				rowNum.add(i);
			}
		}
		Iterator<Integer> rowIterator = rowNum.iterator();
		
		while(rowIterator.hasNext()){
			int loadrow = rowIterator.next();
			Hashtable<String, String> codes = new Hashtable<String, String>();
			for(int j = 0; j<sheet.getRow(0).getLastCellNum(); j++){
				String strcolname = sheet.getRow(0).getCell(j).getStringCellValue().trim();
				String strvalue = sheet.getRow(loadrow).getCell(j).getStringCellValue().trim();
				codes.put(strcolname, strvalue);
			}
			datalist.add(codes);
		}
		
		return datalist;
		
	}
}
