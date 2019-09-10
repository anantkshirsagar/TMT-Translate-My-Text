package com.tmt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.tmt.model.TranslateDetails;

public class TranslatorService {

	public String getTranslatedText(TranslateDetails translateDetails) throws IOException {
		String urlStr = getURL(translateDetails);
		URL url = new URL(urlStr);
		StringBuilder response = new StringBuilder();
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

	public String getURL(TranslateDetails translateDetails) throws UnsupportedEncodingException {
		String url = "https://script.google.com/macros/s/AKfycbyWYYYE3BdbvYErRVbI-rA3EnDhZtb8oItXvZ1fWcn9tkP5zbp2/exec"
				+ "?q=" + URLEncoder.encode(translateDetails.getText(), "UTF-8") + "&target="
				+ translateDetails.getTargetLanguage() + "&source=" + translateDetails.getSourceLanguage();
		return url;
	}
}
