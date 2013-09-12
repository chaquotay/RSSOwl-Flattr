package org.notatoaster.rssowl.flattr.internal;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.notatoaster.rssowl.lib.flattr.Flattr;

public class FlattrFactory {

	private FlattrPreference fPreference;
	private Shell fShell;
	
	public FlattrFactory(FlattrPreference preference) {
		fPreference = preference;
	}
		
	public IFlattr getFlattr() {
		if(fPreference.getMechanism()==FlattrMechanism.Browser)
			return new BrowserFlattr();
		
		if(fPreference.getMechanism()==FlattrMechanism.API) {
			Flattr nativeFlattr = Flattr.create(fPreference.getAccessToken()); 
			return new APIFlattr(nativeFlattr, getShell());
		}
		
		return new UnconfiguredFlattr(getShell());
	}
	
	private Shell getShell() {
		if(fShell!=null)
			return fShell;
		
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}
	
	public void setShell(Shell shell) {
		fShell = shell;
	}
	
}
