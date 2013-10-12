package domain.controller;

import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.management.ManagerMutations;
import domain.mutation.DataRunningProcessMutation;
import domain.mutation.Mutation;

public class MutationsController {
	private ManagerMutations managerMutations = null;
	private DataRunningProcessMutation dataProcessMutation = null;

	public MutationsController(GroundStringController groundStringController) {
		managerMutations = new ManagerMutations();
		dataProcessMutation = new DataRunningProcessMutation(
				groundStringController);

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

}
