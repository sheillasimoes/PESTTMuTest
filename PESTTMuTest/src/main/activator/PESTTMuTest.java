package main.activator;

import java.util.List;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.controller.ProcessMutationTestController;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
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

	public List<ASTNode> getListGroundString() {
		return processMutationTestController.getListGroundString();
	}

	public List<IMutationOperators> getOperatorsApplicable() {
		return processMutationTestController.getOperatorsApplicable();
	}

	public List<String> getProjectNames() {
		return processMutationTestController.getProjectNames();
	}

	public String getProjectName(ASTNode node) {
		return processMutationTestController.getProjectName(node);
	}

	public String getFullyQualifiedName(ASTNode node) {
		return processMutationTestController.getFullyQualifiedName(node);
	}

	public ASTNode getSelectedGroundString() {
		return processMutationTestController.getSelectedGroundString();
	}

	public void setSelectedGroundString(ASTNode node) {
		processMutationTestController.setSelectedGroundString(node);
	}

	public IMutationOperators getSelectedIMutOperator() {
		return processMutationTestController.getSelectedIMutOperator();
	}

	public void setSelectedIMutOperator(IMutationOperators operator) {
		processMutationTestController.setSelectedIMutOperator(operator);
	}

	public String getProjectNameSelected() {
		return processMutationTestController.getProjectNameSelected();
	}

	public void setProjectSelected(String projectName) {
		processMutationTestController.setProjectSelected(projectName);
	}

	public TreeMutationOperators getTreeViewer() {
		return treeViewer;
	}

	public void setTreeViewer(TreeMutationOperators treeViewer) {
		this.treeViewer = treeViewer;
	}

	public void addObserverGroundStringController(Observer o) {
		processMutationTestController.addObserverGroundStringController(o);
	}

	public void deleteObserverGroundStringController(Observer o) {
		processMutationTestController.deleteObserverGroundStringController(o);
	}

	public void addObserverOperatorsController(Observer o) {
		processMutationTestController.addObserverOperatorsController(o);
	}

	public void deleteObserverOperatorsController(Observer o) {
		processMutationTestController.deleteObserverOperatorsController(o);
	}

	public void addObserverGroundString(Observer o) {
		processMutationTestController.addObserverGroundString(o);
	}

	public void deleteObserverGroundString(Observer o) {
		processMutationTestController.deleteObserverGroundString(o);
	}

	public void addObserverMutationOperators(Observer o) {
		processMutationTestController.addObserverMutationOperators(o);
	}

	public void deleteObserverMutationOperators(Observer o) {
		processMutationTestController.deleteObserverMutationOperators(o);
	}

	public void addObserverManagerProjects(Observer o) {
		processMutationTestController.addObserverManagerProjects(o);
	}

	public void deleteObserverManagerProjects(Observer o) {
		processMutationTestController.deleteObserverManagerProjects(o);
	}

	public void addObserverCopyProject(Observer o) {
		processMutationTestController.addObserverCopyProject(o);
	}

	public void deleteObserverCopyProject(Observer o) {
		processMutationTestController.deleteObserverCopyProject(o);
	}

}
