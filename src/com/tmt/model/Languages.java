package com.tmt.model;

import java.util.Collections;
import java.util.Map;

public class Languages {
	private Map<String, String> languageMap;

	public Map<String, String> getLanguageMap() {
		return Collections.unmodifiableMap(languageMap);
	}

	public void setLanguageMap(Map<String, String> languageMap) {
		this.languageMap = languageMap;
	}

}
