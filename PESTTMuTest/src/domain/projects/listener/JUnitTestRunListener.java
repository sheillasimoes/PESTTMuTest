package domain.projects.listener;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	private List<String> testsFailure;
	private List<String> allTest;

	public JUnitTestRunListener() {
		testsFailure = new LinkedList<String>();
		allTest = new LinkedList<String>();
	}

	@Override
	public void testFinished(Description description) throws Exception {
		allTest.add((description.getClassName() + " ("
				+ description.getMethodName() + ")"));
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		testsFailure.add((failure.getDescription().getClassName() + " ("
				+ failure.getDescription().getMethodName() + ")"));
	}

	public void clearData() {
		testsFailure.clear();
		allTest.clear();
	}

	public int passed() {
		return testsFailure.size() > 0 ? 1 : 0;
	}

	public List<String> getTestsFailed() {
		List<String> testsFailed = new LinkedList<String>();
		for (String testName : allTest) {
			if (!testsFailure.contains(testName)) {
				testsFailed.add(testName);
			}
		}
		return testsFailed;
	}
}
