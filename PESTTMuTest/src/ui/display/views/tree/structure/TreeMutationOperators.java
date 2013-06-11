/**
 * 
 */
package ui.display.views.tree.structure;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Sheila Simoes
 * 
 */
public class TreeMutationOperators {
	private Composite parent;
	private CheckboxTreeViewer treeViewer;

	// private final CheckboxTreeViewer ;

	public TreeMutationOperators(Composite parent) {
		this.parent = parent;
	}

	public CheckboxTreeViewer createTreeViewer() {
		treeViewer = new CheckboxTreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new TreeMutOpContentProvider());
		treeViewer.setLabelProvider(new TreeMutOpLabelProvider());
		treeViewer.setInput(DefTreeMutationOperators.INSTANCE
				.getTreeMutationOperators());
		treeViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				AbstractTree element = (AbstractTree) event.getElement();
				if (event.getChecked() && element.hasChildren()) {
					treeViewer.setSubtreeChecked(element, true);
				} else if (!event.getChecked() && element.hasChildren()) {
					treeViewer.setSubtreeChecked(element, false);
				}

			}
		});
		return treeViewer;
	}

	public void checkAllMutationOperators() {
		// get root of the tree
		AbstractTree rootAbstractTree = (AbstractTree) treeViewer.getInput();
		AbstractTree[] childrenRoot = rootAbstractTree.getChildren();
		// check children of the root (categories of mutation operators)
		// check the children of categories of mutations operators
		for (AbstractTree node : childrenRoot) {
			treeViewer.setSubtreeChecked(node, true);
		}

	}

	public void uncheckAllMutationOperators() {
		// get root of the tree
		AbstractTree rootAbstractTree = (AbstractTree) treeViewer.getInput();
		AbstractTree[] childrenRoot = rootAbstractTree.getChildren();
		// uncheck children of the root (categories of mutation operators)
		// uncheck the children of categories of mutations operators
		for (AbstractTree node : childrenRoot) {
			treeViewer.setSubtreeChecked(node, false);
		}

	}

	public Object[] getCheckedElements() {
		return treeViewer.getCheckedElements();
	}

}
