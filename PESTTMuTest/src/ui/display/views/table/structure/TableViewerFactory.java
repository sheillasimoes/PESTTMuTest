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
			return new GroundStringTableViewer(parent, site).create();
		case MUTATIONOPAPPLTABLE:
			return new MutationOpApplicableTableViewer(parent, site).create();
		case MUTANTSTABLE:
			return new MutantsTableViewer(parent, site).create();
		case ANALYSEMUTANTSTABLE:
			return new AnalyseMutantsTableViewer(parent, site).create();
		case TESTCLASSESFAILEDTABLE:
			return new TestClassesFailedTableViewer(parent, site).create();
		case PROJECTTABLE:
			return new ProjectTableViewer(parent, site).create();
		default:
			return null;
		}
	}
}
