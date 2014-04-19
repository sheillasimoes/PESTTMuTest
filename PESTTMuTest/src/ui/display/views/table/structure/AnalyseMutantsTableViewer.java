package ui.display.views.table.structure;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.annotation.PreDestroy;

import main.activator.Activator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPartSite;

import ui.constants.Images;
import ui.constants.TableViewers;
import domain.constants.Description;
import domain.controller.ProcessMutationTestController;
import domain.mutation.Mutation;
import domain.mutation.testingProcess.MutationTestResult;

public class AnalyseMutantsTableViewer extends AbstractTableViewer implements
		Observer {
	private Composite parent;
	private IWorkbenchPartSite site;
	private TableViewer analyseMutantsTableViewer;
	private Image liveMutant;
	private Image killedMutant;

	public AnalyseMutantsTableViewer(Composite parent, IWorkbenchPartSite site) {
		super();
		this.parent = parent;
		this.site = site;
		Activator.getDefault().getMutationsController()
				.addObserverMutationTestResult(this);
		Activator.getDefault().getProcessMutationTestController()
				.addObserver(this);
		liveMutant = Images.getImage((Images.LIVEMUTANT));
		killedMutant = Images.getImage((Images.KILLEDMUTANT));

	}

	public TableViewer create() {
		analyseMutantsTableViewer = createViewTable(parent, site,
				TableViewers.ANALYSEMUTANTSTABLE);
		createColumnsToAnalyseMutants();
		analyseMutantsTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						Activator.getDefault().verifyChangesOperators();
						IStructuredSelection selection = (IStructuredSelection) analyseMutantsTableViewer
								.getSelection();
						// Mutation mutation = Activator.getDefault()
						// .getMutationsController().getSelectedMutation();
						if (selection.getFirstElement() != null) {
							// && (mutation == null || !mutation
							// .equals((Mutation) selection
							// .getFirstElement()))) {
							Activator
									.getDefault()
									.getMutationsController()
									.setSelectedMutation(
											(Mutation) selection
													.getFirstElement());
						}
					}
				});
		setSelections();
		return analyseMutantsTableViewer;
	}

	@Override
	public void update(Observable o, Object arg) {

		Set<Mutation> setMutation = Activator.getDefault()
				.getMutationsController().getMutantsTestResults();
		if (o instanceof MutationTestResult
				&& arg.toString().equals(Description.MUTATION_RESULT)) {
			if (setMutation.size() == 0
					&& analyseMutantsTableViewer.getElementAt(0) != null) {
				analyseMutantsTableViewer.remove(analyseMutantsTableViewer
						.getInput());
			} else {
				analyseMutantsTableViewer.setInput(setMutation);
			}
			// filtrar os mutantes vivos ou apresentar todos os mutantes
		} else if (o instanceof ProcessMutationTestController
				&& setMutation.size() > 0) {
			Set<Mutation> newViewResult = Activator.getDefault()
					.changeViewResult();
			if (setMutation.size() > 0 && newViewResult.size() > 0) {
				if (analyseMutantsTableViewer.getElementAt(0) != null) {
					// remove older information
					analyseMutantsTableViewer.remove(analyseMutantsTableViewer
							.getInput());
					// put new information
					analyseMutantsTableViewer.setInput(newViewResult);
				}
			}
		}
	}

	private void createColumnsToAnalyseMutants() {
		// the names of columns.
		String[] columnNames = new String[] {
				TableViewers.COLUMN_EQUIVALENT_MUTANT,
				TableViewers.COLUMN_MUTANT, TableViewers.COLUMN_MUTANT_STATE,
				TableViewers.COLUMN_MUTATION_OP_APPL,
				TableViewers.COLUMN_GROUND_STRING,
				TableViewers.COLUMN_RESOURCE, TableViewers.COLUMN_LINE };
		// the width of columns.
		int[] columnWidths = new int[] { 75, 200, 65, 200, 200, 150, 40 };

		// first column is for the check equivalent mutant.
		TableViewerColumn col = createColumnsHeaders(analyseMutantsTableViewer,
				columnNames[0], columnWidths[0]);
		col.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
			}
		});

		// second column is for the mutant.
		col = createColumnsHeaders(analyseMutantsTableViewer, columnNames[1],
				columnWidths[1]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setText(cell.getElement().toString());
				super.update(cell);
			}

		});

		// third column is for the mutant state.
		col = createColumnsHeaders(analyseMutantsTableViewer, columnNames[2],
				columnWidths[2]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				if (Activator.getDefault().getMutationsController()
						.isLiveMutant(mutation)) {
					cell.setImage(liveMutant);
				} else {
					cell.setImage(killedMutant);
				}

				super.update(cell);
			}

		});

		// fourth column is for the mutation operator Applicable
		col = createColumnsHeaders(analyseMutantsTableViewer, columnNames[3],
				columnWidths[3]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				cell.setText(mutation.getMutationOperator().toString());
				super.update(cell);
			}

		});

		// fifth column is for the ground string.
		col = createColumnsHeaders(analyseMutantsTableViewer, columnNames[4],
				columnWidths[4]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				cell.setText(mutation.getNodeToString());
				super.update(cell);
			}

		});

		// sixth column is for the fully qualified name
		col = createColumnsHeaders(analyseMutantsTableViewer, columnNames[5],
				columnWidths[5]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				cell.setText(Activator.getDefault().getFullyQualifiedName(
						(mutation.getASTNode())));
				super.update(cell);
			}

		});

		// seventh column is for the line of ground string
		col = createColumnsHeaders(analyseMutantsTableViewer, columnNames[6],
				columnWidths[6]);

		col.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				Mutation mutation = (Mutation) cell.getElement();
				cell.setText(Integer.toString(mutation.getLineNumber()));
				super.update(cell);
			}

		});
	}

	@PreDestroy
	public void dispose() {
		// image.dispose();
		// Activator.getDefault().deleteObserverMutationTestResult(this);
	}

	private void setSelections() {
		analyseMutantsTableViewer.getTable().addListener(SWT.Selection,
				new Listener() {

					public void handleEvent(Event event) {
						// when user enable/disable mutant.
						if (event.detail == SWT.CHECK) {
							for (TableItem item : analyseMutantsTableViewer
									.getTable().getItems()) {
								if (item == event.item) {
									if (item.getChecked()) {
										Activator
												.getDefault()
												.getMutationsController()
												.incrementEquivalentMutants(
														(Mutation) item
																.getData());

									} else {
										Activator
												.getDefault()
												.getMutationsController()
												.decrementEquivalentMutants(
														(Mutation) item
																.getData());
									}

								}
							}

						}
					}
				});
	}
}
