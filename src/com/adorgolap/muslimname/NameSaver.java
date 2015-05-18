package com.adorgolap.muslimname;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.adorgolap.excel.ExcelWriter;

public class NameSaver implements Runnable {
	public static boolean IS_DONE = false;
	private String url = "http://www.muslimnames.info/baby-boys/islamic-boys-names-2/";
	private final String coreUrl = "http://www.muslimnames.info/baby-boys/islamic-boys-names-";

	private int pageCount = 1;
	private Document doc = null;
	private boolean isAvialable = true;
	private ExcelWriter excelWriter;
	private int excelRow = 0;

	private boolean isTimeOut = false;

	public NameSaver() throws IOException {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		Main.jbStart.setEnabled(false);
		Main.jlError.setText("Fetching page " + pageCount);
		Main.jlCommand.setText("Started. Please be patient");
		prepareExcelWrite();
		int timeout = 1;
		while (isAvialable) {
			makeUrl();
			System.out.println("Page :" + pageCount);
			getInfo();
			if (isTimeOut) {
				Main.jlError.setText("Timeout "+timeout+" times while fetching page "
						+ pageCount);
				timeout++;
				Main.jlError2.setText(" Trying again");
				isTimeOut = false;
			} else {
				timeout = 1;
				Main.jpBar.setValue(pageCount);
				Main.jlStatus.setText(" " + pageCount
						+ " out of 160 pages is fetched");
				pageCount++;
				Main.jlError.setText("Fetching page " + pageCount);
				Main.jlError2.setText("");
			}
			if (pageCount == 161) {
				Main.jlError.setText("Finished!");
				isAvialable = false;
			}
		}
		excelWriter.addName(excelRow, "eof");
		excelRow++;
		excelWriter.save();
		Main.jbStart.setEnabled(true);
		Main.jbStart.setText("Exit");
		Main.ex = true;
	}

	private void prepareExcelWrite() {
		excelWriter = new ExcelWriter();
		excelWriter.setOutputFile("names.xls");
		excelWriter.configure(ExcelWriter.CONFIG_FOR_NAME_CONTENT);
	}

	private void getInfo() {
		try {
			Response response = Jsoup
					.connect(url)
					.ignoreContentType(true)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0")
					.referrer("http://www.google.com").timeout(10 * 1000)
					.followRedirects(true).execute();
			doc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
			isTimeOut = true;
			return;
		}
		Elements links = doc
				.select("a[href~=http://www.muslimnames.info/name//*]");
		if (!links.isEmpty()) {
			for (Element link : links) {
				String details = link.text();
				details.trim();
				// System.out.println(details);
				parseAndPrint(details);
			}
		} else {
			isAvialable = false;
		}
	}

	private void makeUrl() {
		url = coreUrl + pageCount + "/";
		System.out.println("url: " + url);
	}

	private void parseAndPrint(String details) {
		System.out.println(details);
		excelWriter.addName(excelRow, details);
		excelRow++;
	}
}
