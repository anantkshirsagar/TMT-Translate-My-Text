package com.tmt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.model.TranslationEntity;

public class DatabaseService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

	public DatabaseService() {
	}
	
	public void save(TranslationEntity translationEntity) {
		LOG.debug("Saving data...");
		
	}
}
