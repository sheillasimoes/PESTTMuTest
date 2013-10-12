package domain.management;

import org.junit.runner.JUnitCore;

import domain.tests.instrument.JUnitTestRunListener;

public class ManagerRunningTests {
	private JUnitCore jUnitCore = null;

	public ManagerRunningTests() {
		jUnitCore = new JUnitCore();
		jUnitCore.addListener(new JUnitTestRunListener());
	}

	public void runTest(Class<?> classes) {
		jUnitCore.run(classes);
		// jUnitCore.getClass().getClassLoader()
	}
}
