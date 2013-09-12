package org.notatoaster.rssowl.flattr.ui.internal.util;

import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.notatoaster.rssowl.flattr.Activator;
import org.rssowl.ui.internal.editors.browser.WebBrowserView;

public class WebBrowserUtils {

	public static boolean openURL(URL url) {
		IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
        try {
          IWebBrowser browser = browserSupport.createBrowser(WebBrowserView.EDITOR_ID);
          browser.openURL(url);
          return true;
        } catch (PartInitException e) {
        	// org.rssowl.core.internal.Activator ???
        	Activator.getDefault().getLog().log(e.getStatus());
        }
        return false;
	}
	
}
