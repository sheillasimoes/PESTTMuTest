package main.activator;

import java.util.List;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.events.RunTestsMutation;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.util.InfoProjectHelper;
import ui.constants.Messages;
import ui.dialog.ProcessMessage;
import ui.display.views.tree.structure.TreeMutationOperators;

public class PESTTMuTest {

	private TreeMutationOperators treeViewer;
	private MutationOperatorsController operatorsController;
	private MutationsController mutationsController;
	private GroundStringController groundStringController;
	private ProjectController projectController;
	private RunTestsMutation runTestsMutation;

	public PESTTMuTest() {
		operatorsController = new MutationOperatorsController();
		groundStringController = new GroundStringController(
				operatorsController.getManagerMutationOperators());
		projectController = new ProjectController(groundStringController);
		mutationsController = new MutationsController(groundStringController);
		runTestsMutation = new RunTestsMutation(operatorsController,
				groundStringController, projectController, mutationsController);
	}

	public TreeMutationOperators getTreeViewer() {
		return treeViewer;
	}

	public void setTreeViewer(TreeMutationOperators treeViewer) {
		this.treeViewer = treeViewer;
	}

	public void startProcessTest() {
		runTestsMutation.startProcessTest(treeViewer.getCheckedElements());
	}

	public void runRandomMutations() {
		runTestsMutation.runRandomMutations();

	}

	public void runAllMutations() {
		runTestsMutation.runAllMutations();
	}

	public List<Mutation> getMutantsToDisplay() {
		ASTNode groundString = groundStringController.getSelectedGroundString();
		List<Mutation> mutations = operatorsController
				.getMutations(groundString);
		return mutationsController.getMutantsToDisplay(groundString, mutations);
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
		return groundStringController.getOperatorsApplicable();
	}

	public void setSelectedIMutOperator(IMutationOperators operator) {
		operatorsController.setSelectedIMutOperator(operator);
	}

	public IMutationOperators getSelectedIMutOperator() {
		return operatorsController.getSelectedIMutOperator();
	}

	public void verifyChangesOperators() {
		boolean result = operatorsController.verifyChangesOperators(treeViewer
				.getCheckedElements());
		if (!result) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_SELECT_ELEMENTS_TREE);
		}
	}

	public String getProjectName(ASTNode node) {
		return InfoProjectHelper.getProjectName(node);
	}

	public String getFullyQualifiedName(ASTNode node) {
		return InfoProjectHelper.getFullyQualifiedName(node);
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

}
