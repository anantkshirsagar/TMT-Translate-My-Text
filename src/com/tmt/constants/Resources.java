package com.tmt.constants;

import java.io.File;

public interface Resources {
	String APP_NAME = "TMT";
	String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	String RESOURCE = CURRENT_WORKING_DIR + File.separator + "resources";
	String ICONS = RESOURCE + File.separator + "icons";
	String EXCHANGE_ICON_IMG = "icons/exchange-icon.png";
	String DOWNLOAD_DATE_FORMAT = "ddMMyyyyHHmmSS";
	String LINE_SEPARATOR = System.lineSeparator();

	// Extensions
	String DOCX = "docx";
	String TXT = "txt";
	String[] EXTENSION = { DOCX, TXT };

	// Path settings
	String DATABASE_SQL_FILE = "database" + File.separator + "translate_my_text.sql";
	String TMT_CFG = "tmt-configurations.properties";
}
