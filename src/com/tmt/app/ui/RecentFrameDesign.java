package com.tmt.app.ui;

import javax.swing.JPanel;
import javax.swing.JTable;

public class RecentFrameDesign extends JPanel implements IDesign {

	private static final long serialVersionUID = 1L;
	private JTable recentRecordTable;

	@Override
	public void init() {
		recentRecordTable = new JTable();
	}

	@Override
	public void addItems() {
	}

}
