package domain.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;

import domain.constants.Messages;
import ui.dialog.ProcessMessage;
import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.groundString.GroundString;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.util.ToStringASTNode;

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

				mutationsController.deleteTestResult();
				System.out.println("count gs " + projectGS.size());
				for (GroundString gs : projectGS) {
					if (((ICompilationUnit) ((CompilationUnit) gs
							.getGroundString().getRoot()).getJavaElement())
							.getElementName().equals(
									"PatternOptionBuilder.java")// "HelpFormatter.java")
							&& ToStringASTNode.toString(gs.getGroundString())
									.equals("required=false")) {// "pos=findWrapPos(text,width,0)")){
						// get info about ASTNode from apply mutation
						mutationsController.initialize(gs.getGroundString(),
								projectController.getMarkers());

						// mutation operators
						List<IMutationOperators> mutationOperators = groundStringController
								.getOperatorsApplicable(gs);

						for (IMutationOperators operator : mutationOperators) {
							// mutations
							List<Mutation> mutations = operator.getMutations(gs
									.getGroundString());

							boolean flag = false;
							ArrayList<Integer> listCount = new ArrayList<Integer>();
							Random random = new Random();
							int i;
							do {
								i = random.nextInt(mutations.size());

								if (!listCount.contains(Integer.valueOf(i))) {
									/*
									 * para nao entrar em ciclo infinito caso
									 * nao existam mutacoes validas
									 */
									listCount.add(Integer.valueOf(i));

									// is generated a valid mutant
									if (mutationsController
											.applyMutant(mutations.get(i))) {

										for (Class<?> testClass : projectController
												.getTestClasses()) {
											controllerRunningTest
													.runTest(testClass);
										}
										// add result
										mutationsController.addResult(mutations
												.get(i), controllerRunningTest
												.getTestsFailed());
										controllerRunningTest.clearData();
										flag = true;
									}
									// altera o ASTNode p o estado original
									mutations.get(i)
											.undoActionMutationOperator();
								}

							} while (!flag
									&& listCount.size() < mutations.size());

						}
						// altera o projeto para o estado original
						mutationsController.undoMutant();
					}
				}
			}
		}
	}
}