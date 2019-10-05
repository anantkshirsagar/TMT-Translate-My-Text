package com.tmt.app.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JEditorPane;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.util.Utility;

public class InputEditorKeyListener extends KeyAdapter {

	private final JEditorPane inputEditor;

	public InputEditorKeyListener(JEditorPane inputEditor) {
		this.inputEditor = inputEditor;
		this.inputEditor.addKeyListener(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		MainFrameDesign mainFrameDesign = Utility.getMainFrameDesign();
		if (!inputEditor.getText().equals("")) {
			mainFrameDesign.convert.setEnabled(true);
		} else if (inputEditor.getText().length() > 0) {
			mainFrameDesign.convert.setEnabled(true);
		} else {
			mainFrameDesign.convert.setEnabled(false);
		}
	}
}
