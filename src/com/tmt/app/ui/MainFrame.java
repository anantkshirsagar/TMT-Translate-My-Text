package com.tmt.app.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

import com.tmt.app.listeners.MainFrameActionListener;
import com.tmt.app.listeners.MenuBarActionListeners;
import com.tmt.constants.UIConstants;
import com.tmt.model.Languages;
import com.tmt.util.Utility;
import com.tmt.util.LanguageReader;

public class MainFrame extends JFrame implements IFrameProperties {

	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;
	private MenuBarActionListeners menuBarActionListener;

	private MainFrameDesign mainFrameDesign;
	private MainFrameActionListener mainFrameActionListener;
	private LanguageReader languageReader;

	public MainFrame() {
		super(UIConstants.MAIN_FRAME_TITLE);
		before(this);

		try {
			loadLanguages();
		} catch (IOException e) {
			System.out.println(" Language exception: " +e);
		}
		
		addCenterPanel();
		addMenuBar();

		Utility.setMainFrameDesign(mainFrameDesign);
		after(this);
	}

	public void loadLanguages() throws FileNotFoundException, IOException {
		InputStream fileIS = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(UIConstants.LANGUAGE_SOURCE_PATH);
		languageReader = new LanguageReader(fileIS);
		Utility.setLanguages(languageReader.readLanguages());
	}

	public void addCenterPanel() {
		mainFrameDesign = new MainFrameDesign();
		mainFrameActionListener = new MainFrameActionListener(mainFrameDesign);
		addActionListeners();
		this.add(mainFrameDesign);
	}

	public void addActionListeners() {
		mainFrameDesign.inputClear.addActionListener(mainFrameActionListener);
		mainFrameDesign.outputClear.addActionListener(mainFrameActionListener);
		mainFrameDesign.save.addActionListener(mainFrameActionListener);
		mainFrameDesign.inputCopy.addActionListener(mainFrameActionListener);
		mainFrameDesign.outputCopy.addActionListener(mainFrameActionListener);
		mainFrameDesign.download.addActionListener(mainFrameActionListener);
		mainFrameDesign.convert.addActionListener(mainFrameActionListener);
	}

	public void addMenuBar() {
		menuBar = new MenuBar();
		menuBarActionListener = new MenuBarActionListeners(menuBar);
		menuBar.addActionListeners(menuBarActionListener);
		setJMenuBar(menuBar);
	}
}
