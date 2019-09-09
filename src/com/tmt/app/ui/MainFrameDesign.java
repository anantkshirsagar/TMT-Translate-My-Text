package com.tmt.app.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrameDesign extends JPanel implements IDesign {
	public JButton inputClear, outputClear, inputCopy, outputCopy, save, download, convert;
	public JComboBox inputLangComboBox, outputLangComboBox;
	public JEditorPane inputEditor, outputEditor;
	public JScrollPane inputScrollPane, outputScrollPane;
	private final String[] inputLangauges, outputLanguages;

	public MainFrameDesign(String[] inputLangauges, String[] outputLanguages) {
		this.inputLangauges = inputLangauges;
		this.outputLanguages = outputLanguages;

		setLayout(null);
		init();
		addItems();
	}

	@Override
	public void init() {
		inputClear = new JButton("Clear");
		inputClear.setBounds(30, 30, 100, 30);
		outputClear = new JButton("Clear");

		inputCopy = new JButton("Copy to clipboard");
		outputCopy = new JButton("Copy to clipboard");

		save = new JButton("Save");
		download = new JButton("Download");
		convert = new JButton("Convert");

		//inputLangComboBox = new JComboBox(inputLangauges);
		//outputLangComboBox = new JComboBox(outputLanguages);

		inputEditor = new JEditorPane();
		outputEditor = new JEditorPane();

		inputScrollPane = new JScrollPane(inputEditor);
		outputScrollPane = new JScrollPane(outputEditor);

	}

	@Override
	public void addItems() {
		add(inputClear);
	}
}
