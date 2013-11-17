package domain.controller;

import java.util.List;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.constants.Messages;
import ui.dialog.ProcessMessage;
import domain.events.RunAllMutationsEvent;
import domain.events.RunRandomMutationsEvent;
import domain.events.StartProcessMutationTestEvent;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.util.InfoProjectHelper;

public class ProcessMutationTestController {
	private MutationOperatorsController operatorsController;
	private MutationsController mutationsController;
	private GroundStringController groundStringController;
	private ProjectController projectController;
	private ControllerRunningTest controllerRunningTest;

	public ProcessMutationTestController() {
		operatorsController = new MutationOperatorsController();
		groundStringController = new GroundStringController(
				operatorsController.getManagerMutationOperators());
		projectController = new ProjectController(groundStringController);
		mutationsController = new MutationsController(groundStringController);
		controllerRunningTest = new ControllerRunningTest();
	}

	public void startProcessMutationTest(Object[] elements) {
		new StartProcessMutationTestEvent().execute(elements,
				operatorsController, groundStringController, projectController);
	}

	public void runRandomMutations() {
		new RunRandomMutationsEvent().execute(
				projectController.getProjectNameSelected(),
				mutationsController, projectController, groundStringController,
				controllerRunningTest);
	}

	public void runAllMutations() {
		new RunAllMutationsEvent().execute(
				projectController.getProjectNameSelected(),
				mutationsController, projectController, groundStringController,
				controllerRunningTest);
	}

	public void analyseProject() {
		groundStringController.initializeListGroundString();
		projectController.analyseProject(getProjectNameSelected());
	}

	public void verifyChangesOperators(Object[] elements) {
		boolean result = operatorsController.verifyChangesOperators(elements);
		if (!result) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_SELECT_ELEMENTS_TREE);
		}
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

	public List<IMutationOperators> getOperatorsApplicable() {
		return groundStringController.getOperatorsApplicable();
	}

	public List<String> getProjectNames() {
		return projectController.getProjectNames();
	}

	public String getProjectName(ASTNode node) {
		return InfoProjectHelper.getProjectName(node);
	}

	public String getFullyQualifiedName(ASTNode node) {
		return InfoProjectHelper.getFullyQualifiedName(node);
	}

	public String getProjectNameSelected() {
		return projectController.getProjectNameSelected();
	}

	public void setProjectSelected(String projectName) {
		projectController.setProjectNameSelected(projectName);
	}

	public ASTNode getSelectedGroundString() {
		return groundStringController.getSelectedGroundString();
	}

	public void setSelectedGroundString(ASTNode node) {
		groundStringController.setSelectedGroundString(node);
	}

	public void setSelectedIMutOperator(IMutationOperators operator) {
		operatorsController.setSelectedIMutOperator(operator);
	}

	public IMutationOperators getSelectedIMutOperator() {
		return operatorsController.getSelectedIMutOperator();
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

	public void addObserverManagerProjects(Observer o) {
		projectController.addObserverManagerProjects(o);
	}

	public void deleteObserverManagerProjects(Observer o) {
		projectController.deleteObserverManagerProjects(o);
	}

	public void addObserverCopyProject(Observer o) {
		projectController.addObserverCopyProject(o);
	}

	public void deleteObserverCopyProject(Observer o) {
		projectController.deleteObserverCopyProject(o);
	}
}
