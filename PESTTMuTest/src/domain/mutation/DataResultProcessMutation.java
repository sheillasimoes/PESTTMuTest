package domain.mutation;

import java.util.LinkedList;
import java.util.List;

public class DataResultProcessMutation {
	private List<MutationTestResult> testResults;

	public DataResultProcessMutation() {
		testResults = new LinkedList<MutationTestResult>();

	}

	public void addResult(MutationTestResult result) {
		testResults.add(result);
	}

	public void clearResults() {
		testResults.clear();
	}

	public List<MutationTestResult> getResults() {
		return testResults;
	}

}
