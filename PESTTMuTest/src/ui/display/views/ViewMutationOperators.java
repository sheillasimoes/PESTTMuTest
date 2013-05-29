package ui.display.views;

import main.activator.Activator;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import ui.display.views.tree.structure.TreeMutationOperators;

public class ViewMutationOperators extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {

		TreeMutationOperators treeMutationOperators = new TreeMutationOperators(
				parent);
		treeMutationOperators.createTreeViewer();

		Activator.getDefault().setTreeViewer(treeMutationOperators);

	}

	@Override
	public void setFocus() {
		// does nothing.

	}

}
