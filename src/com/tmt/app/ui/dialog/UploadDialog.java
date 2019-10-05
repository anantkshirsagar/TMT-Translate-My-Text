package com.tmt.app.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.app.ui.IDesign;
import com.tmt.constants.Resources;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class UploadDialog extends JPanel implements IDesign, ActionListener {

	private static final Logger LOG = LoggerFactory.getLogger(UploadDialog.class);
	private static final long serialVersionUID = 1L;

	private JButton upload, cancel, browse;
	private JDialog dialog;
	private String filePath;

	public UploadDialog() {
		setLayout(null);
		setSize(new Dimension(500, 200));
		setBorder(ComponentUtils.getBorder("File upload"));
		init();
		addItems();
		addActionListeners();
	}

	@Override
	public void init() {
		browse = new JButton("Choose File");
		browse.setBounds(30, 40, 100, 30);

		upload = new JButton("Upload");
		upload.setBounds(140, 40, 100, 30);

		cancel = new JButton("Cancel");
		cancel.setBounds(250, 40, 100, 30);
	}

	@Override
	public void addItems() {
		add(browse);
		add(upload);
		add(cancel);
	}

	public void addActionListeners() {
		browse.addActionListener(this);
		upload.addActionListener(this);
		cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == browse) {
			LOG.debug("Browser action");
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Files", Resources.TXT);
			fileChooser.setFileFilter(fileFilter);

			int r = fileChooser.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				filePath = fileChooser.getSelectedFile().getAbsolutePath();
				LOG.debug("Absolute file path {}", filePath);
			}
		}

		if (ae.getSource() == upload) {
			if (filePath == null) {
				JOptionPane.showMessageDialog(null, "Please select target folder!", Resources.ERROR_TITLE,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			String fileContent = null;
			try {
				fileContent = ComponentUtils.getFileContents(filePath);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Exception: " + e, Resources.ERROR_TITLE,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (fileContent == null || fileContent.isEmpty()) {
				JOptionPane.showMessageDialog(null, "File does not contains data!", Resources.ERROR_TITLE,
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Utility.getMainFrameDesign().inputEditor.setText(fileContent);
			JOptionPane.showMessageDialog(null, "Upload successful", Resources.INFO_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
			Utility.getMainFrameDesign().convert.setEnabled(true);
			getDialog().dispose();
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
}
