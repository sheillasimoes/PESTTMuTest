package main.activator;

import domain.controller.ControllerMain;
import ui.display.views.tree.structure.TreeMutationOperators;

public class PESTTMuTest {

	private TreeMutationOperators treeViewer;
	private ControllerMain controllerMain;

	public PESTTMuTest() {
		controllerMain = new ControllerMain();

	}

	public TreeMutationOperators getTreeViewer() {
		return treeViewer;
	}

	public void setTreeViewer(TreeMutationOperators treeViewer) {
		this.treeViewer = treeViewer;
	}

	public void runMutationOperators() {
		 controllerMain.runMutationOperators(treeViewer.getCheckedElements());
	}
}
