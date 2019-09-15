package com.tmt.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.tmt.constants.Resources;
import com.tmt.model.DownloadEntity;

public class DownloadService {
	public void download(DownloadEntity downloadDetails) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Resources.DOWNLOAD_DATE_FORMAT);
		String currentDate = dateFormat.format(new Date());
		String filename = Resources.APP_NAME + "-" + currentDate + downloadDetails.getExtension();
		String filePath = downloadDetails.getFolderPath() + File.separator + filename;
		
		if (Resources.DOCX.equals(downloadDetails.getExtension().replaceAll("\\.", ""))) {
			exportWordFile(downloadDetails, filePath);
		} else if (Resources.TXT.equals(downloadDetails.getExtension().replaceAll("\\.", ""))) {
			exportTextFile(downloadDetails, filePath);
		}
	}

	public void exportTextFile(DownloadEntity downloadDetails, String filePath) throws IOException {
		StringBuilder content = new StringBuilder();
		content.append("-----").append(downloadDetails.getSourceLanguage()).append("-----")
				.append(Resources.LINE_SEPARATOR);
		content.append(downloadDetails.getSourceText()).append(Resources.LINE_SEPARATOR)
				.append(Resources.LINE_SEPARATOR);
		content.append("-----").append(downloadDetails.getTargetLanguage()).append("-----")
				.append(Resources.LINE_SEPARATOR);
		content.append(downloadDetails.getTargetText());
		FileOutputStream fileOuputStream = new FileOutputStream(new File(filePath));
		fileOuputStream.write(content.toString().getBytes());
		fileOuputStream.flush();
		fileOuputStream.close();
		System.out.println("File written success!");
	}

	public void exportWordFile(DownloadEntity downloadDetails, String filePath) throws IOException {
		XWPFDocument document = new XWPFDocument();
		FileOutputStream out = new FileOutputStream(new File(filePath));
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();

		StringBuilder content = new StringBuilder();
		content.append("-----").append(downloadDetails.getSourceLanguage()).append("-----");
		run.setText(content.toString(), 0);
		run.addBreak();

		content = new StringBuilder();
		content.append(downloadDetails.getSourceText()).append(Resources.LINE_SEPARATOR);
		run.setText(content.toString(), 1);
		run.addBreak();

		content = new StringBuilder();
		content.append("-----").append(downloadDetails.getTargetLanguage()).append("-----");
		run.setText(content.toString(), 2);
		run.addBreak();

		content = new StringBuilder();
		content.append(downloadDetails.getTargetText());
		run.setText(content.toString(), 3);
		run.addBreak();

		document.write(out);
		out.close();
		document.close();
		System.out.println("File downloaded");
	}

	public static void main(String[] args) throws IOException {
		DownloadEntity downloadDetails = new DownloadEntity();
		downloadDetails.setExtension(".docx");
		downloadDetails.setSourceLanguage("English");
		downloadDetails.setSourceText(
				"I am writing values into a word template using apache poi 3.8. I replace specific strings in a word file (keys) with required values, e.g. word document has a paragraph containing key %Entry1%, and I want to replace it with Entry text line1 \nnew line. All replaced keys and values are stored in a Map in my realisation.");
		downloadDetails.setFolderPath("G:\\eclipse-nsg-workspace\\TMT-Translate-My-Text\\tests");
		downloadDetails.setTargetLanguage("Marathi");
		downloadDetails.setTargetText(
				"मी अपाचे पोई 3.8 वापरून वर्ड टेम्पलेटमध्ये व्हॅल्यूज लिहित आहे. मी वर्ड फाईल (कीज) मधील विशिष्ट तारांना आवश्यक मूल्यांसह पुनर्स्थित करतो, उदा. वर्ड डॉक्युमेंटमध्ये% एंट्री 1% की असलेले एक परिच्छेद आहे आणि मला ते &quot;एंट्री टेक्स्ट लाइन 1 ne n नवीन लाइन&quot; सह पुनर्स्थित करायचे आहे. सर्व बदललेल्या की व मूल्ये माझ्या प्राप्तीमध्ये नकाशामध्ये संग्रहित केल्या आहेत.");
		new DownloadService().download(downloadDetails);

	}
}
