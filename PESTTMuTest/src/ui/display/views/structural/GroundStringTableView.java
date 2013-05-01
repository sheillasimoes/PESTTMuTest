/**
 * 
 */
package ui.display.views.structural;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.Colors;
import ui.constants.TableViewers;

/**
 * @author sheilla
 * 
 */
public class GroundStringTableView extends AbstractTableViewer implements
		Observer {

	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer groundStringTableViewer;
	private Control groundStringControl;

	public GroundStringTableView(Composite parent, IWorkbenchPartSite site) {
		this.parent = parent;
		this.site = site;

	}

	public TableViewer create() {
		groundStringTableViewer = createViewTable(parent, site,
				TableViewers.GROUNDSTRINGTABLE);
		groundStringControl = groundStringTableViewer.getControl();
		createColumnsToTableViewer();
		// groundStringTableViewer.setContentProvider(new
		// ArrayContentProvider());

		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		/*List<String> list = new LinkedList<String>();
		list.add(0, "um");
		list.add(1, "dois");
		groundStringTableViewer.setInput(list);
		int n = 0;
		for (TableItem item : groundStringTableViewer.getTable().getItems()) {
			item.setImage(1, null);
			if (n % 2 == 0)
				item.setBackground(Colors.WHITE);
			else
				item.setBackground(Colors.GREY);
			// groundStringTableViewer.getTable().getItem(n)
			// .setText(0, Integer.toString(n + 1));
			n++;
		}*/
		return groundStringTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

	public void createColumnsToTableViewer() {
		String columnNames = TableViewers.COLUMN_GROUND_STRING_TABLE; // the
																		// names
																		// of
																		// column.
		int columnWidths = 10; // the width of column.
		TableViewerColumn col = createColumnsHeaders(groundStringTableViewer,
				columnNames, columnNames.length(), 3);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				String str = (String) cell.getElement();
				cell.setText(str);
			}
		});
	}

	public void dispose() {
		groundStringControl.dispose();
	}

	/***
	 * Clears all visual status (Colors and images).
	 */
	private void cleanPathStatus() {
		int n = 0;
		for (TableItem item : groundStringTableViewer.getTable().getItems()) {
			item.setImage(1, null);
			if (n % 2 == 0)
				item.setBackground(Colors.WHITE);
			else
				item.setBackground(Colors.GREY);
			groundStringTableViewer.getTable().getItem(n)
					.setText(0, Integer.toString(n + 1));
			n++;
		}
	}

}
