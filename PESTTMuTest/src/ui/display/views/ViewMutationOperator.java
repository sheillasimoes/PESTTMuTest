package ui.display.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.part.ViewPart;

import ui.display.views.tree.structure.DefTreeMutationOperators;
import ui.display.views.tree.structure.TreeMutOpContentProvider;
import ui.display.views.tree.structure.TreeMutOpLabelProvider;

public class ViewMutationOperator extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.CHECK);
		treeViewer.setContentProvider(new TreeMutOpContentProvider());
		treeViewer.setLabelProvider(new TreeMutOpLabelProvider());
		treeViewer.setInput(DefTreeMutationOperators.INSTANCE
				.getTreeMutationOperators());

	}

	@Override
	public void setFocus() {
		// does nothing.

	}

}
