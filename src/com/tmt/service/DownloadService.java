package com.tmt.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.constants.Resources;
import com.tmt.model.DownloadEntity;

public class DownloadService {

	private static final Logger LOG = LoggerFactory.getLogger(DownloadService.class);
	private static final String LINE = "------";

	public void download(DownloadEntity downloadDetails) throws IOException {
		LOG.debug("In download {}", downloadDetails);
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
		content.append(LINE).append(downloadDetails.getSourceLanguage()).append(LINE).append(Resources.LINE_SEPARATOR);
		content.append(downloadDetails.getSourceText()).append(Resources.LINE_SEPARATOR)
				.append(Resources.LINE_SEPARATOR);
		content.append(LINE).append(downloadDetails.getTargetLanguage()).append(LINE).append(Resources.LINE_SEPARATOR);
		content.append(downloadDetails.getTargetText());
		FileOutputStream fileOuputStream = new FileOutputStream(new File(filePath));
		fileOuputStream.write(content.toString().getBytes());
		fileOuputStream.flush();
		fileOuputStream.close();
		LOG.debug("Text file exported successfully!");
	}

	public void exportWordFile(DownloadEntity downloadDetails, String filePath) throws IOException {
		XWPFDocument document = new XWPFDocument();
		FileOutputStream out = new FileOutputStream(new File(filePath));
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();

		StringBuilder content = new StringBuilder();
		content.append(LINE).append(downloadDetails.getSourceLanguage()).append(LINE);
		run.setText(content.toString(), 0);
		run.addBreak();

		content = new StringBuilder();
		content.append(downloadDetails.getSourceText()).append(Resources.LINE_SEPARATOR);
		run.setText(content.toString(), 1);
		run.addBreak();

		content = new StringBuilder();
		content.append(LINE).append(downloadDetails.getTargetLanguage()).append(LINE);
		run.setText(content.toString(), 2);
		run.addBreak();

		content = new StringBuilder();
		content.append(downloadDetails.getTargetText());
		run.setText(content.toString(), 3);
		run.addBreak();

		document.write(out);
		out.close();
		document.close();
		LOG.debug("Word file exported successfully!");
	}
}
