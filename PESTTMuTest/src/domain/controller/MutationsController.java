package domain.controller;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.management.ManagerMutations;
import domain.mutation.Mutation;

public class MutationsController {
	private ManagerMutations managerMutations = null;

	public MutationsController() {
		managerMutations = new ManagerMutations();
	}

	public List<Mutation> getMutantsToDisplay(ASTNode node,
			List<Mutation> mutations) {
		return managerMutations.getMutantsToDisplay(node, mutations);
	}

}
