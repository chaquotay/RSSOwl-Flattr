package org.notatoaster.rssowl.flattr.internal;

public enum FlattrMechanism {
	Unknown (""),
	API ("api"),
	Browser ("browser");
	
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
