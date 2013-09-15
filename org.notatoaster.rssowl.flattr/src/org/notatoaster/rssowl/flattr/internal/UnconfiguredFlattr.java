package org.notatoaster.rssowl.flattr.internal;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.notatoaster.rssowl.flattr.ui.internal.util.MessageBoxUtils;
import org.notatoaster.rssowl.flattr.ui.preferences.FlattrPreferencePage;

public class UnconfiguredFlattr implements IFlattr {

	private Shell fShell;

	public UnconfiguredFlattr(Shell shell) {
		this.fShell = shell;
	}
	
	@Override
	public void flattrThing(String thingUrl) {
		if(MessageBoxUtils.askYesNoQuestion(fShell, Messages.FlattrCommon_MSG_NOT_CONFIGURED_OPEN_PREF))
		{
			PreferencesUtil.createPreferenceDialogOn(fShell, FlattrPreferencePage.ID, null, null).open();
		}
	}

}
