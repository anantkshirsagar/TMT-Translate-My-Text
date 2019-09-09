package com.tmt.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tmt.app.ui.MainFrameDesign;

public class MainFrameActionListener implements ActionListener {

	private final MainFrameDesign mainFrameDesign;

	public MainFrameActionListener(MainFrameDesign mainFrameDesign) {
		this.mainFrameDesign = mainFrameDesign;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == mainFrameDesign.inputClear) {
			System.out.println("input clear");
		}

		if (ae.getSource() == mainFrameDesign.outputClear) {

		}

		if (ae.getSource() == mainFrameDesign.save) {

		}

		if (ae.getSource() == mainFrameDesign.inputCopy) {

		}

		if (ae.getSource() == mainFrameDesign.outputCopy) {

		}

		if (ae.getSource() == mainFrameDesign.download) {

		}

		if (ae.getSource() == mainFrameDesign.convert) {

		}
	}

}
