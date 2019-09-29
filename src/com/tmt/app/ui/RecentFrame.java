package com.tmt.app.ui;

import javax.swing.JFrame;

import com.tmt.constants.UIConstants;
import com.tmt.util.Utility;

public class RecentFrame extends JFrame implements IFrameProperties {

	private static final long serialVersionUID = 1L;
	private RecentFrameDesign recentFrameDesign;
	
	public RecentFrame() {
		super(UIConstants.RECENT_FRAME_TITLE);
		before(this);
		
		addCenterPanel();
		Utility.setRecentFrameDesign(recentFrameDesign);
		after(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void addCenterPanel(){
		recentFrameDesign = new RecentFrameDesign();
		this.add(recentFrameDesign);
	}
}
