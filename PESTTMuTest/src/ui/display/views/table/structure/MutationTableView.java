package ui.display.views.table.structure;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;

import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.mutation.Mutation;

import ui.constants.TableViewers;

/**
 * 
 * @author sheilla
 * 
 */
public class MutationTableView extends AbstractTableViewer implements Observer {

	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer mutantTableViewer;

	public MutationTableView(Composite parent, IWorkbenchPartSite site) {
		this.parent = parent;
		this.site = site;
		Activator.getDefault().addObserverOperatorsController(this);
		Activator.getDefault().addObserverGroundStringController(this);
	}

	public TableViewer create() {
		mutantTableViewer = createViewTable(parent, site,
				TableViewers.MUTATIONTABLE);
		createColumnsToMutantViewer();
		return mutantTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		if ((arg0 instanceof GroundStringController || arg0 instanceof MutationOperatorsController)
				&& mutantTableViewer.getElementAt(0) != null) {

			mutantTableViewer.remove(mutantTableViewer.getInput());
		}

		if (arg0 instanceof MutationOperatorsController
				&& Activator.getDefault().getSelectedIMutOperator() != null) {
			List<Mutation> list = Activator.getDefault().getMutations();
			mutantTableViewer.setInput(list);
			editTableStyle(mutantTableViewer);
		}

	}

	public void createColumnsToMutantViewer() {
		TableViewerColumn col = createColumnsHeaders(mutantTableViewer,
				TableViewers.COLUMN_MUTANT_TABLE, TableViewers.COLUMN_WIDTH);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}
		});
	}

	public void removeELement() {
		mutantTableViewer.remove(mutantTableViewer.getInput());
	}
}
