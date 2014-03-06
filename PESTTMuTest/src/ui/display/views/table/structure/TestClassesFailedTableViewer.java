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

public class TestClassesFailedTableViewer extends AbstractTableViewer implements
		Observer {
	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer testClassesFailed;

	public TestClassesFailedTableViewer(Composite parent,
			IWorkbenchPartSite site) {
		super();
		this.parent = parent;
		this.site = site;
		Activator.getDefault().getMutationsController().addObserver(this);
	}

	public TableViewer create() {
		testClassesFailed = createViewTable(parent, site,
				TableViewers.TESTCLASSESFAILEDTABLE);
		createColumnsToTestClassesFailed();
		return testClassesFailed;
	}

	@Override
	public void update(Observable o, Object arg) {
		Mutation mutation = Activator.getDefault().getMutationsController()
				.getSelectedMutation();

		if (mutation == null && testClassesFailed.getElementAt(0) != null) {
			testClassesFailed.remove(testClassesFailed.getInput());
		} else if (mutation != null) {
			testClassesFailed.setInput(Activator.getDefault()
					.getMutationsController().getFailedTests(mutation));

			editTableStyle(testClassesFailed);
		}
	}

	private void createColumnsToTestClassesFailed() {
		TableViewerColumn col = createColumnsHeaders(testClassesFailed,
				TableViewers.COLUMN_NAME_TEST_CLASSES,
				TableViewers.COLUMN_WIDTH);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}
		});
	}

}
