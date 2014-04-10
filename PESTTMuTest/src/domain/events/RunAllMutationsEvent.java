package domain.events;

import java.util.List;

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

public class RunAllMutationsEvent {
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
				for (GroundString gs : projectGS) {
					if (((ICompilationUnit) ((CompilationUnit) gs
							.getGroundString().getRoot()).getJavaElement())
							.getElementName().equals(
									"PatternOptionBuilder.java")
							&& ToStringASTNode.toString(gs.getGroundString())
									.equals("required=false")) {
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
							for (Mutation mutation : mutations) {
								// is generated a valid mutant
								if (mutationsController.applyMutant(mutation)) {
									for (Class<?> testClass : projectController
											.getTestClasses()) {
										controllerRunningTest
												.runTest(testClass);
									}

									// add result
									mutationsController.addResult(mutation,
											controllerRunningTest
													.getTestsFailed());
									controllerRunningTest.clearData();
								}
								// altera o ASTNode p o estado original
								mutation.undoActionMutationOperator();
							}

						}
						// altera o projeto para o estado original
						mutationsController.undoMutant();
					}
				}
			}
		}
	}
}
