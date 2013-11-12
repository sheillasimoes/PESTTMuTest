package domain.controller;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.management.ManagerMutations;
import domain.mutation.DataResultProcessMutation;
import domain.mutation.DataRunningProcessMutation;
import domain.mutation.Mutation;
import domain.mutation.MutationTestResult;

public class MutationsController {
	private ManagerMutations managerMutations;
	private DataRunningProcessMutation dataProcessMutation;
	private DataResultProcessMutation dataResultMutation;

	public MutationsController(GroundStringController groundStringController) {
		managerMutations = new ManagerMutations();
		dataProcessMutation = new DataRunningProcessMutation(
				groundStringController);
		dataResultMutation = new DataResultProcessMutation();

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

	public Set<String> getSetNamesProjects() {
		return dataProcessMutation.getSetNamesProjects();
	}

	public List<ASTNode> getGroundStringFromProject(String nameProject) {
		return dataProcessMutation.getGroundStringFromProject(nameProject);
	}

	public List<MutationTestResult> getMutationTestResults() {
		return dataResultMutation.getResults();
	}

	public void addMutationTestResults(MutationTestResult testResult) {
		dataResultMutation.addResult(testResult);
	}

	public void clearResultMutation() {
		dataResultMutation.clearResults();
	}

}
