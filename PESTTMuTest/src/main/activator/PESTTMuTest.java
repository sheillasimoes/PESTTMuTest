package main.activator;

import java.util.List;
import java.util.Observer;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.controller.ControllerRunningTest;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import ui.constants.Messages;
import ui.dialog.ProcessMessage;
import ui.display.views.tree.structure.TreeMutationOperators;

public class PESTTMuTest {

	private TreeMutationOperators treeViewer;
	private MutationOperatorsController operatorsController;
	private MutationsController mutationsController;
	private GroundStringController groundStringController;
	private ProjectController projectController;
	private ControllerRunningTest controllerRunningTest;

	public PESTTMuTest() {
		operatorsController = new MutationOperatorsController();
		groundStringController = new GroundStringController(
				operatorsController.getManagerMutationOperators());
		projectController = new ProjectController(groundStringController);
		mutationsController = new MutationsController(groundStringController);
		controllerRunningTest = new ControllerRunningTest();
	}

	public TreeMutationOperators getTreeViewer() {
		return treeViewer;
	}

	public void setTreeViewer(TreeMutationOperators treeViewer) {
		this.treeViewer = treeViewer;
	}

	public void runMutationOperators() {

		Object[] elements = treeViewer.getCheckedElements();
		// checks if any was selected mutation operator
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
			projectController.analyseProject();

		}

	}

	public void runAllMutations() {
		// names projects
		Set<String> setNamesProjects = mutationsController
				.getSetNamesProjects();
		for (String nameProject : setNamesProjects) {
			if (projectController.hasTestClasses(nameProject)) {
				// ground string
				List<ASTNode> projectGS = mutationsController
						.getGroundStringFromProject(nameProject);
				// list test classes
				List<Class<?>> testClasses = projectController
						.getTestClasses(projectGS.get(0));
				for (ASTNode node : projectGS) {
					// mutation operators
					List<IMutationOperators> mutationOperators = groundStringController
							.getOperatorsApplicable(node);
					for (IMutationOperators operator : mutationOperators) {
						// mutations
						List<Mutation> mutations = operator.getMutations(node);
						for (Mutation mutation : mutations) {
							if (mutationsController.applyMutant(mutation)) {
								for (Class<?> testClass : testClasses) {
									controllerRunningTest.runTest(testClass);
								}
							}
						}
					}
				}
			}
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
