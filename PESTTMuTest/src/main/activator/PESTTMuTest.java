package main.activator;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.MutationsController;
import domain.controller.ProcessMutationTestController;
import domain.controller.ProjectController;
import domain.mutation.Mutation;
import ui.display.views.tree.structure.TreeMutationOperators;

public class PESTTMuTest {

	private TreeMutationOperators treeViewer;
	private ProcessMutationTestController processMutationTestController;

	public PESTTMuTest() {
		processMutationTestController = new ProcessMutationTestController();
	}

	public void startProcessTest() {
		processMutationTestController.startProcessMutationTest(treeViewer
				.getCheckedElements());
	}

	public void runRandomMutations() {
		processMutationTestController.runRandomMutations();
	}

	public void runAllMutations() {
		processMutationTestController.runAllMutations();
	}

	public void analyseProject() {
		processMutationTestController.analyseProject();
	}

	public void verifyChangesOperators() {
		processMutationTestController.verifyChangesOperators(treeViewer
				.getCheckedElements());
	}

	public List<Mutation> getMutantsToDisplay() {
		return processMutationTestController.getMutantsToDisplay();
	}

	public void changeTypeViewResult(String typeView) {
		processMutationTestController.changeTypeViewResult(typeView);
	}

	public Set<Mutation> changeViewResult() {
		return processMutationTestController.changeViewResult();
	}

	/**
	 * @return the operatorsController
	 */
	public MutationOperatorsController getOperatorsController() {
		return processMutationTestController.getOperatorsController();
	}

	/**
	 * @return the mutationsController
	 */
	public MutationsController getMutationsController() {
		return processMutationTestController.getMutationsController();
	}

	/**
	 * @return the groundStringController
	 */
	public GroundStringController getGroundStringController() {
		return processMutationTestController.getGroundStringController();
	}

	/**
	 * @return the projectController
	 */
	public ProjectController getProjectController() {
		return processMutationTestController.getProjectController();
	}

	/**
	 * @return the processMutationTestController
	 */
	public ProcessMutationTestController getProcessMutationTestController() {
		return processMutationTestController;
	}

	public String getFullyQualifiedName(ASTNode node) {
		return processMutationTestController.getFullyQualifiedName(node);
	}

	public TreeMutationOperators getTreeViewer() {
		return treeViewer;
	}

	public void setTreeViewer(TreeMutationOperators treeViewer) {
		this.treeViewer = treeViewer;
	}

}
