package com.tmt.app.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.tmt.app.listeners.MenuBarActionListeners;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private JMenu file, help;
	public JMenuItem upload, recent;
	public JMenuItem fontSettings;

	public MenuBar() {
		super();
		this.init();
		this.addItems();
	}

	public void init() {
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		upload = new JMenuItem("Upload");
		upload.setMnemonic(KeyEvent.VK_U);

		recent = new JMenuItem("Recent");
		recent.setMnemonic(KeyEvent.VK_R);

		fontSettings = new JMenuItem("Font Settings");
		fontSettings.setMnemonic(KeyEvent.VK_S);
	}

	public void addItems() {
		file.add(upload);
		file.add(recent);
		help.add(fontSettings);
		add(file);
		add(help);
	}

	public void addActionListeners(MenuBarActionListeners menuBarActionListener) {
		upload.addActionListener(menuBarActionListener);
		recent.addActionListener(menuBarActionListener);
		fontSettings.addActionListener(menuBarActionListener);
	}
}