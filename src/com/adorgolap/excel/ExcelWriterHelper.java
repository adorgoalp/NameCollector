package com.adorgolap.excel;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.WritableFont.FontName;
import jxl.write.biff.RowsExceededException;

public class ExcelWriterHelper {
	private WritableFont font = new WritableFont(WritableFont.ARIAL);
	private WritableCellFormat cellFormat;
	private WorkbookSettings wbSettings;
	private WritableWorkbook workbook;
	private WritableSheet excelSheet;
	private File file;
	private CellView cv;

	public void setOutputFile(String path) {
		file = new File(path);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addCaption(int row, int column, String caption) {
		Label label = new Label(column, row, caption, cellFormat);
		try {
			excelSheet.addCell(label);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean openFile() {
		String path = "./Hom/h.txt";
		File f = new File(path);
		// (works for both Windows and Linux)
		if (f.getParentFile().mkdirs()) {
			try {
				System.out.println(f.createNewFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public void setSettings() {
		wbSettings = new WorkbookSettings();
		wbSettings.setLocale(new Locale("en", "EN"));
		try {
			workbook = Workbook.createWorkbook(file, wbSettings);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workbook.createSheet("Report", 0);
		excelSheet = workbook.getSheet(0);
	}

	public void setFont() {
		font = new WritableFont(WritableFont.TIMES, 14, WritableFont.BOLD);

	}

	public void setFont(FontName f, int size) {
		font = new WritableFont(f, size);

	}

	public void setCellFormat() {
		cellFormat = new WritableCellFormat(font);
		try {
			cellFormat.setWrap(false);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		cv = new CellView();
		cv.setFormat(cellFormat);
		cv.setAutosize(true);

	}

	public void write() {
		try {
			workbook.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addStringToCell(int row, int column, String value) {
		Label label = new Label(column, row, value, cellFormat);
		try {
			excelSheet.addCell(label);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void finish() {
		try {
			workbook.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean deleteFile() {
		return file.delete();
	}

	public int getRows() {
		// TODO Auto-generated method stub
		return excelSheet.getRows();
	}

}