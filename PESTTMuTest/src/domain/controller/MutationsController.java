package domain.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.ManagerMutations;
import domain.mutation.Mutation;
import domain.mutation.testingProcess.MutationTestResult;

public class MutationsController extends Observable {
	private ManagerMutations managerMutations;
	private MutationTestResult mutationTestResult;
	private Mutation selectedMutation;

	public MutationsController() {
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

	public Set<Mutation> getMutantsTestResults() {
		return mutationTestResult.getMutantsTestResults();
	}

	public List<Mutation> getLiveMutants() {
		return mutationTestResult.getLiveMutants();
	}

	public List<String> getFailedTests(Mutation mutation) {
		return mutationTestResult.getFailedTests(mutation);
	}

	public void incrementEquivalentMutants() {
		mutationTestResult.incrementEquivalentMutants();
	}

	public void decrementEquivalentMutants() {
		mutationTestResult.decrementEquivalentMutants();
	}

	public int getNumberTotalMutants() {
		return mutationTestResult.getNumberTotalMutants();
	}

	public int getNumberEquivalentMutants() {
		return mutationTestResult.getNumberEquivalentMutants();
	}

	public int getNumberKilledMutants() {
		return mutationTestResult.getNumberKilledMutants();
	}

	public double getMutationScore() {
		return mutationTestResult.getMutationScore();
	}

	public boolean isLiveMutant(Mutation mutation) {
		return mutationTestResult.isLiveMutant(mutation);
	}

	public void deleteTestResult() {
		mutationTestResult.deleteTestResult();
	}

	/**
	 * @return the selectedMutation
	 */
	public Mutation getSelectedMutation() {
		return selectedMutation;
	}

	/**
	 * @param selectedMutation
	 *            the selectedMutation to set
	 */
	public void setSelectedMutation(Mutation selectedMutation) {
		this.selectedMutation = selectedMutation;
		setChanged();
		notifyObservers();
	}

	public void addObserverMutationTestResult(Observer o) {
		mutationTestResult.addObserver(o);
	}

	public void deleteObserverMutationTestResult(Observer o) {
		mutationTestResult.deleteObserver(o);
	}
}
