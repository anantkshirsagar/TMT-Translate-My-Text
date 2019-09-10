package com.tmt.app.ui;

import java.awt.Font;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.jidesoft.swing.ComboBoxSearchable;
import com.tmt.constants.UIConstants;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class MainFrameDesign extends JPanel implements IDesign {

	private static final long serialVersionUID = 1L;
	public JButton inputClear, outputClear, inputCopy, outputCopy, save, download, convert;
	public JComboBox<String> inputLangComboBox, outputLangComboBox;
	public JTextPane inputEditor, outputEditor;
	public JScrollPane inputScrollPane, outputScrollPane;
	private JLabel inputLabel, outputLabel;
	private Font font = new Font(null, Font.PLAIN, UIConstants.DEFAULT_FONT_SIZE);
	private String[] languages;

	public MainFrameDesign() {
		setLayout(null);
		loadLanguages();
		init();
		addItems();
	}

	private void loadLanguages() {
		Map<String, String> languageMap = Utility.getLanguages().getLanguageMap();
		languages = languageMap.keySet().toArray(new String[languageMap.size()]);
	}

	@Override
	public void init() {
		inputLabel = new JLabel("Select input language");
		inputLabel.setBounds(62, 50, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		inputLangComboBox = new JComboBox<String>(languages);
		inputLangComboBox.setBounds(200, 50, 120, UIConstants.COMPONENT_HEIGHT);
		new ComboBoxSearchable(inputLangComboBox);

		inputEditor = new JTextPane();
		inputEditor.setFont(font);
		
		inputScrollPane = new JScrollPane(inputEditor);
		inputScrollPane.setBounds(60, 100, UIConstants.EDITOR_WIDTH, UIConstants.EDITOR_HEIGHT);
		inputScrollPane.setBorder(ComponentUtils.getBorder("Input text"));
		

		outputLabel = new JLabel("Select output language");
		outputLabel.setBounds(750, 400, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		outputLangComboBox = new JComboBox<String>(languages);
		outputLangComboBox.setBounds(890, 400, 120, UIConstants.COMPONENT_HEIGHT);
		new ComboBoxSearchable(outputLangComboBox);

		convert = new JButton("Convert  >>");
		convert.setBounds(750, 450, 260, UIConstants.COMPONENT_HEIGHT);

		outputEditor = new JTextPane();
		outputEditor.setFont(font);
		
		outputScrollPane = new JScrollPane(outputEditor);
		outputScrollPane.setBounds(1050, 100, UIConstants.EDITOR_WIDTH, UIConstants.EDITOR_HEIGHT);
		outputScrollPane.setBorder(ComponentUtils.getBorder("Output text"));

		inputClear = new JButton("Clear");
		inputClear.setBounds(400, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		inputCopy = new JButton("Copy to clipboard");
		inputCopy.setBounds(560, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		save = new JButton("Save");
		save.setBounds(1050, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		outputClear = new JButton("Clear");
		outputClear.setBounds(1210, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		outputCopy = new JButton("Copy to clipboard");
		outputCopy.setBounds(1370, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		download = new JButton("Download");
		download.setBounds(1530, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);
	}

	@Override
	public void addItems() {
		add(inputLabel);
		add(inputLangComboBox);
		add(inputScrollPane);
		add(outputLabel);
		add(outputLangComboBox);
		add(convert);
		add(outputScrollPane);
		add(inputClear);
		add(inputCopy);
		add(save);
		add(outputClear);
		add(outputCopy);
		add(download);
	}
}
