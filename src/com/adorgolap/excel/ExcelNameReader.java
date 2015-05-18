package com.adorgolap.excel;

import java.io.File;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelNameReader {
	File inputWorkbook;
	Workbook w;
	Sheet sheet;
	int maxRow = 0;
	public ExcelNameReader() {
		inputWorkbook = new File("testname.xls");
		try {
			w = Workbook.getWorkbook(inputWorkbook);
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = w.getSheet(0);
		maxRow = sheet.getRows();
	}

	public String getName(int row) {
		if (row >= maxRow) {
			return null;
		}
		return sheet.getCell(0, row).getContents();
	}

}
