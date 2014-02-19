package domain.events;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.constants.Messages;
import ui.dialog.ProcessMessage;
import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;

public class RunAllMutationsEvent {
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
				// verifica se foram encontradas GS para aplicar mutações
				if (projectGS.size() > 0) {
					// test classes
					List<Class<?>> testClasses = projectController
							.getTestClasses();
					mutationsController.deleteTestResult();
					controllerRunningTest.clearData();
					for (ASTNode node : projectGS) {
						// mutation operators
						List<IMutationOperators> mutationOperators = groundStringController
								.getOperatorsApplicable(node);

						for (IMutationOperators operator : mutationOperators) {
							// mutations
							List<Mutation> mutations = operator
									.getMutations(node);

							for (Mutation mutation : mutations) {
								if (mutationsController.applyMutant(mutation)) {
									for (Class<?> testClass : testClasses) {
										controllerRunningTest
												.runTest(testClass);
									}
									mutationsController.undoMutant(mutation);
									// add result
									mutationsController.addResult(mutation,
											controllerRunningTest
													.getTestsFailed());
									controllerRunningTest.clearData();
								}
							}
						}
					}
					System.out.println("count "
							+ controllerRunningTest.getCount());
				}
			}
		}
	}
}
