package ui.display.views.structural;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.TableViewers;

/**
 * Cria as tabelas que fazem parte da view
 * 
 * @author sheilla
 * 
 */
public enum TableViewerFactory {

	INSTANCE;

	public TableViewer createTableViewer(Composite parent,
			IWorkbenchPartSite site, String name) {
		switch (TableViewers.valueOf(name)) {
		case GROUNDSTRINGTABLE:
			return new GroundStringTableView(parent, site).create();
		case OPMUTATIONAPPLTABLE:
			return new OpMutationApplicable(parent, site).create();
		case MUTANTTABLE:
			return new MutantTableView(parent, site).create();
		default:
			return null;
		}
	}
}
