package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import domain.controller.ControllerRunningTest;
import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.groundString.GroundString;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.util.ASTUtil;
import domain.util.InfoProjectHelper;
import domain.util.ToStringASTNode;

public class TestGenerateRandomMutants {
	public void execute(Object[] elements,
			MutationOperatorsController operatorsController,
			MutationsController mutationsController,
			ProjectController projectController,
			GroundStringController groundStringController,
			ControllerRunningTest controllerRunningTest) {
		// create instance of operators
		operatorsController.createMutationOperators(elements);
		for (int j = 0; j < 5; j++) {
			System.out.println("Start!!! ");
			projectController.deleteAllCopiesProjects();
			// clear list of ground string
			groundStringController.initializeListGroundString();
			// create copy project
			projectController.createCopiesProjects();

			// create name file
			String nameFile = projectController.getProjectNames().get(0);
			Date date = new Date();
			SimpleDateFormat teste = new SimpleDateFormat("dd-MM-yy hh-mm");
			nameFile += teste.format(date);
			nameFile += "Random.txt";
			FileWriter fileWriter = null;
			PrintWriter gravarArq = null;
			try {
				fileWriter = new FileWriter(
						("C:\\Users\\Sheila Simoes\\Documents\\MEI(Mestrado em Engenharia Informática)\\Docs_PEI\\docs_2012-2013\\resultsTest\\" + nameFile));
				gravarArq = new PrintWriter(fileWriter);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// analyse project copy
			projectController.analyseProject(projectController
					.getProjectNames().get(0));
			// ground string
			List<GroundString> projectGS = groundStringController
					.getListGroundString();
			// write file
			gravarArq.printf("Project %s %nTotal ground string %d%n",
					projectController.getProjectNames().get(0),
					projectGS.size());
			int countMutants = 0;
			// start time to generate allmutants
			long startTime = System.currentTimeMillis();
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
					Random random = new Random();
					int i;
					int mutantGenerate = 0;
					long startTimeGeneratMutant = System.currentTimeMillis();
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
								mutantGenerate++;
								flag = true;
							}
							// altera o ASTNode p o estado original
							mutations.get(i).undoActionMutationOperator();
						}

					} while (!flag && listCount.size() < mutations.size());

					long stopTimeGeneratMutant = System.currentTimeMillis();
					// write file
					gravarArq
							.printf("GS %s %s %d, Mutation operator %s, number mutant %d, generation time %d %n",
									InfoProjectHelper.getFullyQualifiedName(gs
											.getGroundString()),
									ToStringASTNode.toString(gs
											.getGroundString()),
									ASTUtil.getLineNumber(gs.getGroundString()),
									operator.toString(),
									mutantGenerate,
									(stopTimeGeneratMutant - startTimeGeneratMutant));

				}
				// altera o projeto para o estado original
				mutationsController.undoMutant();
			}
			long stopTime = System.currentTimeMillis();
			gravarArq
					.printf("Total time create and analise project %d, Total mutants %d, time generation of mutants %d, NMPS %.2f",
							projectController.getTimeAnalyseProject(),
							countMutants,
							(stopTime - startTime),
							(countMutants / (((stopTime - startTime) + projectController
									.getTimeAnalyseProject()) * 0.001)));
			try {
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fim!!!");
		}
	}
}
