package domain.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
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

	public void initialize(ASTNode node, IMarker[] markers) {
		managerMutations.initialize(node, markers);
	}

	public List<Mutation> getMutantsToDisplay(ASTNode node,
			List<Mutation> mutations, IMarker[] markers) {
		initialize(node, markers);
		return managerMutations.getMutantsToDisplay(mutations);
	}

	public boolean applyMutant(Mutation mutation) {
		return managerMutations.applyMutant(mutation);
	}

	public void undoMutant() {
		managerMutations.undoMutant();
	}

	public void addResult(Mutation mutation, List<String> data) {
		mutationTestResult.addResult(mutation, data);
	}

	public Set<Mutation> getMutantsTestResults() {
		return mutationTestResult.getMutantsTestResults();
	}

	public Set<Mutation> getLiveMutants() {
		return mutationTestResult.getLiveMutants();
	}

	public List<String> getFailedTests(Mutation mutation) {
		return mutationTestResult.getFailedTests(mutation);
	}

	public void incrementEquivalentMutants(Mutation mutation) {
		mutationTestResult.incrementEquivalentMutants(mutation);
	}

	public void decrementEquivalentMutants(Mutation mutation) {
		mutationTestResult.decrementEquivalentMutants(mutation);
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

	public int getNumberLiveMutants() {
		return mutationTestResult.getNumberLiveMutants();
	}

	public void calculateMutationScore() {
		mutationTestResult.calculateMutationScore();
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
