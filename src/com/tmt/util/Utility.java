package com.tmt.util;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.model.Languages;

public class Utility {
	private static MainFrameDesign mainFrameDesign;
	private static Languages languages;

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
}
