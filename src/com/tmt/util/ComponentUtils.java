package com.tmt.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import com.tmt.constants.ConfigurationConstants;
import com.tmt.constants.Resources;

public class ComponentUtils {
	private ComponentUtils() {
	}

	public static TitledBorder getBorder(String title) {
		return BorderFactory.createTitledBorder(title);
	}

	public static void copyToClipboard(String text) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection data = new StringSelection(text);
		clipboard.setContents(data, data);
	}

	public static ImageIcon getImageIcon(String imagePath) throws IOException {
		if (imagePath == null || imagePath.isEmpty()) {
			throw new NullPointerException("Please provide image path.");
		}
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(imagePath);
		Image img = ImageIO.read(resourceAsStream);
		return new ImageIcon(img);
	}

	public static String getKey(Map<String, String> map, String value) {
		if (map == null) {
			throw new NullPointerException("Map cannot be null");
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String valueFromMap = entry.getValue();
			String keyFromMap = entry.getKey();
			if (valueFromMap.equals(value)) {
				return keyFromMap;
			}
		}
		return null;
	}

	public static String getFileContents(String filePath) throws IOException {
		StringBuilder fileContents = new StringBuilder();
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			fileContents.append(line).append(Resources.LINE_SEPARATOR);
		}
		return fileContents.toString();
	}

	public static String checkAvailableDatabaseAndGetFilePath(Map<String, String> configurationMap) {
		if (configurationMap.get(ConfigurationConstants.TMT_DATABASE_PSQL) != null
				&& Boolean.parseBoolean(configurationMap.get(ConfigurationConstants.TMT_DATABASE_PSQL))) {
			return configurationMap.get(ConfigurationConstants.TMT_POSTGRES_PROPERTIES);
		} else if (configurationMap.get(ConfigurationConstants.TMT_DATABASE_MYSQL) != null
				&& Boolean.parseBoolean(configurationMap.get(ConfigurationConstants.TMT_DATABASE_MYSQL))) {
			return configurationMap.get(ConfigurationConstants.TMT_MYSQL_PROPERTIES);
		} else if (configurationMap.get(ConfigurationConstants.TMT_DATABASE_ORACLE) != null
				&& Boolean.parseBoolean(configurationMap.get(ConfigurationConstants.TMT_DATABASE_ORACLE))) {
			return configurationMap.get(ConfigurationConstants.TMT_ORACLE_PROPERTIES);
		}
		return null;
	}
}
