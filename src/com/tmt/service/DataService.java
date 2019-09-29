package com.tmt.service;

import java.io.File;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.dbmanager.connection.setting.ConnectionSettings;
import com.dbmanager.property.util.PropertyReader;
import com.tmt.constants.Resources;
import com.tmt.model.TranslationEntity;

public class DataService {

	private static final Logger LOG = LoggerFactory.getLogger(DataService.class);
	private PropertyReader propertyReader;
	private AbstractConnectionSettings connectionSettings;

	public DataService() {
		propertyReader = new PropertyReader(new File(Resources.PSQL_PROPERTIES));
		try {
			connectionSettings = new ConnectionSettings(propertyReader.propertiesReader());
		} catch (Exception e) {
			System.out.println(" Exception: " + e);
		}
	}

	public void insert(TranslationEntity translationEntity) throws ClassNotFoundException, SQLException {
		LOG.debug("insert {}", translationEntity);
		connectionSettings.build();

		connectionSettings.closeConnection();
	}

	public void update(long id, TranslationEntity translationEntity) {

	}

	public void delete(long id) {

	}
}
