package com.tmt.service;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List<TranslationEntity> getTranslationEntities() throws ClassNotFoundException, SQLException {
		connectionSettings.build();
		List<TranslationEntity> entityList = new ArrayList<TranslationEntity>();
		String query = "select * from translation_entity";
		PreparedStatement preparedStatement = connectionSettings.getConnection().prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			TranslationEntity translationEntity = new TranslationEntity();
			translationEntity.setSourceLanguage(resultSet.getString("source_language"));
			translationEntity.setTargetLanguage(resultSet.getString("target_language"));
			translationEntity.setSourceText(resultSet.getString("source_text"));
			translationEntity.setTargetText(resultSet.getString("target_text"));
			translationEntity.setCreationDate(resultSet.getTimestamp("creation_time"));
			entityList.add(translationEntity);
		}

		LOG.debug("Data list prepared!");
		connectionSettings.closeConnection();
		return entityList;
	}

	public TranslationEntity getTranslationEntityById(int id) throws SQLException, ClassNotFoundException {
		connectionSettings.build();
		String query = "select * from translation_entity where id = ?";
		PreparedStatement preparedStatement = connectionSettings.getConnection().prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		TranslationEntity translationEntity = null;
		if (resultSet.next()) {
			translationEntity = new TranslationEntity();
			translationEntity.setSourceLanguage(resultSet.getString("source_language"));
			translationEntity.setTargetLanguage(resultSet.getString("target_language"));
			translationEntity.setSourceText(resultSet.getString("source_text"));
			translationEntity.setTargetText(resultSet.getString("target_text"));
			translationEntity.setCreationDate(resultSet.getDate("creation_time"));
		}
		LOG.debug("Translation entity {}", translationEntity);
		connectionSettings.closeConnection();
		return translationEntity;
	}

	public void update(long id, TranslationEntity translationEntity) {
		LOG.debug("Update");
	}

	public void delete(long id) {
		LOG.debug("Delete");
	}
}
