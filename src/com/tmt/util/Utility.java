package com.tmt.util;

import java.util.Map;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.app.ui.RecentFrameDesign;
import com.tmt.model.Languages;
import com.tmt.model.TranslationEntity;

public class Utility {

	private static MainFrameDesign mainFrameDesign;
	private static Languages languages;
	private static RecentFrameDesign recentFrameDesign;
	private static String propertiesFile;
	private static Map<Integer, TranslationEntity> dataMap;

	private Utility() {
	}

	public static MainFrameDesign getMainFrameDesign() {
		return mainFrameDesign;
	}

	public static void setMainFrameDesign(MainFrameDesign mainFrameDesign) {
		Utility.mainFrameDesign = mainFrameDesign;
	}

	public static Languages getLanguages() {
		return languages;
	}

	public static void setLanguages(Languages languages) {
		Utility.languages = languages;
	}

	public static RecentFrameDesign getRecentFrameDesign() {
		return recentFrameDesign;
	}

	public static void setRecentFrameDesign(RecentFrameDesign recentFrameDesign) {
		Utility.recentFrameDesign = recentFrameDesign;
	}

	public static String getPropertiesFile() {
		return propertiesFile;
	}

	public static void setPropertiesFile(String propertiesFile) {
		Utility.propertiesFile = propertiesFile;
	}

	public static Map<Integer, TranslationEntity> getDataMap() {
		return dataMap;
	}

	public static void setDataMap(Map<Integer, TranslationEntity> dataMap) {
		Utility.dataMap = dataMap;
	}
}
