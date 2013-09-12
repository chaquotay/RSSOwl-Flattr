package org.notatoaster.rssowl.flattr.internal;

import java.net.URI;

import org.notatoaster.rssowl.flattr.ui.internal.util.WebBrowserUtils;
import org.rssowl.core.util.URIUtils;

public class BrowserFlattr implements IFlattr {

	@Override
	public void flattrThing(String thingUrl) {
		try {
			URI link = URIUtils.createURI(thingUrl);
			WebBrowserUtils.openURL(link.toURL());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
