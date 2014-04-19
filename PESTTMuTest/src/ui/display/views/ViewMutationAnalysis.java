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

import domain.constants.Description;
import ui.constants.Colors;
import ui.constants.TableViewers;
import ui.display.views.table.structure.TableViewerFactory;

public class ViewMutationAnalysis extends ViewPart implements Observer {
	private Label labelNameProject;
	private Label labelTotalMutants;
	private Label labelEquivMutants;
	private Label labelKilledMutants;
	private Label labelLiveMutants;
	private Label labelMutationScore;

	@Override
	public void createPartControl(Composite parent) {
		Activator.getDefault().getMutationsController()
				.addObserverMutationTestResult(this);
		parent.setLayout(new GridLayout(1, false));

		// create a row with project name and mutation score
		Composite groups = new Composite(parent, SWT.NULL);
		groups.setLayout(new GridLayout(3, false));
		Group group = new Group(groups, SWT.NULL);
		group.setLayout(new RowLayout());
		group.setText("PROJECT NAME");
		labelNameProject = new Label(group, SWT.NULL);
		labelNameProject.setLayoutData(new RowData(300, 20));
		labelNameProject.setAlignment(SWT.CENTER);

		Label separator = new Label(groups, SWT.SEPARATOR | SWT.VERTICAL);
		separator.setLayoutData(new GridData(GridData.FILL));

		// create group to contain information about mutation score
		Group groupScore = new Group(groups, SWT.NONE);
		groupScore.setLayout(new GridLayout(10, false));
		groupScore.setText("MUTATION SCORE");

		// label total mutants
		Label labelText1 = new Label(groupScore, SWT.NONE);
		labelText1.setBackground(Colors.GREY);
		labelText1.setText("Total Mutants #");
		labelTotalMutants = new Label(groupScore, SWT.NONE);
		labelTotalMutants.setLayoutData(new GridData(GridData.FILL));
		labelTotalMutants.setBackground(Colors.VIOLET);
		labelTotalMutants.setText("0");

		// label killed mutants
		Label labelText2 = new Label(groupScore, SWT.NONE);
		labelText2.setBackground(Colors.GREY);
		labelText2.setText("Killed Mutants #");
		labelKilledMutants = new Label(groupScore, SWT.NONE);
		labelKilledMutants.setLayoutData(new GridData(GridData.FILL));
		labelKilledMutants.setBackground(Colors.VIOLET);
		labelKilledMutants.setText("0");

		// label live mutants
		Label labelText3 = new Label(groupScore, SWT.NONE);
		labelText3.setBackground(Colors.GREY);
		labelText3.setText("Live Mutants #");
		labelLiveMutants = new Label(groupScore, SWT.NONE);
		labelLiveMutants.setLayoutData(new GridData(GridData.FILL));
		labelLiveMutants.setBackground(Colors.VIOLET);
		labelLiveMutants.setText("0");

		// label mutants equivalents
		Label labelText4 = new Label(groupScore, SWT.NONE);
		labelText4.setBackground(Colors.GREY);
		labelText4.setText("Equivalent Mutants #");
		labelEquivMutants = new Label(groupScore, SWT.NONE);
		labelEquivMutants.setLayoutData(new GridData(GridData.FILL));
		labelEquivMutants.setBackground(Colors.VIOLET);
		labelEquivMutants.setText("0");

		// label mutation score
		Label labelText5 = new Label(groupScore, SWT.NONE);
		labelText5.setBackground(Colors.GREY);
		labelText5.setText("Mutation Score =");
		labelMutationScore = new Label(groupScore, SWT.NONE);
		labelMutationScore.setLayoutData(new GridData(GridData.FILL));
		labelMutationScore.setBackground(Colors.BLUE_SOFT);
		labelMutationScore.setText("0 %");

		// create a row with table (result mutation test)
		Composite tables = new Composite(parent, SWT.NULL);
		tables.setLayout(new FillLayout());
		tables.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				true));
		TableViewerFactory.INSTANCE.createTableViewer(tables, getSite(),
				TableViewers.ANALYSEMUTANTSTABLE.toString());

		TableViewerFactory.INSTANCE.createTableViewer(tables, getSite(),
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
			// add project name
			labelNameProject.setText(Activator.getDefault()
					.getProjectController().getProjectNameSelected());

			if (arg.toString().equals(Description.CALCULATE_MUTATION_SCORE)) {
				// add total mutants
				labelTotalMutants.setText(Integer.toString(Activator
						.getDefault().getMutationsController()
						.getNumberTotalMutants()));

				// add killed mutants
				labelKilledMutants.setText(Integer.toString(Activator
						.getDefault().getMutationsController()
						.getNumberKilledMutants()));

				// add live mutants
				labelLiveMutants.setText(Integer.toString(Activator
						.getDefault().getMutationsController()
						.getNumberLiveMutants()));

				// add equivalent mutants
				labelEquivMutants.setText(Integer.toString(Activator
						.getDefault().getMutationsController()
						.getNumberEquivalentMutants()));

				// add mutation score
				labelMutationScore.setText(String.format("%.2f", Activator
						.getDefault().getMutationsController()
						.getMutationScore())
						+ " %");
				// Double.toString(Activator
				// .getDefault().getMutationsController()
				// .getMutationScore())
				// + " %");

			}
		} else if (Activator.getDefault().getMutationsController()
				.getMutantsTestResults().size() == 0
				&& labelNameProject.getText() != "") {
			labelNameProject.setText("");
			labelTotalMutants.setText("0");
			labelKilledMutants.setText("0");
			labelLiveMutants.setText("0");
			labelEquivMutants.setText("0");
			labelMutationScore.setText("0 %");
		}

	}
}
