package ui.display.views.structural;

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
 * 
 * @author sheilla
 * 
 */
public class MutantTableView extends AbstractTableViewer implements
		Observer {

	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer mutantTableViewer;

	public MutantTableView(Composite parent, IWorkbenchPartSite site) {
		this.parent = parent;
		this.site = site;

	}

	public TableViewer create() {
		mutantTableViewer = createViewTable(parent, site, TableViewers.MUTANTTABLE);
		createColumnsToMutationViewer();
		return mutantTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public void createColumnsToMutationViewer() {
		String columnNames = TableViewers.COLUMN_MUTANT_TABLE; // the names of column.
		int columnWidths = 700; // the width of column.
		TableViewerColumn col = createColumnsHeaders(mutantTableViewer,
				columnNames, columnWidths, 0);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				String str = (String) cell.getElement();
				cell.setText(str);
			}
		});
	}
}
