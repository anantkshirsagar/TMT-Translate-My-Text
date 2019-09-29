package com.tmt.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.app.listeners.MainFrameActionListener;
import com.tmt.constants.ConfigurationConstants;
import com.tmt.constants.Resources;

public class ConfigurationReader {

	private static final Logger LOG = LoggerFactory.getLogger(MainFrameActionListener.class);

	public static Map<String, String> getConfigurationProperties(String configurationFile)
			throws FileNotFoundException, IOException {
		LOG.debug("Loading configurations...");
		Properties properties = new Properties();
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(configurationFile);
		properties.load(resourceAsStream);

		Map<String, String> cfgProperties = new HashMap<String, String>();
		cfgProperties.put(ConfigurationConstants.TMT_DATABASE_PSQL,
				properties.getProperty(ConfigurationConstants.TMT_DATABASE_PSQL).trim());
		cfgProperties.put(ConfigurationConstants.TMT_POSTGRES_PROPERTIES,
				properties.getProperty(ConfigurationConstants.TMT_POSTGRES_PROPERTIES).trim());
		cfgProperties.put(ConfigurationConstants.TMT_DATABASE_MYSQL,
				properties.getProperty(ConfigurationConstants.TMT_DATABASE_MYSQL).trim());
		cfgProperties.put(ConfigurationConstants.TMT_MYSQL_PROPERTIES,
				properties.getProperty(ConfigurationConstants.TMT_MYSQL_PROPERTIES).trim());
		cfgProperties.put(ConfigurationConstants.TMT_DATABASE_ORACLE,
				properties.getProperty(ConfigurationConstants.TMT_DATABASE_ORACLE).trim());
		cfgProperties.put(ConfigurationConstants.TMT_ORACLE_PROPERTIES,
				properties.getProperty(ConfigurationConstants.TMT_ORACLE_PROPERTIES).trim());
		return cfgProperties;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String, String> configurations = ConfigurationReader.getConfigurationProperties(Resources.TMT_CFG);
		LOG.debug("configurations {}", configurations);
	}
}
