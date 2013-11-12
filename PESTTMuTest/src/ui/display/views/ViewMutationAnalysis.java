package ui.display.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import ui.constants.TableViewers;
import ui.display.views.table.structure.TableViewerFactory;

public class ViewMutationAnalysis extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.GROUNDSTRINGTABLE.toString());
		TableViewerFactory.INSTANCE.createTableViewer(parent, getSite(),
				TableViewers.MUTATIONTABLE.toString());
		Label label1 = new Label(parent, SWT.SHADOW_OUT);

		Label label2 = new Label(parent, SWT.SHADOW_IN);
		Label label3 = new Label(parent, SWT.SHADOW_IN);
		label3.setText("Total de mutantes: ");
		label2.setText("Total de mutantes mortos: ");
		label1.setText("total de mutantes equivalentes: ");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
