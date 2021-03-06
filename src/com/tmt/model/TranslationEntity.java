package com.tmt.model;

import java.util.Date;

public class TranslationEntity {
	private String sourceLanguage;
	private String targetLanguage;
	private String sourceText;
	private String targetText;
	private Date creationDate;

	public String getTargetLanguage() {
		return targetLanguage;
	}

	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TranslationEntity [sourceLanguage=");
		builder.append(sourceLanguage);
		builder.append(", targetLanguage=");
		builder.append(targetLanguage);
		builder.append(", sourceText=");
		builder.append(sourceText);
		builder.append(", targetText=");
		builder.append(targetText);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append("]");
		return builder.toString();
	}
}
