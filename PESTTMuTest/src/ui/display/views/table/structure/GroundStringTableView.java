/**
 * 
 */
package ui.display.views.table.structure;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

import domain.groundString.ManagerGroundString;
import domain.projects.ManagerProjects;
import ui.constants.TableViewers;

/**
 * 
 * @author Sheilla Simoes
 * 
 */
public class GroundStringTableView extends AbstractTableViewer implements
		Observer {

	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer groundStringTableViewer;

	public GroundStringTableView(Composite parent, IWorkbenchPartSite site) {
		super();
		this.parent = parent;
		this.site = site;
		Activator.getDefault().addObserverGroundString(this);
		Activator.getDefault().addObserverManagerProjects(this);
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

		// apresenta os operadores de muta��o que podem ser aplicados
		groundStringTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						Activator.getDefault().verifyChangesOperators();
						IStructuredSelection selection = (IStructuredSelection) groundStringTableViewer
								.getSelection();
						ASTNode node = (ASTNode) selection.getFirstElement();
						Activator.getDefault().setSelectedGroundString(node);

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
			if (Activator.getDefault().getProjectNameSelected() != null)
				Activator.getDefault().analyseProject();
		} else if (arg0 instanceof ManagerGroundString
				&& Activator.getDefault().getProjectNameSelected() != null) {
			groundStringTableViewer.setInput(Activator.getDefault()
					.getListGroundString());
			editTableStyle(groundStringTableViewer);
		}

	}

	private void createColumnsToGroundString() {
		String[] columnNames = new String[] {
				TableViewers.COLUMN_GROUND_STRING,
				TableViewers.COLUMN_FULLY_QUALIFIED_NAME,
				TableViewers.COLUMN_PROJECT_NAME }; // the names of
													// columns.
		int[] columnWidths = new int[] { 200, 200, 150 }; // the width of
															// columns.

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

		// second column is for the fully qualified name
		col = createColumnsHeaders(groundStringTableViewer, columnNames[1],
				columnWidths[1]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				ASTNode node = (ASTNode) cell.getElement();
				cell.setText(Activator.getDefault().getFullyQualifiedName(node));
				super.update(cell);
			}

		});
	}

	public void dispose() {
		Activator.getDefault().deleteObserverGroundString(this);
		Activator.getDefault().deleteObserverManagerProjects(this);
		groundStringTableViewer.getControl().dispose();
	}
}
