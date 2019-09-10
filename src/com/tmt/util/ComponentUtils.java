package com.tmt.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

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
}
