package com.tmt.app;

import java.io.IOException;
import java.util.Map;

import com.tmt.app.ui.MainFrame;
import com.tmt.constants.Resources;
import com.tmt.manager.ConfigurationReader;
import com.tmt.util.Utility;

public class TMTApplication {
	public static void main(String[] args) throws IOException {
		Map<String, String> configurationMap = ConfigurationReader.getConfigurationProperties(Resources.TMT_CFG);
		String propertiesPath = ConfigurationReader.getDBPropertiesPath(configurationMap);
		Utility.setPropertiesFile(propertiesPath);
		new MainFrame();
	}
}
