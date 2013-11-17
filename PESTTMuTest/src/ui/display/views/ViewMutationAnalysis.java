package ui.display.views;

import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.part.ViewPart;

import ui.constants.TableViewers;
import ui.display.views.table.structure.TableViewerFactory;

public class ViewMutationAnalysis extends ViewPart implements Observer {

	@Override
	public void createPartControl(Composite parent) {
		Activator.getDefault().addObserverGroundString(this);
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.ANALYSEMUTANTS.toString());
		// Label label = new Label(parent, SWT.SEPARATOR);
		Group group = new Group(parent, SWT.SHADOW_ETCHED_OUT);
		group.setText("Mutation Score");

		// Label label1 = new Label(parent, SWT.SHADOW_OUT);
		// Label label2 = new Label(parent, SWT.SHADOW_IN);
		// Label label3 = new Label(parent, SWT.SHADOW_IN);
		// label3.setText("Total de mutantes: ");
		// label2.setText("Total de mutantes mortos: ");
		// label1.setText("total de mutantes equivalentes: ");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
