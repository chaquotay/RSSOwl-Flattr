package org.notatoaster.rssowl.flattr.ui.internal.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxUtils {

	public static void showError(Shell shell, String message) {
		MessageBox mb = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		  mb.setMessage(message);
		  mb.open();
	}
	
	public static void showInformation(Shell shell, String message) {
		MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
		  mb.setMessage(message);
		  mb.open();
	}
	
	public static boolean askYesNoQuestion(Shell shell, String message) {
		MessageBox mb = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.YES | SWT.NO);
		  mb.setMessage(message);
		  return mb.open()==SWT.YES;
	}
	
}
