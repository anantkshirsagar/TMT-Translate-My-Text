package com.tmt.app.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public interface IFrameProperties {
	default void before(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(1180,650);
		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2,
				screenSize.height / 2 - frame.getSize().height / 2);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	default void after(JFrame frame) {
		frame.setVisible(true);
	}
}
