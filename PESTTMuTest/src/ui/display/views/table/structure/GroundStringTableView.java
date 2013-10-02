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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

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
						IStructuredSelection selection = (IStructuredSelection) groundStringTableViewer
								.getSelection();

						ASTNode node = (ASTNode) selection.getFirstElement();

						CompilationUnit cUnit = (CompilationUnit) node
								.getRoot();
						IFile file = (IFile) cUnit.getJavaElement()
								.getResource();
						IWorkbenchPage page = PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getActivePage();
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

						ASTNode node = (ASTNode) selection.getFirstElement();
						Activator.getDefault().setSelectedGroundString(node);

					}
				});

		return groundStringTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		List<ASTNode> list = Activator.getDefault().getListGroundString();

		if (list.size() == 0 && groundStringTableViewer.getElementAt(0) != null) {
			Object allElements = groundStringTableViewer.getInput();
			groundStringTableViewer.remove(allElements);

		} else if (list.size() > 0) {
			groundStringTableViewer.setInput(list);
			editTableStyle(groundStringTableViewer);

		}

	}

	private void createColumnsToGroundString() {
		TableViewerColumn col = createColumnsHeaders(groundStringTableViewer,
				TableViewers.COLUMN_GROUND_STRING_TABLE,
				TableViewers.COLUMN_WIDTH);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}

		});

	}

	public void dispose() {
		Activator.getDefault().deleteObserverGroundString(this);
	}
}
