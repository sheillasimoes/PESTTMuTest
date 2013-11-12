package domain.projects.listener;

import java.util.LinkedList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	private int count;
	private List<String> namesTestFailure;

	public JUnitTestRunListener() {
		namesTestFailure = new LinkedList<String>();
	}

	@Override
	public void testStarted(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testStarted(description);

	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		namesTestFailure.add(failure.getDescription().getDisplayName());
		count++;
		super.testFailure(failure);
	}

	@Override
	public void testAssumptionFailure(Failure failure) {
		namesTestFailure.add(failure.getDescription().getDisplayName());
		count++;
		super.testAssumptionFailure(failure);
	}

	public int getCount() {
		return count;
	}

	public void clearData() {
		namesTestFailure.clear();
	}

	public List<String> getNamesTestFailure() {
		return namesTestFailure;
	}
}
