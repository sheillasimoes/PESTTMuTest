package domain.events;

import java.util.List;
import java.util.Random;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.constants.Messages;
import ui.dialog.ProcessMessage;
import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;

public class RunRandomMutationsEvent {
	public void execute(String projectName,
			MutationsController mutationsController,
			ProjectController projectController,
			GroundStringController groundStringController,
			ControllerRunningTest controllerRunningTest) {
		if (projectName == null) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_SELECT_PROJECT);
		} else {
			// verifica se o projeto selecionado tem classes de teste
			if (!projectController.hasTestClasses(projectName)) {
				ProcessMessage.INSTANCE.showInformationMessage("Info",
						Messages.PROJECT_NOT_HAVE_TEST_CALSSES);
			} else {
				// ground string
				List<ASTNode> projectGS = groundStringController
						.getListGroundString();
				// test classes
				List<Class<?>> testClasses = projectController.getTestClasses();
				mutationsController.deleteTestResult();

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
								// add result
								mutationsController
										.addResult(mutations.get(i),
												controllerRunningTest
														.getTestsFailure());
								mutationsController
										.undoMutant(mutations.get(i));
								controllerRunningTest.clearData();
								flag = true;
							}
						} while (!flag);
					}
				}
				System.out.println("count " + controllerRunningTest.getCount());
			}
		}

	}
}
