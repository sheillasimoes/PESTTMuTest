package domain.projects.listener;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	private List<String> testsFailure;

	public JUnitTestRunListener() {
		testsFailure = new LinkedList<String>();
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
		super.testFailure(failure);
	}

	public void clearData() {
		testsFailure.clear();
	}

	public List<String> getTestsFailed() {
		return testsFailure;
	}
}
