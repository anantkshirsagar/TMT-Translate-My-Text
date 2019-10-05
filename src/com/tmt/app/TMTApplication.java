package com.tmt.app;

import java.io.IOException;
import java.util.Map;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.app.ui.MainFrame;
import com.tmt.constants.Resources;
import com.tmt.manager.ConfigurationReader;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class TMTApplication {

	private static final Logger LOG = LoggerFactory.getLogger(TMTApplication.class);
	public static void main(String[] args) throws IOException {
		try {
			ComponentUtils.checkInternetConnectivity();
			LOG.debug("Internet is connected!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Internet is not connected to the system!", Resources.ERROR_TITLE,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Map<String, String> configurationMap = ConfigurationReader.getConfigurationProperties(Resources.TMT_CFG);
		String propertiesPath = ConfigurationReader.getDBPropertiesPath(configurationMap);
		Utility.setPropertiesFile(propertiesPath);
		new MainFrame();
	}
}
