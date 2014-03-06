package domain.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.constants.Messages;
import ui.dialog.ProcessMessage;
import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.groundString.GroundString;
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
					Messages.NOT_SELECT_PROJECT_NAME);
		} else {
			mutationsController.setSelectedMutation(null);
			mutationsController.deleteTestResult();
			// verifica se o projeto selecionado tem classes de teste
			if (!projectController.hasTestClasses(projectName)) {
				ProcessMessage.INSTANCE.showInformationMessage("Info",
						Messages.PROJECT_NOT_HAVE_TEST_CALSSES);
			} else {
				// ground string
				List<GroundString> projectGS = groundStringController
						.getListGroundString();
				// verifica se foram encontradas GS para aplicar mutações
				if (projectGS.size() > 0) {
					// test classes
					List<Class<?>> testClasses = projectController
							.getTestClasses();
					mutationsController.deleteTestResult();
					controllerRunningTest.clearData();
					for (GroundString gs : projectGS) {

						// mutation operators
						List<IMutationOperators> mutationOperators = groundStringController
								.getOperatorsApplicable(gs);

						for (IMutationOperators operator : mutationOperators) {
							// mutations
							List<Mutation> mutations = operator.getMutations(gs
									.getGroundString());
							boolean flag = false;
							ArrayList<Integer> listCount = new ArrayList<Integer>();
							do {
								Random random = new Random();
								int i = random.nextInt(mutations.size());
								// verifica se é gerado um mutante válido
								if (!listCount.contains(Integer.valueOf(i))
										&& mutationsController
												.applyMutant(mutations.get(i))) {
									for (Class<?> testClass : testClasses) {
										controllerRunningTest
												.runTest(testClass);
									}
									mutationsController.undoMutant(mutations
											.get(i));
									// add result
									mutationsController.addResult(mutations
											.get(i), controllerRunningTest
											.getTestsFailed());
									controllerRunningTest.clearData();
									flag = true;
								}
								/*
								 * verificacao para nao entrar em ciclo infinito
								 * caso nao existam mutacoes validas
								 */
								if (!listCount.contains(Integer.valueOf(i))) {
									listCount.add(Integer.valueOf(i));
								}
							} while (!flag
									&& listCount.size() < mutations.size());
						}
						break;
					}
				}
			}

		}
	}
}