package ui.display.views.table.structure;

import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;

import domain.mutation.Mutation;
import ui.constants.TableViewers;

public class AnalyseMutantsTableViewer extends AbstractTableViewer implements
		Observer {
	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer analyseMutantTableViewer;

	public AnalyseMutantsTableViewer(Composite parent, IWorkbenchPartSite site) {
		super();
		this.parent = parent;
		this.site = site;
		// Activator.getDefault().addObserverResultMutation(this);
		Activator.getDefault().addObserverGroundString(this);
	}

	public TableViewer create() {
		analyseMutantTableViewer = createViewTable(parent, site,
				TableViewers.ANALYSEMUTANTS);
		createColumnsToMutantViewer();
		return analyseMutantTableViewer;
	}

	@Override
	public void update(Observable o, Object arg) {

		System.out.println("boolean " + o.toString());
	}

	private void createColumnsToMutantViewer() {
		String[] columnNames = new String[] { TableViewers.COLUMN_MUTANT,
				TableViewers.COLUMN_STATUS,
				TableViewers.COLUMN_FULLY_QUALIFIED_NAME,
				TableViewers.COLUMN_PROJECT_NAME }; // the names of
													// columns.
		int[] columnWidths = new int[] { 200, 60, 215, 150 }; // the width of
																// columns.

		// first column is for the mutant.
		TableViewerColumn col = createColumnsHeaders(analyseMutantTableViewer,
				columnNames[0], columnWidths[0]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}

		});

		// second column is for status
		col = createColumnsHeaders(analyseMutantTableViewer, columnNames[1],
				columnWidths[1]);
		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setImage(null);
				super.update(cell);
			}

		});

		// third column is for the fully qualified name
		col = createColumnsHeaders(analyseMutantTableViewer, columnNames[2],
				columnWidths[2]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				cell.setText(Activator.getDefault().getFullyQualifiedName(
						mutation.getASTNode()));
				super.update(cell);
			}

		});

		// third column is for the projet name
		col = createColumnsHeaders(analyseMutantTableViewer, columnNames[3],
				columnWidths[3]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				cell.setText(Activator.getDefault().getProjectName(
						mutation.getASTNode()));
				super.update(cell);
			}

		});

	}

	// @Override
	// protected void editTableStyle(TableViewer tableViewer) {
	// // TODO Auto-generated method stub
	// super.editTableStyle(tableViewer);
	// }

	public void dispose() {
		Activator.getDefault().deleteObserverGroundString(this);
		// Activator.getDefault().deleteObserverResultMutation(this);
		analyseMutantTableViewer.getControl().dispose();
	}

}
