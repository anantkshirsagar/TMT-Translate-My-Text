package com.tmt.model;

public class DownloadEntity {
	private String sourceText;
	private String targetText;
	private String sourceLanguage;
	private String targetLanguage;
	private String folderPath;
	private String extension;

	public String getSourceText() {
		return sourceText;
	}

	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	public String getTargetText() {
		return targetText;
	}

	public void setTargetText(String targetText) {
		this.targetText = targetText;
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DownloadDetails [sourceText=");
		builder.append(sourceText);
		builder.append(", targetText=");
		builder.append(targetText);
		builder.append(", sourceLanguage=");
		builder.append(sourceLanguage);
		builder.append(", targetLanguage=");
		builder.append(targetLanguage);
		builder.append(", folderPath=");
		builder.append(folderPath);
		builder.append(", extension=");
		builder.append(extension);
		builder.append("]");
		return builder.toString();
	}
}
