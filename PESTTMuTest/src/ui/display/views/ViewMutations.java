package ui.display.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import ui.constants.TableViewers;
import ui.display.views.table.structure.TableViewerFactory;

public class ViewMutations extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.GROUNDSTRINGTABLE.toString());
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.OPMUTATIONAPPLTABLE.toString());
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.MUTATIONTABLE.toString());

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
