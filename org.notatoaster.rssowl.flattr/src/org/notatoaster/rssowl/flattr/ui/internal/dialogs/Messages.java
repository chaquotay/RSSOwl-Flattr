package org.notatoaster.rssowl.flattr.ui.internal.dialogs;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.notatoaster.rssowl.flattr.ui.internal.dialogs.messages"; //$NON-NLS-1$
	public static String AuthorizationDialog_AUTH_CODE;
	public static String AuthorizationDialog_OPEN_AUTH_WEBSITE;
	public static String AuthorizationDialog_STEP1;
	public static String AuthorizationDialog_STEP2;
	public static String AuthorizationDialog_TITLE;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
