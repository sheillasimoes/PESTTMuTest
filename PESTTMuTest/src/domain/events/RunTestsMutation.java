package domain.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.mutation.Mutation;
import domain.mutation.MutationTestResult;
import domain.mutation.operators.IMutationOperators;
import ui.constants.Messages;
import ui.dialog.ProcessMessage;

public class RunTestsMutation {
	private MutationOperatorsController operatorsController;
	private GroundStringController groundStringController;
	private ProjectController projectController;
	private MutationsController mutationsController;
	private ControllerRunningTest controllerRunningTest;

	public RunTestsMutation(MutationOperatorsController operatorsController,
			GroundStringController groundStringController,
			ProjectController projectController,
			MutationsController mutationsController) {
		this.operatorsController = operatorsController;
		this.groundStringController = groundStringController;
		this.projectController = projectController;
		this.mutationsController = mutationsController;
		controllerRunningTest = new ControllerRunningTest();

	}

	public void startProcessTest(Object[] elements) {
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
		mutationsController.clearResultMutation();
		controllerRunningTest.clearData();

		// names projects
		Set<String> setNamesProjects = mutationsController
				.getSetNamesProjects();

		for (String nameProject : setNamesProjects) {
			if (projectController.hasTestClasses(nameProject)) {
				// ground string from project
				List<ASTNode> projectGS = mutationsController
						.getGroundStringFromProject(nameProject);
				// classes de teste
				List<Class<?>> testClasses = projectController
						.getTestClasses(projectGS.get(0));

				// Lista com o resultado do processo de teste de mutação
				MutationTestResult testResult = new MutationTestResult(
						nameProject);

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

								// resultado dos testes sobre o mutante gerado
								Map<Mutation, List<String>> mapResult = new HashMap<Mutation, List<String>>();

								mapResult.put(mutation, controllerRunningTest
										.getNamesTestFailure());
								testResult.addResult(mapResult);
								mutationsController.undoMutant(mutation);
								controllerRunningTest.clearData();
							}
						}
					}
				}
				// adiciona o resultado do processo de mutação de um dado
				// projeto
				mutationsController.addMutationTestResults(testResult);
			}
		}
		System.out.println("count " + controllerRunningTest.getCount());
	}

	public void runRandomMutations() {
		mutationsController.clearResultMutation();
		// names projects
		Set<String> setNamesProjects = mutationsController
				.getSetNamesProjects();

		for (String nameProject : setNamesProjects) {
			if (projectController.hasTestClasses(nameProject)) {
				// ground string from project
				List<ASTNode> projectGS = mutationsController
						.getGroundStringFromProject(nameProject);
				// classes de teste
				List<Class<?>> testClasses = projectController
						.getTestClasses(projectGS.get(0));
				// Lista com o resultado do processo de teste de mutação
				MutationTestResult testResult = new MutationTestResult(
						nameProject);

				for (ASTNode node : projectGS) {
					// mutation operators
					List<IMutationOperators> mutationOperators = groundStringController
							.getOperatorsApplicable(node);
					for (IMutationOperators operator : mutationOperators) {

						// mutations
						List<Mutation> mutations = operator.getMutations(node);
						boolean flag = false;
						do {
							Random random = new Random();
							int i = random.nextInt(mutations.size());
							if (mutationsController.applyMutant(mutations
									.get(i))) {

								for (Class<?> testClass : testClasses) {
									controllerRunningTest.runTest(testClass);
								}
								// resultado dos testes sobre o mutante gerado
								Map<Mutation, List<String>> mapResult = new HashMap<Mutation, List<String>>();

								mapResult.put(mutations.get(i),
										controllerRunningTest
												.getNamesTestFailure());
								testResult.addResult(mapResult);
								mutationsController
										.undoMutant(mutations.get(i));
								controllerRunningTest.clearData();
								flag = true;
							}
						} while (!flag);
					}
				}
				// adiciona o resultado do processo de mutação de um dado
				// projeto
				mutationsController.addMutationTestResults(testResult);
			}
		}
		System.out.println("count " + controllerRunningTest.getCount());
	}
}
