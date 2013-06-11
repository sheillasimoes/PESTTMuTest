package main.activator;

import domain.controller.ControllerMain;
import ui.constants.Messages;
import ui.dialog.ProcessMessage;
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
		Object[] elements = treeViewer.getCheckedElements();
		if (elements.length == 0) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_SELECT_ELEMENTS_TREE);
		} else {
			controllerMain.runMutationOperators(elements);
		}
	}
}
