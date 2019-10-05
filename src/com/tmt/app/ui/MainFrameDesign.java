package com.tmt.app.ui;

import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jidesoft.swing.ComboBoxSearchable;
import com.tmt.app.listeners.InputEditorKeyListener;
import com.tmt.constants.Resources;
import com.tmt.constants.UIConstants;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class MainFrameDesign extends JPanel implements IDesign {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(MainFrameDesign.class);
	public JButton inputClear, outputClear, inputCopy, outputCopy, save, download, convert, exchange;
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
		LOG.debug("Loading languagese from supported-lang.properties");
		Map<String, String> languageMap = Utility.getLanguages().getLanguageMap();
		languages = languageMap.keySet().toArray(new String[languageMap.size()]);
	}

	@Override
	public void init() {
		LOG.debug("Initializing main screen");
		inputLabel = new JLabel("Select input language");
		inputLabel.setBounds(30, 20, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		inputLangComboBox = new JComboBox<String>(languages);
		inputLangComboBox.setBounds(180, 20, 120, UIConstants.COMPONENT_HEIGHT);
		inputLangComboBox.setSelectedItem("English");
		new ComboBoxSearchable(inputLangComboBox);

		inputEditor = new JTextPane();
		inputEditor.setFont(font);
		new InputEditorKeyListener(inputEditor);
		inputScrollPane = new JScrollPane(inputEditor);
		inputScrollPane.setBounds(30, 65, UIConstants.EDITOR_WIDTH, UIConstants.EDITOR_HEIGHT);
		inputScrollPane.setBorder(ComponentUtils.getBorder("Input text"));

		outputLabel = new JLabel("Select output language");
		outputLabel.setBounds(UIConstants.BUTTON_X_AXIS, 200, UIConstants.COMPONENT_WIDTH,
				UIConstants.COMPONENT_HEIGHT);

		outputLangComboBox = new JComboBox<String>(languages);
		outputLangComboBox.setBounds(600, 200, 120, UIConstants.COMPONENT_HEIGHT);
		outputLangComboBox.setSelectedItem("Marathi");
		new ComboBoxSearchable(outputLangComboBox);

		convert = new JButton("Convert  >>");
		convert.setBounds(UIConstants.BUTTON_X_AXIS, 250, UIConstants.BUTTON_Y_AXIS, UIConstants.COMPONENT_HEIGHT);
		convert.setEnabled(false);
		
		exchange = new JButton();
		ImageIcon imageIcon = null;
		try {
			imageIcon = ComponentUtils.getImageIcon(Resources.EXCHANGE_ICON_IMG);
		} catch (IOException e) {
			LOG.debug("Image path exception {}", e);
		}
		exchange.setIcon(imageIcon);
		exchange.setBounds(UIConstants.BUTTON_X_AXIS, 295, UIConstants.BUTTON_Y_AXIS, UIConstants.COMPONENT_HEIGHT);
		exchange.setToolTipText("Exchange text");

		outputEditor = new JTextPane();
		outputEditor.setFont(font);
		outputScrollPane = new JScrollPane(outputEditor);
		outputScrollPane.setBounds(740, 65, UIConstants.EDITOR_WIDTH, UIConstants.EDITOR_HEIGHT);
		outputScrollPane.setBorder(ComponentUtils.getBorder("Output text"));

		inputClear = new JButton("Clear");
		inputClear.setBounds(120, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		inputCopy = new JButton("Copy to clipboard");
		inputCopy.setBounds(278, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		save = new JButton("Save");
		save.setBounds(UIConstants.BUTTON_X_AXIS, 340, 273, UIConstants.COMPONENT_HEIGHT);
		
		outputClear = new JButton("Clear");
		outputClear.setBounds(743, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		outputCopy = new JButton("Copy to clipboard");
		outputCopy.setBounds(900, UIConstants.Y_AXIS, UIConstants.COMPONENT_WIDTH, UIConstants.COMPONENT_HEIGHT);

		download = new JButton("Download");
		download.setBounds(450, 385, UIConstants.BUTTON_Y_AXIS, UIConstants.COMPONENT_HEIGHT);
	}

	@Override
	public void addItems() {
		LOG.debug("Adding components in screen.");
		add(inputLabel);
		add(inputLangComboBox);
		add(inputScrollPane);
		add(outputLabel);
		add(outputLangComboBox);
		add(convert);
		add(exchange);
		add(outputScrollPane);
		add(inputClear);
		add(inputCopy);
		add(save);
		add(outputClear);
		add(outputCopy);
		add(download);
	}
}
