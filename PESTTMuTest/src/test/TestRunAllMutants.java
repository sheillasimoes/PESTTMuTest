package test;

import java.util.List;

import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.groundString.GroundString;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;

public class TestRunAllMutants {
	public void execute(MutationsController mutationsController,
			ProjectController projectController,
			GroundStringController groundStringController,
			ControllerRunningTest controllerRunningTest) {

		// ground string
		List<GroundString> projectGS = groundStringController
				.getListGroundString();
		// verifica se foram encontradas GS para aplicar mutações
		if (projectGS.size() > 0) {
			// test classes
			List<Class<?>> testClasses = projectController.getTestClasses();
			int countMutants = 0;
			// limpa o contador do tempo
			controllerRunningTest.setCountTime(0);
			for (GroundString gs : projectGS) {
				// mutation operators
				List<IMutationOperators> mutationOperators = groundStringController
						.getOperatorsApplicable(gs);

				for (IMutationOperators operator : mutationOperators) {
					// mutations
					List<Mutation> mutations = operator.getMutations(gs
							.getGroundString());
					// get info about ASTNode from apply mutation
					mutationsController.initialize(mutations.get(0));
					for (Mutation mutation : mutations) {
						// is generated a valid mutant
						if (mutationsController.applyMutant(mutation)) {
							for (Class<?> testClass : testClasses) {
								controllerRunningTest.runTest(testClass);
							}
							// altera ASTNode p o estado original
							mutation.undoActionMutationOperator();
							countMutants++;
						} else {
							// altera o ASTNode p o estado original
							mutation.undoActionMutationOperator();
						}
					}
					// altera o projeto para o estado original
					mutationsController.undoMutant();
				}
			}
			System.out.println("total ground String " + projectGS.size()
					+ "total mutants " + countMutants + " time "
					+ controllerRunningTest.getCountTime());
		}

	}

}
