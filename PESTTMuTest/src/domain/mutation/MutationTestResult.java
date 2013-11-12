package domain.mutation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MutationTestResult {
	private String projectName;
	private int numberMutants;
	private int numberEquivalentMutants;
	private List<Map<Mutation, List<String>>> result;

	public MutationTestResult(String projectName) {
		this.projectName = projectName;
		result = new LinkedList<Map<Mutation, List<String>>>();
	}

	public String getProjectName() {
		return projectName;
	}

	public int getNumberMutants() {
		return numberMutants;
	}

	public int getNumberEquivalentMutants() {
		return numberEquivalentMutants;
	}

	public List<Map<Mutation, List<String>>> getResult() {
		return result;
	}

	public void addResult(Map<Mutation, List<String>> result) {
		this.result.add(result);
		numberMutants++;
	}

	public void incrementEquivalentMutant() {
		numberEquivalentMutants++;
	}

	public void decrementEquivalentMutant() {
		numberEquivalentMutants--;
	}

}
