package ui.display.views.table.structure;

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
			return new OpMutationApplicableView(parent, site).create();
		case MUTATIONTABLE:
			return new MutationTableView(parent, site).create();
		default:
			return null;
		}
	}
}
