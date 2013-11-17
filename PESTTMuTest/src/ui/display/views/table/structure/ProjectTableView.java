package ui.display.views.table.structure;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.TableViewers;

public class ProjectTableView extends AbstractTableViewer implements Observer {
	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer projectTableViewer;

	public ProjectTableView(Composite parent, IWorkbenchPartSite site) {
		this.parent = parent;
		this.site = site;
		Activator.getDefault().addObserverCopyProject(this);
	}

	public TableViewer create() {
		projectTableViewer = createViewTable(parent, site,
				TableViewers.PROJECTTABLE);
		createColumnsToProjectViewer();
		projectTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						Activator.getDefault().verifyChangesOperators();
						IStructuredSelection selection = (IStructuredSelection) projectTableViewer
								.getSelection();
						Activator.getDefault().setProjectSelected(
								(String) selection.getFirstElement());
					}
				});
		return projectTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		List<String> list = Activator.getDefault().getProjectNames();

		if (list.size() == 0 && projectTableViewer.getElementAt(0) != null) {
			Object allElements = projectTableViewer.getInput();
			projectTableViewer.remove(allElements);
		} else if (list.size() > 0) {
			projectTableViewer.setInput(list);
			editTableStyle(projectTableViewer);
		}
	}

	public void dispose() {
		Activator.getDefault().deleteObserverCopyProject(this);
		projectTableViewer.getControl().dispose();
	}

	private void createColumnsToProjectViewer() {
		TableViewerColumn col = createColumnsHeaders(projectTableViewer,
				TableViewers.COLUMN_PROJECT_NAME, TableViewers.COLUMN_WIDTH);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}
		});
	}
}
