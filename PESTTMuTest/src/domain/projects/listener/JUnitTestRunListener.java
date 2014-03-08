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

	public JUnitTestRunListener() {
		testsFailure = new LinkedList<String>();
	}

	@Override
	public void testFinished(Description description) throws Exception {
		countEnd++;
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		if (failure.getException() instanceof AssertionError) {
			testsFailure.add(new StringBuilder(failure.getDescription()
					.getClassName()
					+ " ("
					+ failure.getDescription().getMethodName() + ")")
					.toString());
		}
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

	public List<String> getTestsFailed() {
		System.out.println("test started " + countIni + "  test end "
				+ countEnd + " test fail.. " + testsFailure.size());
		return testsFailure;
	}
}
