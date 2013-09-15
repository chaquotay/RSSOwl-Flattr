package org.notatoaster.rssowl.flattr.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.notatoaster.rssowl.flattr.Activator;
import org.notatoaster.rssowl.flattr.internal.FlattrMechanism;
import org.notatoaster.rssowl.flattr.internal.FlattrPreference;
import org.notatoaster.rssowl.flattr.ui.internal.dialogs.AuthorizationDialog;
import org.notatoaster.rssowl.lib.flattr.FlattrAuthorization;
import org.rssowl.core.util.StringUtils;

public class FlattrPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public static final String ID = "org.notatoaster.rssowl.flattr.ui.preferences.FlattrPreferencePage"; //$NON-NLS-1$
	private Shell fShell;
	private FlattrAuthorization fFlattr;
	private FlattrPreference fFlattrPref;

	public FlattrPreferencePage() {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setImageDescriptor(Activator.getImageDescriptor("icons/flattr-icon.png")); //$NON-NLS-1$
		fFlattr = Activator.getDefault().getFlattr();
		fFlattrPref = new FlattrPreference(getPreferenceStore());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		fShell = workbench.getActiveWorkbenchWindow().getShell();
	}

	private GridData horizontalSpan(int span) {
		GridData gd = new GridData();
		gd.horizontalSpan = span;
		return gd;
	}

	@Override
	protected Control createContents(Composite parent) {
				Composite c = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		c.setLayout(layout);

		Label heading = new Label(c, SWT.BOLD);
		heading.setFont(JFaceResources.getFontRegistry().getBold("")); //$NON-NLS-1$

		heading.setText(Messages.FlattrPreferencePage_MECHANISM);
		heading.setLayoutData(horizontalSpan(2));

		final Button rdAPI = new Button(c, SWT.RADIO);
		rdAPI.setText(Messages.FlattrPreferencePage_MECHANISM_API);
		rdAPI.setToolTipText(fFlattrPref.getAccessToken());
		final Button btnConfigureAPI = new Button(c, SWT.PUSH);
		btnConfigureAPI.setText(Messages.FlattrPreferencePage_AUTHORIZE_BUTTON);
		rdAPI.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isAPI = rdAPI.getSelection();
				btnConfigureAPI.setEnabled(isAPI);
				if (isAPI)
					fFlattrPref.setMechanism(FlattrMechanism.API);;
			}

		});
		rdAPI.setSelection(fFlattrPref.getMechanism()==FlattrMechanism.API);
		btnConfigureAPI.setEnabled(rdAPI.getSelection());

		btnConfigureAPI.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AuthorizationDialog dlg = new AuthorizationDialog(fShell,
						fFlattr);
				dlg.setBlockOnOpen(true);
				dlg.open();
				String token;
				try {
					String code = dlg.getCode();
					if (StringUtils.isSet(code)) {
						token = fFlattr.getAccessToken(code);
						if (StringUtils.isSet(token)) {
							fFlattrPref.setAccessToken(token);
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		final Button rdBrowser = new Button(c, SWT.RADIO);
		rdBrowser.setText(Messages.FlattrPreferencePage_MECHANISM_BROWSER);
		rdBrowser.setLayoutData(horizontalSpan(2));
		rdBrowser.setSelection(fFlattrPref.getMechanism()==FlattrMechanism.Browser);
		rdBrowser.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isBrowser = rdBrowser.getSelection();
				if (isBrowser)
					fFlattrPref.setMechanism(FlattrMechanism.Browser);
			}

		});

		return c;
	}

}