package domain.projects.test;

import java.util.List;

import org.junit.runner.JUnitCore;

import domain.projects.listener.JUnitTestRunListener;

public class ManagerRunningTests {
	private JUnitCore junit;
	private JUnitTestRunListener listener;

	public ManagerRunningTests() {
		junit = new JUnitCore();
		listener = new JUnitTestRunListener();
		junit.addListener(listener);
	}

	public void runTest(Class<?> clazz) {
		junit.run(clazz);
	}

	public List<String> getTestsFailed() {
		return listener.getTestsFailed();
	}

	public void clearData() {
		listener.clearData();
	}
}
