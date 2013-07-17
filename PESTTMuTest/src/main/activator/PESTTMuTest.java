package main.activator;

import java.util.List;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.ast.visitors.SourceCodeVisitor;
import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.mutation.IMutationOperators;
import domain.mutation.Mutation;
import domain.projects.ExploreProject;
import ui.constants.Messages;
import ui.dialog.ProcessMessage;
import ui.display.views.tree.structure.TreeMutationOperators;

public class PESTTMuTest {

	private TreeMutationOperators treeViewer;
	private MutationOperatorsController operatorsController;
	private ExploreProject exploreProject;
	private GroundStringController groundStringController;

	public PESTTMuTest() {
		operatorsController = new MutationOperatorsController();
		groundStringController = new GroundStringController(
				operatorsController.getManagerMutationOperators());
		SourceCodeVisitor sourceCodeVisitor = new SourceCodeVisitor(
				groundStringController);
		exploreProject = new ExploreProject(sourceCodeVisitor);
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

			if (operatorsController.getSelectedIMutOperator() != null
					|| groundStringController.getSelectedGroundString() != null) {

				operatorsController.setSelectedIMutOperator(null);
				groundStringController.setSelectedGroundString(null);
			}
			operatorsController.createMutationOperators(elements);
			groundStringController.initializeListGroundString();
			exploreProject.scanProject();
		}
	}

	public void addObserverGroundStringController(Observer o) {
		groundStringController.addObserver(o);
	}

	public void deleteObserverGroundStringController(Observer o) {
		groundStringController.deleteObserver(o);
	}

	public void addObserverOperatorsController(Observer o) {
		operatorsController.addObserver(o);
	}

	public void deleteObserverOperatorsController(Observer o) {
		operatorsController.deleteObserver(o);
	}

	public void addObserverGroundString(Observer o) {
		groundStringController.addObserverGroundString(o);
	}

	public void deleteObserverGroundString(Observer o) {
		groundStringController.deleteObserverGroundString(o);
	}

	public void addObserverMutationOperators(Observer o) {
		operatorsController.addObserverMutationOperators(o);
	}

	public void deleteObserverMutationOperators(Observer o) {
		operatorsController.deleteObserverMutationOperators(o);
	}

	public List<ASTNode> getListGroundString() {
		return groundStringController.getListGroundString();
	}

	public void setSelectedGroundString(ASTNode node) {
		groundStringController.setSelectedGroundString(node);
	}

	public ASTNode getSelectedGroundString() {
		return groundStringController.getSelectedGroundString();
	}

	public List<IMutationOperators> getOperatorsApplicable() {
		return operatorsController
				.getOperatorsApplicable(groundStringController
						.getSelectedGroundString());
	}

	public void setSelectedIMutOperator(IMutationOperators operator) {
		operatorsController.setSelectedIMutOperator(operator);
	}

	public IMutationOperators getSelectedIMutOperator() {
		return operatorsController.getSelectedIMutOperator();
	}

	public List<Mutation> getMutations() {
		ASTNode selectedGroundString = groundStringController
				.getSelectedGroundString();
		return operatorsController.getMutations(selectedGroundString);
	}

}
