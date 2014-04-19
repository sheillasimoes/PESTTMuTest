package domain.mutation.testingProcess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import domain.constants.Description;
import domain.mutation.Mutation;

public class MutationTestResult extends Observable {
	private double mutationScore;
	private int numberTotalMutants;
	private int numberEquivalentMutants;
	private int numberKilledMutants;
	private Map<Mutation, List<String>> result;
	private Set<Mutation> liveMutants;

	public MutationTestResult() {
		result = new HashMap<Mutation, List<String>>();
		liveMutants = new HashSet<Mutation>();
	}

	public void addResult(Mutation mutation, List<String> data) {
		if (data.size() == 0) {
			liveMutants.add(mutation);
		} else {
			numberKilledMutants++;
		}
		result.put(mutation, new LinkedList<>(data));
		numberTotalMutants++;
		setChanged();
		notifyObservers(Description.MUTATION_RESULT);
	}

	public Set<Mutation> getMutantsTestResults() {
		return result.keySet();
	}

	/**
	 * @return the liveMutants
	 */
	public Set<Mutation> getLiveMutants() {
		return liveMutants;
	}

	public List<String> getFailedTests(Mutation mutation) {
		return result.get(mutation);
	}

	public int getNumberTotalMutants() {
		return numberTotalMutants;
	}

	public int getNumberEquivalentMutants() {
		return numberEquivalentMutants;
	}

	public int getNumberKilledMutants() {
		return numberKilledMutants;
	}

	public int getNumberLiveMutants() {
		return liveMutants.size();
	}

	public void calculateMutationScore() {
		if (result.size() > 0
				&& ((result.size() - numberEquivalentMutants)) > 0) {
			mutationScore = ((double) numberKilledMutants / (double) (numberTotalMutants - numberEquivalentMutants))
					* (double) 100;
			System.out
					.println("haga "
							+ (((double) numberKilledMutants / (double) (numberTotalMutants - numberEquivalentMutants)) * (double) 100));
			setChanged();
			notifyObservers(Description.CALCULATE_MUTATION_SCORE);
		}
	}

	public double getMutationScore() {
		return mutationScore;
	}

	public void incrementEquivalentMutants(Mutation mutation) {
		if (liveMutants.contains(mutation)) {
			numberEquivalentMutants++;
		}
	}

	public void decrementEquivalentMutants(Mutation mutation) {
		if (liveMutants.contains(mutation)) {
			numberEquivalentMutants--;
		}
	}

	public boolean isLiveMutant(Mutation mutation) {
		return liveMutants.contains(mutation);
	}

	public void deleteTestResult() {
		result.clear();
		liveMutants.clear();
		mutationScore = 0.0;
		numberEquivalentMutants = 0;
		numberKilledMutants = 0;
		numberTotalMutants = 0;
		setChanged();
		notifyObservers(Description.MUTATION_RESULT);
	}
}
