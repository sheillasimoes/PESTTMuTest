package ui.display.views.table.structure;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.Colors;
import ui.constants.TableViewers;

/**
 * Configura a estrutura basica das tabelas
 * 
 * @author sheilla
 * 
 */
public abstract class AbstractTableViewer {

	/**
	 * 
	 * @param parent
	 * @param site
	 * @param id
	 * @return
	 */
	protected TableViewer createViewTable(Composite parent,
			IWorkbenchPartSite site, TableViewers id) {
		TableViewer tableViewer = null;
		switch (id) {
		case GROUNDSTRINGTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
			break;
		case MUTATIONOPAPPLTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			break;
		case MUTANTSTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			break;
		case ANALYSEMUTANTS:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
			break;
		case PROJECTTABLE:
			tableViewer = new TableViewer(parent, SWT.NONE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
			break;
		default:
			break;

		}

		Table table = tableViewer.getTable(); // create the table.
		table.setToolTipText("");
		table.setHeaderVisible(true); // show header.
		table.setLinesVisible(true); // show table lines.
		tableViewer.setContentProvider(new ArrayContentProvider()); // set the
																	// content
																	// provider.
		site.setSelectionProvider(tableViewer); // Make the selection available
												// to other views.

		return tableViewer;
	}

	/**
	 * 
	 * @param viewer
	 * @param columnName
	 * @param columnWidth
	 * @return
	 */
	public TableViewerColumn createColumnsHeaders(TableViewer viewer,
			String columnName, int columnWidth) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
				SWT.NONE); // the columns style.
		final TableColumn column = viewerColumn.getColumn(); // get the column
		column.setText(columnName); // set the column title.
		column.setWidth(columnWidth); // set the column width.
		column.setAlignment(SWT.LEAD); // set the column alignment.
		column.setResizable(true); // set the column to be resizable.
		column.setMoveable(true); // set the column to be moveable.
		return viewerColumn;
	}

	/**
	 * Edit table style
	 * 
	 * @param tableViewer
	 */
	protected void editTableStyle(TableViewer tableViewer) {
		int n = 0;
		for (TableItem item : tableViewer.getTable().getItems()) {
			item.setImage(1, null);
			if (n % 2 == 0)
				item.setBackground(Colors.WHITE);
			else
				item.setBackground(Colors.GREY);

			n++;
		}
	}

}
