package org.notatoaster.rssowl.flattr.internal;

import org.eclipse.jface.preference.IPreferenceStore;
import org.notatoaster.rssowl.flattr.Activator;

public class FlattrPreference {

	private static final String P_MECHANISM = "flattrMechanism"; //$NON-NLS-1$
	private static final String P_TOKEN = "flattrOAuthAccessToken"; //$NON-NLS-1$
	
	private IPreferenceStore fStore;
	
	public FlattrPreference(IPreferenceStore store) {
		fStore = store;
	}
	
	public FlattrMechanism getMechanism() {
		String m = fStore.getString(P_MECHANISM);
		return FlattrMechanism.fromPrefValue(m);
	}
	
	public void setMechanism(FlattrMechanism mechanism) {
		fStore.setValue(P_MECHANISM, mechanism.toPrefValue());
	}
	
	public String getAccessToken() {
		return fStore.getString(P_TOKEN);
	}
	
	public void setAccessToken(String token) {
		fStore.setValue(P_TOKEN, token);
	}
	
	public void initDefaults() {
		fStore.setDefault(P_MECHANISM, FlattrMechanism.Browser.toPrefValue());
		fStore.setDefault(P_TOKEN, "");  //$NON-NLS-1$
	}
	
	public static FlattrPreference getDefault() {
		return new FlattrPreference(Activator.getDefault().getPreferenceStore());
	}
	
}
