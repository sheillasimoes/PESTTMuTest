package domain.tests.instrument;

import java.util.Observable;

import org.eclipse.jdt.junit.ITestRunListener;

import domain.events.EndTestsExecutionEvent;
import domain.events.FailedTestsExecutionEvent;

@SuppressWarnings("deprecation")
public class JUnitTestRunListener extends Observable implements
		ITestRunListener {

	@Override
	public void testEnded(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testFailed(int arg0, String arg1, String arg2, String arg3) {
		setChanged();
		notifyObservers(new FailedTestsExecutionEvent());
	}

	@Override
	public void testReran(String arg0, String arg1, String arg2, int arg3,
			String arg4) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testRunEnded(long arg0) {
		setChanged();
		notifyObservers(new EndTestsExecutionEvent());

	}

	@Override
	public void testRunStarted(int arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void testRunStopped(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void testRunTerminated() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testStarted(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
