package org.notatoaster.rssowl.flattr.ui.internal.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.notatoaster.rssowl.flattr.Activator;
import org.notatoaster.rssowl.flattr.ui.internal.util.WebBrowserUtils;
import org.notatoaster.rssowl.lib.flattr.FlattrAuthorization;
import org.rssowl.core.util.StringUtils;

public class AuthorizationDialog extends Dialog {

	private FlattrAuthorization fFlattr;
	private String fCode = null;

	public AuthorizationDialog(Shell parentShell, FlattrAuthorization flattr) {
		super(parentShell);
		fFlattr = flattr;
	}

	public String getCode() {
		return fCode;
	}

	private GridData horizontalSpan(int span) {
		GridData gd = new GridData();
		gd.horizontalSpan = span;
		return gd;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite c = (Composite) super.createDialogArea(parent);
		c.setLayout(new GridLayout(2, false));

		Label step1 = new Label(c, SWT.NONE);
		step1.setText("Step 1: Initiate Flattr authorization");
		step1.setFont(JFaceResources.getFontRegistry().getBold(""));
		step1.setLayoutData(horizontalSpan(2));

		Link btnStartOAuth = new Link(c, SWT.NONE);
		btnStartOAuth.setText("<a>Open Flattr authorization website in browser...</a>");
		btnStartOAuth.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					WebBrowserUtils.openURL(fFlattr.authenticate().toURL());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnStartOAuth.setLayoutData(horizontalSpan(2));

		Label step2= new Label(c, SWT.NONE);
		step2.setText("Step 2: Confirm Flattr authorization");
		step2.setFont(JFaceResources.getFontRegistry().getBold(""));
		step2.setLayoutData(horizontalSpan(2));


		Label lblCode = new Label(c, SWT.NONE);
		lblCode.setText("Flattr authorization code:");

		final Text txtCode = new Text(c, SWT.BORDER);
		GridData gd = new GridData();
		gd.minimumWidth = 200;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		txtCode.setLayoutData(gd);

		txtCode.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				fCode = txtCode.getText();
				getButton(IDialogConstants.OK_ID).setEnabled(StringUtils.isSet(fCode));
			}});

		return c;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Flattr Authorization");
		newShell.setSize(550, 200);
		newShell.setImage(Activator.getImageDescriptor("icons/flattr-icon.png").createImage(true));
	}



}
