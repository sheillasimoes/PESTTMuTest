package domain.controller;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.IMutationOperators;
import domain.projects.ExploreProject;

public class ControllerMain {

	private MutationOperatorsController operatorsController;
	private List<IMutationOperators> listOperators;

	public ControllerMain() {
		operatorsController = new MutationOperatorsController();
	}

	public void runMutationOperators(Object[] elements) {
		operatorsController.setOperatorsSelected(elements);
		operatorsController.createMutationOperators();
		listOperators = operatorsController.getInstanceOfOperators();
		ExploreProject exploreProject = new ExploreProject();
		exploreProject.scanProject();
		System.out.println("Contrllermain tamanho list "
				+ exploreProject.getList().size());
		for (ASTNode node : exploreProject.getList()) {
			System.out.println("node:" + node.toString());
		}
	}

}
