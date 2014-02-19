package domain.mutation.testingProcess;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

import domain.mutation.Mutation;

public class MutationTestResult extends Observable {
	private int numberTotalMutants;
	private int numberEquivalentMutants;
	private int numberKilledMutants;
	private Map<Mutation, List<String>> result;
	private List<Mutation> liveMutants;

	public MutationTestResult() {
		result = new HashMap<Mutation, List<String>>();
		liveMutants = new LinkedList<Mutation>();
	}

	public void addResult(Mutation mutation, List<String> data) {
		if (data.size() > 0) {
			liveMutants.add(mutation);
		} else
			numberKilledMutants++;
		this.result.put(mutation, data);
		numberTotalMutants++;
		setChanged();
		notifyObservers();
	}

	public Set<Mutation> getMutantsTestResults() {
		return result.keySet();
	}

	/**
	 * @return the liveMutants
	 */
	public List<Mutation> getLiveMutants() {
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

	public double getMutationScore() {
		return 0.0;
	}

	public void incrementEquivalentMutants() {
		numberEquivalentMutants++;
	}

	public void decrementEquivalentMutants() {
		numberEquivalentMutants--;
	}

	public boolean isLiveMutant(Mutation mutation) {
		return liveMutants.contains(mutation);
	}

	public void deleteTestResult() {
		result.clear();
		liveMutants.clear();
		numberEquivalentMutants = 0;
		numberKilledMutants = 0;
		numberTotalMutants = 0;
		setChanged();
		notifyObservers();
	}
}
