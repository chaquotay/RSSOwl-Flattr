package org.notatoaster.rssowl.flattr.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.notatoaster.rssowl.flattr.internal.messages"; //$NON-NLS-1$
	public static String FlattrCommon_MSG_ERROR;
	public static String FlattrCommon_MSG_NOT_AUTHORIZED_OPEN_PREF;
	public static String FlattrCommon_MSG_NOT_CONFIGURED_OPEN_PREF;
	public static String FlattrCommon_MSG_SUCCESS;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
