package com.tmt.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import com.tmt.app.ui.dialog.DownloadDialog;
import com.tmt.constants.ConfigurationConstants;
import com.tmt.constants.Resources;
import com.tmt.model.DownloadEntity;
import com.tmt.model.TranslationEntity;

public class ComponentUtils {

	private static String errorMessage;

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

	public static boolean isValidTranslationEntity(TranslationEntity translationEntity) {
		if (isEmpty(translationEntity.getSourceLanguage())) {
			setErrorMessage("Source language cannot be null");
			return true;
		}
		if (isEmpty(translationEntity.getTargetLanguage())) {
			setErrorMessage("Target language cannot be null");
			return true;
		}
		if (isEmpty(translationEntity.getSourceText())) {
			setErrorMessage("Input text cannot be null");
			return true;
		}
		if (isEmpty(translationEntity.getTargetText())) {
			setErrorMessage("Output text cannot be null! Please press convert button.");
			return true;
		}
		return false;
	}

	public static boolean isValidDownloadEntity(DownloadEntity downloadEntity) {
		if (isEmpty(downloadEntity.getSourceLanguage())) {
			setErrorMessage("Source language cannot be null");
			return true;
		}
		if (isEmpty(downloadEntity.getTargetLanguage())) {
			setErrorMessage("Target language cannot be null");
			return true;
		}
		if (isEmpty(downloadEntity.getSourceText())) {
			setErrorMessage("Input text cannot be null");
			return true;
		}
		if (isEmpty(downloadEntity.getTargetText())) {
			setErrorMessage("Output text cannot be null! Please press convert button.");
			return true;
		}
		if (isEmpty(downloadEntity.getFolderPath())) {
			setErrorMessage("Folder path cannot be null");
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String contentStr) {
		if (contentStr == null || contentStr.equals("")) {
			return true;
		}
		return false;
	}

	public static void checkInternetConnectivity() throws IOException {
		URL url = new URL(Resources.CONNECTION_CHECK_URL);
		URLConnection connection = url.openConnection();
		connection.connect();
	}
	
	public static DownloadEntity getDownloadEntity(TranslationEntity translationEntity) {
		DownloadEntity downloadEntity = new DownloadEntity();
		downloadEntity.setSourceLanguage(translationEntity.getSourceLanguage());
		downloadEntity.setSourceText(translationEntity.getSourceText());
		downloadEntity.setTargetLanguage(translationEntity.getTargetLanguage());
		downloadEntity.setTargetText(translationEntity.getTargetText());
		return downloadEntity;
	}
	
	public static String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String errorMessage) {
		ComponentUtils.errorMessage = errorMessage;
	}
	
	public static void showDialog(DownloadEntity downloadEntity) {
		DownloadDialog downloadDialog = new DownloadDialog();
		downloadDialog.setDownloadEntity(downloadEntity);
		JOptionPane optionPane = new JOptionPane();
		JDialog dialog = optionPane.createDialog("Download file dialog");
		dialog.setSize(530, 135);
		dialog.setLocationRelativeTo(Utility.getMainFrameDesign());
		downloadDialog.setDialog(dialog);
		dialog.setContentPane(downloadDialog);
		dialog.setVisible(true);

	}
}
