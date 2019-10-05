package com.tmt.app.listeners;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		this.setHorizontalAlignment(JLabel.CENTER);
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		cell.setBackground(new Color(102, 178, 255));
		cell.setForeground(Color.white);
		return cell;
	}
}