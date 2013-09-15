package org.notatoaster.rssowl.flattr.ui.internal.actions;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.notatoaster.rssowl.flattr.internal.FlattrFactory;
import org.notatoaster.rssowl.flattr.internal.FlattrPreference;
import org.notatoaster.rssowl.flattr.internal.IFlattr;
import org.rssowl.core.persist.IEntity;
import org.rssowl.core.util.StringUtils;
import org.rssowl.ui.internal.Activator;

public class FlattrAction extends Action implements IWorkbenchWindowActionDelegate, IObjectActionDelegate  {

	/** Action ID */
	public static final String ID = "org.notatoaster.rssowl.flattr.ui.internal.actions.FlattrAction"; //$NON-NLS-1$

	private IStructuredSelection fSelection;
	private Shell fShell;

	/** Default Constructor for Reflection */
	public FlattrAction() {
		this(StructuredSelection.EMPTY);
	}

	/**
	 * @param selection
	 */
	public FlattrAction(IStructuredSelection selection) {
		fSelection = selection;
		setText("Flattr this!");
		setId(ID);
		setActionDefinitionId(ID);
	}

	/*
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {}

	/*
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		if(window!=null)
			fShell = window.getShell();
	}

	/*
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/*
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		try {
			IFlattr f = getFlattr();
			internalRun(f);
		} catch (Exception e) {
			Activator.getDefault().getLog().log(Activator.getDefault().createErrorStatus(e.getMessage(), e));
		}
	}

	private IFlattr getFlattr() {
		FlattrPreference fp = FlattrPreference.getDefault();
		FlattrFactory ff = new FlattrFactory(fp);
		ff.setShell(getShell());
		IFlattr f = ff.getFlattr();
		return f;
	}

	private void internalRun(IFlattr flattr) throws URISyntaxException, MalformedURLException {
		List<?> selection = fSelection.toList();
		for (Object object : selection) {
			if (object instanceof IEntity) {
				IEntity news = (IEntity) object;
				String href = (String)news.getProperty("payment-uri"); //$NON-NLS-1$
				if(StringUtils.isSet(href))
					flattr.flattrThing(href);
			}
		}
	}
	
	private Shell getShell() {
		return fShell;
	}

	/*
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 * org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {	      
			fSelection = (IStructuredSelection) selection;
			action.setEnabled(isAnythingPayableSelected());
		}
	}
	
	private boolean isAnythingPayableSelected() {
		if(fSelection==null)
			return false;
		
		List<?> selection = fSelection.toList();
		for (Object object : selection) {
			if (object instanceof IEntity) {
				IEntity news = (IEntity) object;
				String href = (String)news.getProperty("payment-uri"); //$NON-NLS-1$
				if(StringUtils.isSet(href))
					return true;
			}
		}
		return false;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		if(targetPart!=null) {
			fShell = targetPart.getSite().getShell();
		}
	}
}