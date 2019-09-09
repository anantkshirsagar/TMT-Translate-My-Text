package com.tmt.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tmt.app.ui.TMTMenuBar;

public class MenuBarActionListeners implements ActionListener {
	private final TMTMenuBar menuBar;

	public MenuBarActionListeners(TMTMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuBar.upload.isArmed()) {
			System.out.println("upload");
		}

		if (menuBar.userManual.isArmed()) {
			System.out.println("userManual");
		}

		if (menuBar.recent.isArmed()) {
			System.out.println("recent");
		}
	}
}
