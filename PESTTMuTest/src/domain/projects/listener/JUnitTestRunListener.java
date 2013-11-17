package domain.projects.listener;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	private int count;
	private List<String> testsFailure;

	public JUnitTestRunListener() {
		testsFailure = new LinkedList<String>();
	}

	@Override
	public void testStarted(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testStarted(description);

	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		testsFailure.add(failure.getDescription().getDisplayName());
		count++;
		super.testFailure(failure);
	}

	@Override
	public void testAssumptionFailure(Failure failure) {
		testsFailure.add(failure.getDescription().getDisplayName());
		count++;
		super.testAssumptionFailure(failure);
	}

	public int getCount() {
		return count;
	}

	public void clearData() {
		testsFailure.clear();
	}

	public List<String> getTestsFailure() {
		return testsFailure;
	}
}
