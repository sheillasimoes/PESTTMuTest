package ui.display.views;

import java.util.Observable;
import java.util.Observer;

import main.activator.Activator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import ui.constants.TableViewers;
import ui.display.views.table.structure.TableViewerFactory;

public class ViewMutationAnalysis extends ViewPart implements Observer {
	private Label label;

	@Override
	public void createPartControl(Composite parent) {
		Activator.getDefault().getMutationsController()
				.addObserverMutationTestResult(this);
		parent.setLayout(new GridLayout(1, false));

		// create a row with project name
		Group group = new Group(parent, SWT.NULL);
		group.setLayout(new RowLayout());
		group.setText("PROJECT NAME");
		label = new Label(group, SWT.NULL);
		label.setLayoutData(new RowData(400, 20));
		label.setAlignment(SWT.CENTER);

		// create a row with result mutation test
		Composite tablesBar = new Composite(parent, SWT.NULL);
		tablesBar.setLayout(new FillLayout());
		tablesBar.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		TableViewerFactory.INSTANCE.createTableViewer(tablesBar, getSite(),
				TableViewers.ANALYSEMUTANTSTABLE.toString());

		TableViewerFactory.INSTANCE.createTableViewer(tablesBar, getSite(),
				TableViewers.TESTCLASSESFAILEDTABLE.toString());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable o, Object arg) {
		if (Activator.getDefault().getMutationsController()
				.getMutantsTestResults().size() > 0) {
			label.setText(Activator.getDefault().getProjectController()
					.getProjectNameSelected());
		} else if (label.getText() != ""
				&& Activator.getDefault().getMutationsController()
						.getMutantsTestResults().size() == 0) {
			label.setText("");
		}
	}
}
