package com.tmt.app.ui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import com.tmt.app.ui.IDesign;
import com.tmt.app.ui.dialog.FontChooserDialog.DisposeOnClose;
import com.tmt.util.ComponentUtils;

public class UploadDialog extends JPanel implements IDesign, ActionListener {
	private static final long serialVersionUID = 1L;

	private JFileChooser fileChooser;
	private JButton upload, cancel, browse;
	private JDialog dialog;

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
			fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int r = fileChooser.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION) {
				fileChooser.getSelectedFile().getAbsolutePath();
			}
		}

		if (ae.getSource() == upload) {
			
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
