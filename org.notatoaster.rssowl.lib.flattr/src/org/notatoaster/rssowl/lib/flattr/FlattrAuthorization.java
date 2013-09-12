package org.notatoaster.rssowl.lib.flattr;

import java.net.URI;
import java.util.EnumSet;

import org.shredzone.flattr4j.oauth.AccessToken;
import org.shredzone.flattr4j.oauth.FlattrAuthenticator;
import org.shredzone.flattr4j.oauth.Scope;

public class FlattrAuthorization {
	
	private static final String CALLBACK_URL = "http://chaquotay.github.io/RSSOwl-Flattr/rssowl-flattr-oauth.html";
	private FlattrAuthenticator auth;

	public FlattrAuthorization() {
		auth = new FlattrAuthenticator(FlattrApplicationConfig.Key);
		auth.setCallbackUrl(CALLBACK_URL);
		auth.setScope(EnumSet.of(Scope.FLATTR));
	}
	
	public URI authenticate() throws Exception {
		String url = auth.authenticate();
		return new URI(url);
	}
	
	public String getAccessToken(String code) throws Exception {
		AccessToken token = auth.fetchAccessToken(code);
		if(token!=null)
			return token.getToken();
		
		return null;
	}

}


