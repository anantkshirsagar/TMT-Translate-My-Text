package com.tmt.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dbmanager.connection.setting.AbstractConnectionSettings;
import com.dbmanager.connection.setting.ConnectionSettings;
import com.dbmanager.property.util.PropertyReader;
import com.tmt.model.TranslationEntity;
import com.tmt.util.Utility;

public class DataService {

	private static final Logger LOG = LoggerFactory.getLogger(DataService.class);
	private PropertyReader propertyReader;
	private AbstractConnectionSettings connectionSettings;

	public DataService() {
		propertyReader = new PropertyReader(new File(Utility.getPropertiesFile()));
		try {
			connectionSettings = new ConnectionSettings(propertyReader.propertiesReader());
		} catch (Exception e) {
			LOG.error(" Database service exception: {}", e);
		}
	}

	public void insert(TranslationEntity translationEntity) throws ClassNotFoundException, SQLException {
		LOG.debug("insert {}", translationEntity);
		connectionSettings.build();
		String query = "insert into translation_entity(source_language, target_language, source_text, target_text, creation_time) values(?,?,?,?,?)";
		PreparedStatement prepareStatement = connectionSettings.getConnection().prepareStatement(query);
		prepareStatement.setString(1, translationEntity.getSourceLanguage());
		prepareStatement.setString(2, translationEntity.getTargetLanguage());
		prepareStatement.setString(3, translationEntity.getSourceText());
		prepareStatement.setString(4, translationEntity.getTargetText());

		java.sql.Timestamp sqlDateFormat = new java.sql.Timestamp(translationEntity.getCreationDate().getTime());
		prepareStatement.setTimestamp(5, sqlDateFormat);
		prepareStatement.executeUpdate();
		LOG.debug("Data save successfully!");
		connectionSettings.closeConnection();
	}

	public void update(long id, TranslationEntity translationEntity) {
		LOG.debug("Update");
	}

	public void delete(long id) {
		LOG.debug("Delete");
	}
}
