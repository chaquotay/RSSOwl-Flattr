package org.notatoaster.rssowl.flattr.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.notatoaster.rssowl.flattr.ui.preferences.messages"; //$NON-NLS-1$
	public static String FlattrPreferencePage_AUTHORIZE_BUTTON;
	public static String FlattrPreferencePage_MECHANISM;
	public static String FlattrPreferencePage_MECHANISM_API;
	public static String FlattrPreferencePage_MECHANISM_BROWSER;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
