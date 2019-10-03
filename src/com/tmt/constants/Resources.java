package com.tmt.constants;

import java.io.File;

public final class Resources {

	private Resources() {
	}
	public static final String APP_NAME = "TMT";
	public static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	public static final String RESOURCE = CURRENT_WORKING_DIR + File.separator + "resources";
	public static final String ICONS = RESOURCE + File.separator + "icons";
	public static final String EXCHANGE_ICON_IMG = "icons/exchange-icon.png";
	public static final String DOWNLOAD_DATE_FORMAT = "ddMMyyyyHHmmSS";
	public static final String LINE_SEPARATOR = System.lineSeparator();

	// Extensions
	public static final String DOCX = "docx";
	public static final String TXT = "txt";
	public static final String[] EXTENSION = { DOCX, TXT };

	// Path settings
	public static final String CREATE_MYSQL_TABLES = "resources" + File.separator + "database" + File.separator
			+ "tmt_mysql_tables.sql";
	
	public static final String CREATE_PSQL_TABLES = "resources" + File.separator + "database" + File.separator
			+ "tmt_psql_tables.sql";
	public static final String TMT_CFG = "tmt-configurations.properties";
}
