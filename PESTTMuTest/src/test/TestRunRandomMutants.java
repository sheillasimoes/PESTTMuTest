package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.groundString.GroundString;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.util.ToStringASTNode;

public class TestRunRandomMutants {
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
			List<StringBuilder> list = new LinkedList<StringBuilder>();
			int countMutants = 0;
			int countGroundString = 0;
			// limpa o contador do tempo
			controllerRunningTest.setCountTime(0);
			for (GroundString gs : projectGS) {
				countGroundString++;
				// mutation operators
				List<IMutationOperators> mutationOperators = groundStringController
						.getOperatorsApplicable(gs);
				StringBuilder str = new StringBuilder();
				str.append(countGroundString);
				str.append(" groundString "
						+ ToStringASTNode.toString(gs.getGroundString())
						+ " MUTANTE ");
				for (IMutationOperators operator : mutationOperators) {
					// mutations
					List<Mutation> mutations = operator.getMutations(gs
							.getGroundString());
					// get info about ASTNode from apply mutation
					mutationsController.initialize(gs.getGroundString(),
							projectController.getMarkers());
					boolean flag = false;
					ArrayList<Integer> listCount = new ArrayList<Integer>();
					Random random = new Random();
					int i;
					do {
						i = random.nextInt(mutations.size());

						if (!listCount.contains(Integer.valueOf(i))) {
							/*
							 * para nao entrar em ciclo infinito caso nao
							 * existam mutacoes validas
							 */
							listCount.add(Integer.valueOf(i));

							// is generated a valid mutant
							if (mutationsController.applyMutant(mutations
									.get(i))) {
								countMutants++;
								str.append(" count " + countMutants
										+ mutations.get(i).toString());
								list.add(str);
								for (Class<?> testClass : testClasses) {
									controllerRunningTest.runTest(testClass);
								}
								// altera ASTNode p o estado original
								mutations.get(i).undoActionMutationOperator();
								// controllerRunningTest.clearData();
								flag = true;
							} else {
								// altera o ASTNode p o estado original
								mutations.get(i).undoActionMutationOperator();
							}
						}

					} while (!flag && listCount.size() < mutations.size());
					// altera o projeto para o estado original
					mutationsController.undoMutant();
				}
			}
			System.out.println("total ground String " + projectGS.size()
					+ " total mutants " + countMutants + " time "
					+ controllerRunningTest.getCountTime() + " testClasses "
					+ testClasses.size());
			for (StringBuilder s : list) {
				System.out.println(s.toString());
			}
			int i = 0;
			for (GroundString g : projectGS) {
				i++;
				System.out.println(i + " "
						+ ToStringASTNode.toString(g.getGroundString()));
			}
		}

	}
}
