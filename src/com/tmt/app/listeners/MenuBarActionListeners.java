package com.tmt.app.listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.app.ui.MenuBar;
import com.tmt.app.ui.dialog.JFontChooser;
import com.tmt.app.ui.dialog.UploadDialog;
import com.tmt.util.Utility;

public class MenuBarActionListeners implements ActionListener {
	private final MenuBar menuBar;

	public MenuBarActionListeners(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (menuBar.upload.isArmed()) {
			UploadDialog uploadDialog = new UploadDialog();
			JOptionPane optionPane = new JOptionPane();
			JDialog dialog = optionPane.createDialog("Upload file dialog");
			dialog.setSize(385, 135);
			uploadDialog.setDialog(dialog);
			dialog.setContentPane(uploadDialog);
			dialog.setVisible(true);
			
			System.out.println("upload");
		}

		if (menuBar.userManual.isArmed()) {
			System.out.println("userManual");
		}

		if (menuBar.recent.isArmed()) {
			System.out.println("recent");
		}

		if (menuBar.fontSettings.isArmed()) {
			MainFrameDesign mainFrameDesign = Utility.getMainFrameDesign();
			Font font = JFontChooser.showDialog((Component) null, "Font Dialog");
			if (font != null) {
				mainFrameDesign.inputEditor.setFont(new Font(null, font.getStyle(), font.getSize()));
				mainFrameDesign.outputEditor.setFont(new Font(null, font.getStyle(), font.getSize()));
			}
		}
	}
}
