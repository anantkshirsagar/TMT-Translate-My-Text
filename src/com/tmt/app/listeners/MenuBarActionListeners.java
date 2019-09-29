package com.tmt.app.listeners;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.app.ui.MainFrameDesign;
import com.tmt.app.ui.MenuBar;
import com.tmt.app.ui.RecentFrame;
import com.tmt.app.ui.dialog.JFontChooser;
import com.tmt.app.ui.dialog.UploadDialog;
import com.tmt.util.Utility;

public class MenuBarActionListeners implements ActionListener {

	private static final Logger LOG = LoggerFactory.getLogger(MenuBarActionListeners.class);
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
			JOptionPane.showMessageDialog(null, "Upload successful");
		}

		if (menuBar.userManual.isArmed()) {
			LOG.debug("User manual");
		}

		if (menuBar.recent.isArmed()) {
			RecentFrame recentFrame = new RecentFrame();
		}

		if (menuBar.fontSettings.isArmed()) {
			LOG.debug("Initializing font dialog...");
			MainFrameDesign mainFrameDesign = Utility.getMainFrameDesign();
			Font font = JFontChooser.showDialog((Component) null, "Font Dialog");
			if (font != null) {
				mainFrameDesign.inputEditor.setFont(new Font(null, font.getStyle(), font.getSize()));
				mainFrameDesign.outputEditor.setFont(new Font(null, font.getStyle(), font.getSize()));
			}
		}
	}
}
