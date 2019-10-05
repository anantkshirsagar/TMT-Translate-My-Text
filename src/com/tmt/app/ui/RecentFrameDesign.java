package com.tmt.app.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmt.app.listeners.TableCellRenderer;
import com.tmt.constants.Resources;
import com.tmt.constants.UIConstants;
import com.tmt.model.DownloadEntity;
import com.tmt.model.TranslationEntity;
import com.tmt.service.DataService;
import com.tmt.util.ComponentUtils;
import com.tmt.util.Utility;

public class RecentFrameDesign extends JPanel implements IDesign {

	private static final Logger LOG = LoggerFactory.getLogger(RecentFrameDesign.class);
	private static final long serialVersionUID = 1L;
	private JTable recentRecordTable;
	private JScrollPane tableScrollPane;

	@Override
	public void init() {
		DefaultTableModel tableModel = new DefaultTableModel();
		recentRecordTable = new JTable(tableModel);

		addTableHeader(tableModel);
		addTableData(tableModel);
		setTableHeaderFont();
		setCellFont();
		setCellAlignment(JLabel.CENTER, tableModel);
		recentRecordTable.getColumnModel().getColumn(0).setMaxWidth(60);
		recentRecordTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		TableColumn tableColumn = recentRecordTable.getColumnModel().getColumn(4);
		tableColumn.setCellRenderer(new TableCellRenderer());

		addActionEvent();

		tableScrollPane = new JScrollPane(recentRecordTable);
		tableScrollPane.setPreferredSize(new Dimension(1150, 600));
	}

	@Override
	public void addItems() {
		add(tableScrollPane);
	}

	public void addTableHeader(DefaultTableModel tableModel) {
		for (int i = 0; i < UIConstants.COLUMN_NAMES.length; ++i) {
			tableModel.addColumn(UIConstants.COLUMN_NAMES[i]);
		}
	}

	public void addTableData(DefaultTableModel tableModel) {
		LOG.debug("addableData");
		DataService dataService = new DataService();
		List<TranslationEntity> translationEntities = null;
		try {
			translationEntities = dataService.getTranslationEntities();
		} catch (ClassNotFoundException | SQLException e) {
			LOG.error("Data not found {}", e);
			JOptionPane.showMessageDialog(null, "Data not available", Resources.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (translationEntities == null) {
			JOptionPane.showMessageDialog(null, "Data not available", Resources.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}

		Map<Integer, TranslationEntity> dataMap = new HashMap<Integer, TranslationEntity>();
		int i = 0;
		Map<String, String> languageMap = Utility.getLanguages().getLanguageMap();
		SimpleDateFormat dateFormat = new SimpleDateFormat(UIConstants.DATE_FORMAT);

		for (TranslationEntity translationEntity : translationEntities) {
			String sourceLangCode = translationEntity.getSourceLanguage();
			String sourceLang = ComponentUtils.getKey(languageMap, sourceLangCode);
			String targetLangCode = translationEntity.getTargetLanguage();
			String targetLang = ComponentUtils.getKey(languageMap, targetLangCode);

			String formattedDate = dateFormat.format(translationEntity.getCreationDate());
			Object[] entity = { String.valueOf(i + 1), formattedDate, sourceLang, targetLang, Resources.DOWNLOAD };
			tableModel.addRow(entity);
			dataMap.put(i, translationEntity);
			++i;
		}
		Utility.setDataMap(dataMap);
	}

	public void addActionEvent() {
		recentRecordTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = recentRecordTable.rowAtPoint(evt.getPoint());
				int col = recentRecordTable.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					DownloadEntity downloadEntity = ComponentUtils.getDownloadEntity(Utility.getDataMap().get(row));
					LOG.debug("Download entity {}", downloadEntity);
					ComponentUtils.showDialog(downloadEntity);
					LOG.debug("row[{}], column[{}]", row, col);
				}
			}
		});
	}

	public void setTableHeaderFont() {
		JTableHeader tableHeader = recentRecordTable.getTableHeader();
		tableHeader.setFont(new Font(null, Font.BOLD, 14));
	}

	public void setCellFont() {
		recentRecordTable.setFont(new Font(null, Font.PLAIN, 14));
		recentRecordTable.setRowHeight(30);
	}

	public void setCellAlignment(int alignment, DefaultTableModel tableModel) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(alignment);
		for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
			recentRecordTable.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
		}
	}
}
