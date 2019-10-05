package com.tmt.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.model.DownloadEntity;
import com.tmt.model.Languages;
import com.tmt.model.TranslationEntity;
import com.tmt.service.DataService;
import com.tmt.service.TranslatorService;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class MainFrameActionListener implements ActionListener {

	private static final Logger LOG = LoggerFactory.getLogger(MainFrameActionListener.class);
	private final MainFrameDesign mainFrameDesign;

	public MainFrameActionListener(MainFrameDesign mainFrameDesign) {
		this.mainFrameDesign = mainFrameDesign;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mainFrameDesign.inputClear) {
			LOG.debug("Clearing input text...");
			mainFrameDesign.inputEditor.setText("");
			mainFrameDesign.convert.setEnabled(false);
		}

		if (ae.getSource() == mainFrameDesign.outputClear) {
			LOG.debug("Clearing output text...");
			mainFrameDesign.outputEditor.setText("");
		}

		if (ae.getSource() == mainFrameDesign.save) {
			String sourceLang = mainFrameDesign.inputLangComboBox.getSelectedItem().toString();
			LOG.debug("Source language {}", sourceLang);

			String targetLang = mainFrameDesign.outputLangComboBox.getSelectedItem().toString();
			LOG.debug("Target language {}", targetLang);

			Languages languages = Utility.getLanguages();
			Map<String, String> languageMap = languages.getLanguageMap();

			TranslationEntity translationEntity = new TranslationEntity();
			translationEntity.setCreationDate(new Date());
			translationEntity.setSourceLanguage(languageMap.get(sourceLang));
			translationEntity.setSourceText(mainFrameDesign.inputEditor.getText());
			translationEntity.setTargetLanguage(languageMap.get(targetLang));
			translationEntity.setTargetText(mainFrameDesign.outputEditor.getText());
			if (ComponentUtils.isValidTranslationEntity(translationEntity)) {
				LOG.debug("Error Message {}", ComponentUtils.getErrorMessage());
				JOptionPane.showMessageDialog(null, ComponentUtils.getErrorMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			DataService dataService = new DataService();
			try {
				dataService.insert(translationEntity);
				JOptionPane.showMessageDialog(null, "Data saved successfully!", "Information",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ClassNotFoundException | SQLException e) {
				LOG.error("Exception {}", e);
				JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (ae.getSource() == mainFrameDesign.inputCopy) {
			LOG.debug("Input text copied");
			String inputText = mainFrameDesign.inputEditor.getText();
			ComponentUtils.copyToClipboard(inputText);
		}

		if (ae.getSource() == mainFrameDesign.outputCopy) {
			LOG.debug("Output text copied");
			String outputText = mainFrameDesign.outputEditor.getText();
			ComponentUtils.copyToClipboard(outputText);
		}

		if (ae.getSource() == mainFrameDesign.download) {
			LOG.debug("File downloading...");
			String sourceText = mainFrameDesign.inputEditor.getText();
			String targetText = mainFrameDesign.outputEditor.getText();
			String selectedSourceLang = mainFrameDesign.inputLangComboBox.getSelectedItem().toString();
			String selectedTargetLang = mainFrameDesign.outputLangComboBox.getSelectedItem().toString();

			DownloadEntity downloadEntity = new DownloadEntity();
			downloadEntity.setSourceText(sourceText);
			downloadEntity.setTargetText(targetText);
			downloadEntity.setSourceLanguage(selectedSourceLang);
			downloadEntity.setTargetLanguage(selectedTargetLang);

			ComponentUtils.showDialog(downloadEntity);
		}

		if (ae.getSource() == mainFrameDesign.exchange) {
			String sourceText = mainFrameDesign.inputEditor.getText();
			String targetText = mainFrameDesign.outputEditor.getText();
			String selectedInputLang = mainFrameDesign.inputLangComboBox.getSelectedItem().toString();
			mainFrameDesign.inputLangComboBox.setSelectedItem(mainFrameDesign.outputLangComboBox.getSelectedItem());
			mainFrameDesign.outputLangComboBox.setSelectedItem(selectedInputLang);
			mainFrameDesign.inputEditor.setText(targetText);
			mainFrameDesign.outputEditor.setText(sourceText);
		}

		if (ae.getSource() == mainFrameDesign.convert) {
			LOG.debug("Converting text from source to target language...");

			String sourceLang = mainFrameDesign.inputLangComboBox.getSelectedItem().toString();
			LOG.debug("Source language {}", sourceLang);

			String targetLang = mainFrameDesign.outputLangComboBox.getSelectedItem().toString();
			LOG.debug("Target language {}", targetLang);

			Languages languages = Utility.getLanguages();
			Map<String, String> languageMap = languages.getLanguageMap();

			TranslationEntity translationEntity = new TranslationEntity();
			translationEntity.setCreationDate(new Date());
			translationEntity.setSourceLanguage(languageMap.get(sourceLang));
			translationEntity.setSourceText(mainFrameDesign.inputEditor.getText());
			translationEntity.setTargetLanguage(languageMap.get(targetLang));
			translationEntity.setTargetText(mainFrameDesign.outputEditor.getText());
			LOG.debug("translation entity {}", translationEntity);

			TranslatorService translatorService = new TranslatorService();
			try {
				String translatedText = translatorService.getTranslatedText(translationEntity);
				mainFrameDesign.outputEditor.setText(translatedText);
			} catch (IOException e) {
				LOG.error("Translation exception {}", e);
			}
		}
	}
}
