package org.notatoaster.rssowl.flattr.interpreter;

import java.net.URI;

import org.jdom.Attribute;
import org.jdom.Element;
import org.rssowl.core.interpreter.INamespaceHandler;
import org.rssowl.core.persist.IEntity;
import org.rssowl.core.persist.IPersistable;
import org.rssowl.core.util.URIUtils;

public class AtomPaymentNamespaceHandler implements INamespaceHandler {

	@Override
	public void processElement(Element element, IPersistable type) {
		if("link".equals(element.getName())) { //$NON-NLS-1$
			String rel = element.getAttributeValue("rel"); //$NON-NLS-1$
			if("payment".equals(rel)) { //$NON-NLS-1$
				String title = element.getAttributeValue("title", "Flattr this!"); //$NON-NLS-1$
				String href = element.getAttributeValue("href"); //$NON-NLS-1$
				URI uri = URIUtils.createURI(href);
				if(uri != null) {
					if(type instanceof IEntity) {
						IEntity feed = (IEntity)type;
						feed.setProperty("payment-title", title); //$NON-NLS-1$
						feed.setProperty("payment-uri", href); //$NON-NLS-1$
					}
				}
			}
		}
	}

	@Override
	public void processAttribute(Attribute attribute, IPersistable type) {
		
	}

}
