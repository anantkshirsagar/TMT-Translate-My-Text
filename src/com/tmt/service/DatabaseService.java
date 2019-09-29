package com.tmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbmanager.util.SqlFileRunner;
import com.tmt.constants.Resources;
import com.tmt.manager.ConfigurationReader;
import com.tmt.model.TranslationEntity;

public class DatabaseService {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

	public DatabaseService() {
	}

	public void createDatabase() {
		ConfigurationReader.getConfigurationProperties(Resources.TMT_CFG);
		SqlFileRunner sqlRunner = new SqlFileRunner(Resources. , Resources.DATABASE_SQL_FILE);
	}
}
