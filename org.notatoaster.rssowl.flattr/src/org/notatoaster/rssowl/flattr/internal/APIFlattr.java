package org.notatoaster.rssowl.flattr.internal;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.notatoaster.rssowl.flattr.ui.internal.util.MessageBoxUtils;
import org.notatoaster.rssowl.flattr.ui.preferences.FlattrPreferencePage;
import org.notatoaster.rssowl.lib.flattr.Flattr;
import org.notatoaster.rssowl.lib.flattr.FlattrResult;
import org.notatoaster.rssowl.lib.flattr.FlattrResultCode;

public class APIFlattr implements IFlattr {

	private Flattr fNativeFlattr;
	private Shell fShell;
	
	public APIFlattr(Flattr nativeFlattr, Shell shell) {
		fNativeFlattr = nativeFlattr;
		this.fShell = shell;
	}
	
	@Override
	public void flattrThing(String thingUrl) {
		FlattrResult result = fNativeFlattr.flattrThing(thingUrl);
		if(result.isSuccess()) {
			MessageBoxUtils.showInformation(fShell, Messages.FlattrCommon_MSG_SUCCESS);
		} else {
			
			if(result.getCode()==FlattrResultCode.FailureUnauthorized) {
				if(MessageBoxUtils.askYesNoQuestion(fShell, Messages.FlattrCommon_MSG_NOT_AUTHORIZED_OPEN_PREF)) {
					PreferencesUtil.createPreferenceDialogOn(fShell, FlattrPreferencePage.ID, null, null).open();
				}
			} else {
				MessageBoxUtils.showError(fShell, Messages.FlattrCommon_MSG_ERROR + result.getMessage());
			}
		}
	}

}
