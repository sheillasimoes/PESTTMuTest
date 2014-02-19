package domain.projects.listener;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	private int count = 0;
	private List<String> testsFailure;
	private List<String> allTest;

	public JUnitTestRunListener() {
		testsFailure = new LinkedList<String>();
		allTest = new LinkedList<String>();
	}

	@Override
	public void testFinished(Description description) throws Exception {
		allTest.add(description.getDisplayName());
		System.out.println("finish : " + description.getClassName()
				+ " method name " + description.getMethodName());
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		testsFailure.add(failure.getDescription().getDisplayName());
		System.out.println("failure : "
				+ failure.getDescription().getClassName() + " method name "
				+ failure.getDescription().getMethodName());
		count++;
	}

	public int getCount() {
		return count;
	}

	public void clearData() {
		testsFailure.clear();
		allTest.clear();
		count = 0;
	}

	public List<String> getTestsFailed() {
		List<String> testsFailed = new LinkedList<String>();
		for (String testName : allTest) {

			if (!testsFailure.contains(testName)) {
				testsFailed.add(testName);
			}
		}
		System.out.println("listnner run teste " + testsFailed + " killed "
				+ count);
		return testsFailed;
	}
}
