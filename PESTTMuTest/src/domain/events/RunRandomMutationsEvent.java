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
			// verifica se o projeto selecionado tem classes de teste
			if (!projectController.hasTestClasses(projectName)) {
				ProcessMessage.INSTANCE.showInformationMessage("Info",
						Messages.PROJECT_NOT_HAVE_TEST_CALSSES);
			} else {
				mutationsController.deleteTestResult();
				// ground string
				List<GroundString> projectGS = groundStringController
						.getListGroundString();
				for (GroundString gs : projectGS) {

					// mutation operators
					List<IMutationOperators> mutationOperators = groundStringController
							.getOperatorsApplicable(gs);

					// get info about ASTNode from apply mutation
					mutationsController.initialize(gs.getGroundString(),
							projectController.getMarkers());

					for (IMutationOperators operator : mutationOperators) {
						// mutations
						List<Mutation> mutations = operator.getMutations(gs
								.getGroundString());

						boolean flag = false;
						ArrayList<Integer> listCount = new ArrayList<Integer>();
						for (int i = 0; i < mutations.size(); i++)
							listCount.add(i);
						Random random = new Random();
						int i;
						do {
							int pos = random.nextInt(listCount.size());
							i = listCount.get(pos);
							listCount.remove(pos);

							// is generated a valid mutant
							if (mutationsController.applyMutant(mutations
									.get(i))) {

								for (Class<?> testClass : projectController
										.getTestClasses()) {
									controllerRunningTest.runTest(testClass);

								}
								// add result
								mutationsController.addResult(mutations.get(i),
										controllerRunningTest.getTestsFailed());
								controllerRunningTest.clearData();
								flag = true;
							}
							// altera o ASTNode p o estado original
							mutations.get(i).undoActionMutationOperator();

						} while (!flag && listCount.size() > 0);

					}
					// altera o projeto para o estado original
					mutationsController.undoMutant();
				}
			}
		}
	}
}