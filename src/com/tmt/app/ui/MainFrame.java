package com.tmt.app.ui;

import javax.swing.JFrame;

import com.tmt.app.listeners.MainFrameActionListener;
import com.tmt.app.listeners.MenuBarActionListeners;
import com.tmt.constants.UIConstants;

public class MainFrame extends JFrame implements IFrameProperties {

	private static final long serialVersionUID = 1L;
	private TMTMenuBar menuBar;
	private MenuBarActionListeners menuBarActionListener;

	private MainFrameDesign mainFrameDesign;
	private MainFrameActionListener mainFrameActionListener;

	public MainFrame() {
		super(UIConstants.MAIN_FRAME_TITLE);
		before(this);

		addCenterPanel();
		addMenuBar();
		after(this);
	}

	public void addCenterPanel() {
		mainFrameDesign = new MainFrameDesign(null, null);
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
		menuBar = new TMTMenuBar();
		menuBarActionListener = new MenuBarActionListeners(menuBar);
		menuBar.addActionListeners(menuBarActionListener);
		setJMenuBar(menuBar);
	}
}
