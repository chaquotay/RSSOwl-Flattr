package org.notatoaster.rssowl.lib.flattr;

import org.shredzone.flattr4j.FlattrService;
import org.shredzone.flattr4j.exception.FlattrException;
import org.shredzone.flattr4j.exception.FlattrServiceException;
import org.shredzone.flattr4j.oauth.AccessToken;
import org.shredzone.flattr4j.oauth.ConsumerKey;
import org.shredzone.flattr4j.spring.DefaultFlattrServiceFactory;

public class Flattr {

	private FlattrService fFlattrService;

	public Flattr(ConsumerKey consumerKey, AccessToken accessToken) {
		if(accessToken!=null) {
			fFlattrService = new DefaultFlattrServiceFactory(consumerKey, accessToken).getFlattrService();
		}
	}
	
	public FlattrResult flattrThing(String thingUrl) {
		if(fFlattrService==null)
			return FlattrResult.failureBecauseConfigurationIncomplete();
		
		try {
			fFlattrService.click(thingUrl);
			return FlattrResult.success();
		} catch(FlattrServiceException fse) {
			return FlattrResult.failure(fse.getCode());
		} catch(FlattrException fe) {
			return FlattrResult.error(fe);
		} catch(Exception ex) {
			return FlattrResult.error(ex);
		}
	}
	
	public static Flattr create(String accessToken) {
		return new Flattr(FlattrApplicationConfig.Key, new AccessToken(accessToken));
	}
	
}
