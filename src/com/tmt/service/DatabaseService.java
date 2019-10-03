package com.tmt.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbmanager.util.SqlFileRunner;
import com.tmt.constants.Resources;
import com.tmt.manager.ConfigurationReader;

public class DatabaseService {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

	public void createTables() throws IOException, ClassNotFoundException, SQLException {
		LOG.debug("Loading tables...");
		Map<String, String> configurationMap = ConfigurationReader.getConfigurationProperties(Resources.TMT_CFG);
		String propertiesPath = ConfigurationReader.getDBPropertiesPath(configurationMap);
		SqlFileRunner sqlRunner = new SqlFileRunner(propertiesPath, Resources.CREATE_MYSQL_TABLES);
		sqlRunner.runScript();
		LOG.debug("Tables created successfully");
	}
}
