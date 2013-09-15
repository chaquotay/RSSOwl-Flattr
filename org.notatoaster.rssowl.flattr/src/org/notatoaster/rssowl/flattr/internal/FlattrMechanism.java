package org.notatoaster.rssowl.flattr.internal;

public enum FlattrMechanism {
	Unknown (""), //$NON-NLS-1$
	API ("api"), //$NON-NLS-1$
	Browser ("browser"); //$NON-NLS-1$
	
	private final String fPrefValue;
	
	FlattrMechanism(String prefValue) {
		fPrefValue = prefValue;
	}
	
	public static FlattrMechanism fromPrefValue(String value) {
		if(API.fPrefValue.equals(value))
			return API;
		
		if(Browser.fPrefValue.equals(value))
			return Browser;
		
		return Unknown;
	}
	
	public String toPrefValue() {
		return fPrefValue;
	}
}
