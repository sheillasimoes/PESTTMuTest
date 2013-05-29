/**
 * 
 */
package ui.display.views.table.structure;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.TableViewers;

/**
 * @author sheilla
 * 
 */
public class OpMutationApplicable extends AbstractTableViewer implements
		Observer {
	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer OpMutationApplTableViewer;

	public OpMutationApplicable(Composite parent, IWorkbenchPartSite site) {
		this.parent = parent;
		this.site = site;

	}

	public TableViewer create() {
		OpMutationApplTableViewer = createViewTable(parent, site,
				TableViewers.OPMUTATIONAPPLTABLE);
		createColumnsToMutationViewer();
		return OpMutationApplTableViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public void createColumnsToMutationViewer() {
		String columnNames = TableViewers.COLUMN_OP_MUTATION_APPL_TABLE; // the
																			// names
																			// of
																			// column.
		int columnWidths = 700; // the width of column.
		TableViewerColumn col = createColumnsHeaders(OpMutationApplTableViewer,
				columnNames, columnWidths);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				String str = (String) cell.getElement();
				cell.setText(str);
			}
		});
	}

}
