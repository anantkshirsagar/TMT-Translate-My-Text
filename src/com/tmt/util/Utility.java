package com.tmt.util;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.app.ui.RecentFrameDesign;
import com.tmt.model.Languages;

public class Utility {

	private static MainFrameDesign mainFrameDesign;
	private static Languages languages;
	private static RecentFrameDesign recentFrameDesign;
	private static String propertiesFile;

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
}
