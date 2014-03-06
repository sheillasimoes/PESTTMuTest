/**
 * 
 */
package ui.display.views.table.structure;

import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPartSite;

import domain.groundString.GroundString;
import domain.groundString.ManagerGroundString;
import domain.projects.ManagerProjects;
import domain.util.ASTUtil;
import ui.constants.TableViewers;

/**
 * 
 * @author Sheilla Simoes
 * 
 */
public class GroundStringTableViewer extends AbstractTableViewer implements
		Observer {

	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer groundStringTableViewer;

	public GroundStringTableViewer(Composite parent, IWorkbenchPartSite site) {
		super();
		this.parent = parent;
		this.site = site;
		Activator.getDefault().getGroundStringController()
				.addObserverGroundString(this);
		Activator.getDefault().getProjectController()
				.addObserverManagerProjects(this);
	}

	public TableViewer create() {
		groundStringTableViewer = createViewTable(parent, site,
				TableViewers.GROUNDSTRINGTABLE);
		createColumnsToGroundString();

		// apresenta a linha no ficheiro onde a ground string
		groundStringTableViewer
				.addDoubleClickListener(new IDoubleClickListener() {

					@Override
					public void doubleClick(DoubleClickEvent event) {
						// IStructuredSelection selection =
						// (IStructuredSelection) groundStringTableViewer
						// .getSelection();
						//
						// ASTNode node = (ASTNode) selection.getFirstElement();
						//
						// CompilationUnit cUnit = (CompilationUnit) node
						// .getRoot();
						// IFile file = (IFile) cUnit.getJavaElement()
						// .getResource();
						// IWorkbenchPage page = PlatformUI.getWorkbench()
						// .getActiveWorkbenchWindow().getActivePage();
						// ITextEditor editor = (ITextEditor)
						// IDE.openEditor(page,
						// file);
						// editor.selectAndReveal(offset, length);
					}
				});

		// apresenta os operadores de mutação que podem ser aplicados
		groundStringTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						Activator.getDefault().verifyChangesOperators();
						IStructuredSelection selection = (IStructuredSelection) groundStringTableViewer
								.getSelection();
						GroundString groundString = (GroundString) selection
								.getFirstElement();
						Activator.getDefault().getGroundStringController()
								.setSelectedGroundString(groundString);

					}
				});
		return groundStringTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof ManagerProjects) {
			if (groundStringTableViewer.getElementAt(0) != null)
				groundStringTableViewer.remove(groundStringTableViewer
						.getInput());
			if (Activator.getDefault().getProjectController()
					.getProjectNameSelected() != null)
				Activator.getDefault().analyseProject();
		} else if (arg0 instanceof ManagerGroundString
				&& Activator.getDefault().getProjectController()
						.getProjectNameSelected() != null) {
			groundStringTableViewer.setInput(Activator.getDefault()
					.getGroundStringController().getListGroundString());
			editTableStyle(groundStringTableViewer);
		}

	}

	private void createColumnsToGroundString() {
		// the names of columns.
		String[] columnNames = new String[] {
				TableViewers.COLUMN_GROUND_STRING,
				TableViewers.COLUMN_RESOURCE, TableViewers.COLUMN_LINE };
		// the width of columns.
		int[] columnWidths = new int[] { 200, 150, 40 };

		// first column is for the ground string.
		TableViewerColumn col = createColumnsHeaders(groundStringTableViewer,
				columnNames[0], columnWidths[0]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}

		});

		// second column is for the resource name
		col = createColumnsHeaders(groundStringTableViewer, columnNames[1],
				columnWidths[1]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				GroundString groundString = (GroundString) cell.getElement();
				cell.setText(Activator.getDefault().getFullyQualifiedName(
						groundString.getGroundString()));
				super.update(cell);
			}

		});

		// third column is for the location
		col = createColumnsHeaders(groundStringTableViewer, columnNames[2],
				columnWidths[2]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				GroundString groundString = (GroundString) cell.getElement();
				cell.setText(String.valueOf(ASTUtil.getLineNumber(groundString
						.getGroundString())));
				super.update(cell);
			}

		});
	}

	public void dispose() {
		Activator.getDefault().getGroundStringController()
				.deleteObserverGroundString(this);
		Activator.getDefault().getProjectController()
				.deleteObserverManagerProjects(this);
	}
}
