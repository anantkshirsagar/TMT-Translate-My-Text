package com.tmt.app.ui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.tmt.app.listeners.MenuBarActionListeners;

public class TMTMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private JMenu file, help;
	public JMenuItem upload, recent;
	public JMenuItem userManual;

	public TMTMenuBar() {
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
		
		userManual = new JMenuItem("User Manual");
		userManual.setMnemonic(KeyEvent.VK_U);
	}

	public void addItems() {
		file.add(upload);
		file.add(recent);
		help.add(userManual);
		
		this.add(file);
		this.add(help);
	}

	public void addActionListeners(MenuBarActionListeners menuBarActionListener) {
		upload.addActionListener(menuBarActionListener);
		recent.addActionListener(menuBarActionListener);
		userManual.addActionListener(menuBarActionListener);
	}
}