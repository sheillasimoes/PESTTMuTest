package ui.display.views;

import java.util.LinkedList;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;

import ui.display.views.structural.GroupMutationOperator;

public class ViewMutationOperator extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		defTree(parent);

	}

	@Override
	public void setFocus() {
		// does nothing.

	}

	private void defTree(Composite parent) {
		// create tree
		final Tree tree = new Tree(parent, SWT.CHECK | SWT.MULTI
				| SWT.FULL_SELECTION | SWT.BORDER);

		// def root of tree
		TreeItem treeItemRoot;
		TreeItem treeItemNode;
		GroupMutationOperator[] groupMutationOperators = GroupMutationOperator
				.values();
		for (int i = 0; i < groupMutationOperators.length; i++) {
			treeItemRoot = new TreeItem(tree, SWT.CHECK);
			treeItemRoot.setText(groupMutationOperators[i].getNameCat());

			// def nodes of tree
			LinkedList<String> setOperator = groupMutationOperators[i]
					.getElements();
			for (int j = 0; j < setOperator.size(); j++) {
				treeItemNode = new TreeItem(treeItemRoot, 0);
				treeItemNode.setText(setOperator.get(j));
			}
		}

	}
}
