package com.tmt.app.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import com.tmt.app.ui.IDesign;
import com.tmt.constants.Resources;
import com.tmt.model.DownloadEntity;
import com.tmt.service.DownloadService;
import com.tmt.util.ComponentUtils;

public class DownloadDialog extends JPanel implements IDesign, ActionListener {

	private static final long serialVersionUID = 1L;
	private DownloadEntity downloadEntity;
	private String folderPath;
	private JFileChooser fileChooser;
	private JButton download, cancel, browse;
	private JDialog dialog;
	private boolean isFromDB;
	private JComboBox<String> extensionComboBox;

	public DownloadDialog(DownloadEntity downloadEntity, boolean isFromDB) {
		this.isFromDB = isFromDB;
		setDownloadEntity(downloadEntity);
		setDialogProperties();
	}

	public DownloadDialog() {
		setDialogProperties();
	}

	public void setDialogProperties() {
		setLayout(null);
		setSize(new Dimension(1000, 200));
		setBorder(ComponentUtils.getBorder("File download"));
		init();
		addItems();
		addActionListeners();
	}

	@Override
	public void init() {
		browse = new JButton("Choose Folder");
		browse.setBounds(30, 40, 130, 30);

		extensionComboBox = new JComboBox<String>(Resources.EXTENSION);
		extensionComboBox.setBounds(170, 40, 100, 30);

		download = new JButton("Download");
		download.setBounds(280, 40, 100, 30);

		cancel = new JButton("Cancel");
		cancel.setBounds(390, 40, 100, 30);
	}

	@Override
	public void addItems() {
		add(browse);
		add(extensionComboBox);
		add(download);
		add(cancel);
	}

	public void addActionListeners() {
		browse.addActionListener(this);
		download.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == browse) {
			fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle("Choose download folder");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				folderPath = fileChooser.getSelectedFile().getAbsolutePath();
				System.out.println(folderPath);
			}
		}

		if (ae.getSource() == download) {
			if (isFromDB) {

			} else {
				System.out.println("In download from UI");
				downloadEntity.setFolderPath(folderPath);
				downloadEntity.setExtension("." + extensionComboBox.getSelectedItem().toString());
				System.out.println(downloadEntity);
				DownloadService downloadService = new DownloadService();
				try {
					downloadService.download(downloadEntity);
					getDialog().dispose();
					JOptionPane.showMessageDialog(null, "File download successfully", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error occured while download", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.out.println(" Exception while downloading: " + e);
				}
			}
		}

		if (ae.getSource() == cancel) {
			getDialog().dispose();
		}
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public DownloadEntity getDownloadEntity() {
		return downloadEntity;
	}

	public void setDownloadEntity(DownloadEntity downloadEntity) {
		this.downloadEntity = downloadEntity;
	}
}
