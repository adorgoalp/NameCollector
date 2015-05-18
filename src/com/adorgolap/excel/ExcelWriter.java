package com.adorgolap.excel;

import jxl.write.WritableFont;

public class ExcelWriter{
	public static final int CONFIG_CAPTION = 1;
	public static final int CONFIG_CONTENT = 2;
	public static final int CONFIG_FOR_NAME_CONTENT = 3;
	ExcelWriterHelper excelWriterHelper;
	public ExcelWriter() {
		excelWriterHelper = new ExcelWriterHelper();
	}
	public int te()
	{
		return 1;
	}
	public void setOutputFile(String outFilePath)
	{
		excelWriterHelper.setOutputFile(outFilePath);
	}
	public void configure(int ConfigType) {
		switch (ConfigType) {
		case CONFIG_CAPTION:
			excelWriterHelper.setSettings();
			excelWriterHelper.setFont();
			excelWriterHelper.setCellFormat();
			excelWriterHelper.addCaption(0, 0, "Name");
			excelWriterHelper.addCaption(0, 1, "Phone");
			excelWriterHelper.addCaption(0, 2, "Address");
			//excelWriterHelper.write();
			break;
		case CONFIG_CONTENT:
			excelWriterHelper.setFont(WritableFont.TIMES,12);
			excelWriterHelper.setCellFormat();
			break;
		case CONFIG_FOR_NAME_CONTENT:
			excelWriterHelper.setSettings();
			excelWriterHelper.setFont(WritableFont.TIMES,12);
			excelWriterHelper.setCellFormat();
			break;
		default:
			break;
		}
	}
	public void addName(int excelRow, String details)
	{
		excelWriterHelper.addStringToCell(excelRow, 0, details);
	}
	public void addInfo(String name, String phnNo, String address)
	{
		int row = excelWriterHelper.getRows();
		excelWriterHelper.addStringToCell(row,0,name);
		excelWriterHelper.addStringToCell(row,1,phnNo);
		excelWriterHelper.addStringToCell(row,2,address);
		//excelWriterHelper.write();
	}

	public void save() {
		excelWriterHelper.write();
		excelWriterHelper.finish();
	}
	public boolean  deleteOutputFile()
	{
		return excelWriterHelper.deleteFile();
	}
}
