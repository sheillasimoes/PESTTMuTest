/**
 * 
 */
package ui.display.views.table.structure;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.TableViewer;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.IWorkbenchPartSite;

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

	public GroundStringTableView(Composite parent, IWorkbenchPartSite site) {
		super();
		this.parent = parent;
		this.site = site;

	}

	public TableViewer create() {
		groundStringTableViewer = createViewTable(parent, site,
				TableViewers.GROUNDSTRINGTABLE);
		createColumnsHeaders(groundStringTableViewer, TableViewers.COLUMN_GROUND_STRING_TABLE, 700);
		return groundStringTableViewer;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

}
