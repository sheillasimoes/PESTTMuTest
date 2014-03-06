package domain.projects.listener;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	private List<String> testsFailure;
	int countIni = 0;
	int countEnd = 0;

	// private List<String> allTest;

	public JUnitTestRunListener() {
		testsFailure = new LinkedList<String>();
	}

	@Override
	public void testFinished(Description description) throws Exception {
		countEnd++;
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		testsFailure.add(new StringBuilder(failure.getDescription()
				.getClassName()
				+ " ("
				+ failure.getDescription().getMethodName() + ")").toString());
		System.out.println("failed " + testsFailure.get(0));
		System.out.println("exception exception" + failure.getException()
				+ " \n message" + failure.getMessage());
	}

	@Override
	public void testStarted(Description description) throws Exception {
		countIni++;
	}

	public void clearData() {
		testsFailure.clear();
		countIni = 0;
		countEnd = 0;
	}

	public int passed() {
		return testsFailure.size() > 0 ? 1 : 0;
	}

	public List<String> getTestsFailed() {
		System.out.println("test started " + countIni + "  test end "
				+ countEnd + " test fail.. " + testsFailure.size());
		return testsFailure;
	}
}
