package domain.mutation.testingProcess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.mutation.Mutation;

public class MutationTestResult {
	private int numberMutants;
	private int numberEquivalentMutants;
	private int numberKilledMutants;
	private Map<Mutation, List<String>> result;

	public MutationTestResult() {
		result = new HashMap<Mutation, List<String>>();
	}

	public int getNumberMutants() {
		return numberMutants;
	}

	public int getNumberEquivalentMutants() {
		return numberEquivalentMutants;
	}

	public Map<Mutation, List<String>> getResult() {
		return result;
	}

	public void addResult(Mutation mutation, List<String> data) {
		this.result.put(mutation, data);
		numberKilledMutants += data.size();
		numberMutants++;
	}

	public int getNumberKilledMutants() {
		return numberKilledMutants;
	}

	public void incrementEquivalentMutant() {
		numberEquivalentMutants++;
	}

	public void decrementEquivalentMutant() {
		numberEquivalentMutants--;
	}

	public boolean isKilledMutant(Mutation mutation) {
		return result.get(mutation).size() > 0 ? true : false;
	}

	public void getLiveMutants() {

	}

	public void deleteTestResult() {
		result.clear();
		numberEquivalentMutants = 0;
		numberKilledMutants = 0;
		numberMutants = 0;

	}
}
