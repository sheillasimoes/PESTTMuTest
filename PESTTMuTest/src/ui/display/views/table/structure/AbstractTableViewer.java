package ui.display.views.table.structure;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.TableViewers;

/**
 * Configura a estrutura basica das tabelas
 * 
 * @author sheilla
 * 
 */
public abstract class AbstractTableViewer {

	protected TableViewer createViewTable(Composite parent,
			IWorkbenchPartSite site, TableViewers id) {
		TableViewer tableViewer = null;
		switch (id) {
		case GROUNDSTRINGTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			break;
		case OPMUTATIONAPPLTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			break;
		case MUTANTTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			break;

		}

		// configure the table for display
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(33, true));
		Table table = tableViewer.getTable(); // create the table.
		table.setLayout(layout);
		table.setHeaderVisible(true); // show header.
		table.setLinesVisible(true); // show table lines.
		tableViewer.setContentProvider(new ArrayContentProvider()); // set the
																	// content
																	// provider.
		site.setSelectionProvider(tableViewer); // Make the selection available
												// to other views.
		return tableViewer;
	}

	public TableViewerColumn createColumnsHeaders(TableViewer viewer,
			String columnName, int columnWidth) {

		TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.CENTER); // the
								// columns
								// style.

		final TableColumn column = viewerColumn.getColumn(); // get the column.
		column.setText(columnName); // set the column title.
		column.setWidth(columnWidth); // set the column width.

		return viewerColumn;
	}
}
