package com.tmt.constants;

import java.io.File;

public interface Resources {
	String APP_NAME = "TMT";
	String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	String RESOURCES = CURRENT_WORKING_DIR + File.separator + "resources";
	String ICONS = RESOURCES + File.separator + "icons";

	String EXCHANGE_ICON_IMG = "icons/exchange-icon.png";

	String DOWNLOAD_DATE_FORMAT = "ddMMyyyyHHmmSS";

	String LINE_SEPARATOR = System.lineSeparator();

	// Extensions
	String DOCX = "docx";

	String TXT = "txt";

	String[] EXTENSIONS = { DOCX, TXT };
}
