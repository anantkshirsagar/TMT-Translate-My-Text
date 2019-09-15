package com.tmt.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.app.ui.dialog.DownloadDialog;
import com.tmt.app.ui.dialog.UploadDialog;
import com.tmt.model.DownloadEntity;
import com.tmt.model.Languages;
import com.tmt.model.TranslateDetails;
import com.tmt.service.TranslatorService;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class MainFrameActionListener implements ActionListener {

	private final MainFrameDesign mainFrameDesign;

	public MainFrameActionListener(MainFrameDesign mainFrameDesign) {
		this.mainFrameDesign = mainFrameDesign;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mainFrameDesign.inputClear) {
			mainFrameDesign.inputEditor.setText("");
		}

		if (ae.getSource() == mainFrameDesign.outputClear) {
			mainFrameDesign.outputEditor.setText("");
		}

		if (ae.getSource() == mainFrameDesign.save) {

		}

		if (ae.getSource() == mainFrameDesign.inputCopy) {
			String inputText = mainFrameDesign.inputEditor.getText();
			ComponentUtils.copyToClipboard(inputText);
		}

		if (ae.getSource() == mainFrameDesign.outputCopy) {
			String outputText = mainFrameDesign.outputEditor.getText();
			ComponentUtils.copyToClipboard(outputText);
		}

		if (ae.getSource() == mainFrameDesign.download) {
			String sourceText = mainFrameDesign.inputEditor.getText();
			String targetText = mainFrameDesign.outputEditor.getText();
			String selectedSourceLang = mainFrameDesign.inputLangComboBox.getSelectedItem().toString();
			String selectedTargetLang = mainFrameDesign.outputLangComboBox.getSelectedItem().toString();
			
			DownloadEntity downloadEntity = new DownloadEntity();
			downloadEntity.setSourceText(sourceText);
			downloadEntity.setTargetText(targetText);
			downloadEntity.setSourceLanguage(selectedSourceLang);
			downloadEntity.setTargetLanguage(selectedTargetLang);
			
			DownloadDialog downloadDialog = new DownloadDialog();
			downloadDialog.setDownloadEntity(downloadEntity);
			JOptionPane optionPane = new JOptionPane();
			JDialog dialog = optionPane.createDialog("Download file dialog");
			dialog.setSize(530, 135);
			dialog.setLocationRelativeTo(mainFrameDesign);
			downloadDialog.setDialog(dialog);
			dialog.setContentPane(downloadDialog);
			dialog.setVisible(true);
		}

		if (ae.getSource() == mainFrameDesign.exchange) {
			String sourceText = mainFrameDesign.inputEditor.getText();
			String targetText = mainFrameDesign.outputEditor.getText();
			mainFrameDesign.inputEditor.setText(targetText);
			mainFrameDesign.outputEditor.setText(sourceText);
		}

		if (ae.getSource() == mainFrameDesign.convert) {
			String text = mainFrameDesign.inputEditor.getText();

			String sourceLang = mainFrameDesign.inputLangComboBox.getSelectedItem().toString();
			System.out.println(sourceLang);

			String targetLang = mainFrameDesign.outputLangComboBox.getSelectedItem().toString();
			System.out.println(targetLang);

			Languages languages = Utility.getLanguages();
			Map<String, String> languageMap = languages.getLanguageMap();

			TranslateDetails translateDetails = new TranslateDetails();
			translateDetails.setSourceLanguage(languageMap.get(sourceLang));
			translateDetails.setTargetLanguage(languageMap.get(targetLang));
			translateDetails.setText(text);

			TranslatorService translatorService = new TranslatorService();
			try {
				String translatedText = translatorService.getTranslatedText(translateDetails);
				mainFrameDesign.outputEditor.setText(translatedText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
