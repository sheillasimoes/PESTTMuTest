package ui.display.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import ui.constants.TableViewers;
import ui.display.views.table.structure.TableViewerFactory;

/**
 * Apresenta informação relacionada com os mutantes gerados
 * 
 * @author sheilla
 * 
 */
public class ViewMutants extends ViewPart {

	public ViewMutants() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.GROUNDSTRINGTABLE.toString());
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.OPMUTATIONAPPLTABLE.toString());
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.MUTANTTABLE.toString());

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
