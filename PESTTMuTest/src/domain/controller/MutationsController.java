package domain.controller;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.ManagerMutations;
import domain.mutation.Mutation;
import domain.mutation.testingProcess.MutationTestResult;

public class MutationsController {
	private ManagerMutations managerMutations;
	private MutationTestResult mutationTestResult;

	public MutationsController(GroundStringController groundStringController) {
		managerMutations = new ManagerMutations();
		mutationTestResult = new MutationTestResult();
	}

	public List<Mutation> getMutantsToDisplay(ASTNode node,
			List<Mutation> mutations) {
		return managerMutations.getMutantsToDisplay(node, mutations);
	}

	public boolean applyMutant(Mutation mutation) {
		return managerMutations.applyMutant(mutation);
	}

	public void undoMutant(Mutation mutation) {
		managerMutations.undoMutant(mutation);
	}

	public void addResult(Mutation mutation, List<String> data) {
		mutationTestResult.addResult(mutation, data);
	}

	public void deleteTestResult() {
		mutationTestResult.deleteTestResult();
	}
}
