package com.tmt.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.tmt.model.Languages;

public class LanguageReader {

	private final InputStream fileIS;
	private Properties properties;

	public LanguageReader(InputStream fileIS) {
		this.fileIS = fileIS;
	}

	public Languages readLanguages() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(fileIS);
		return getAllLanguages();
	}

	public Languages getAllLanguages() {
		Map<String, String> languageMap = new HashMap<String, String>();
		Set<Object> keys = getAllKeys();
		for (Object key : keys) {
			languageMap.put(key.toString().trim(), getPropertyValue(key.toString().trim()).trim());
		}
		Languages languages = new Languages();
		languages.setLanguageMap(languageMap);
		return languages;
	}

	public Set<Object> getAllKeys() {
		return properties.keySet();
	}

	public String getPropertyValue(String key) {
		return properties.getProperty(key);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("lang/supported-lang.properties");
		LanguageReader reader = new LanguageReader(resourceAsStream);
		Languages readLanguages = reader.readLanguages();
		for (Map.Entry<String, String> entry : readLanguages.getLanguageMap().entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + " = " + value);
		}
	}
}
