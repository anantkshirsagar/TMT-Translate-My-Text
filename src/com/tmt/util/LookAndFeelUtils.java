package com.tmt.util;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.tmt.constants.LookAndFeelType;

/**
 * This class sets look and feel to window
 * 
 */
public class LookAndFeelUtils {
	public static void setLookAndFeel(LookAndFeelType type) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		String typeClass = null;
		switch (type) {
		case NIMBUS:
			typeClass = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
			break;
		case WINDOWS:
			typeClass = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			break;
		case MOTIF:
			typeClass = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			break;
		case METAL:
			typeClass = "javax.swing.plaf.metal.MetalLookAndFeel";
			break;
		case WINDOWS_CLASSIC:
			typeClass = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
		}
		UIManager.setLookAndFeel(typeClass);
	}
}
