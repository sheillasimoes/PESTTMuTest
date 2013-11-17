package domain.controller;

import java.util.List;

import domain.projects.test.ManagerRunningTests;

public class ControllerRunningTest {
	private ManagerRunningTests managerRunningTests = null;

	public ControllerRunningTest() {
		managerRunningTests = new ManagerRunningTests();
	}

	public void runTest(Class<?> classes) {
		managerRunningTests.runTest(classes);
	}

	public int getCount() {
		return managerRunningTests.getCount();
	}

	public void clearData() {
		managerRunningTests.clearData();
	}

	public List<String> getTestsFailure() {
		return managerRunningTests.getTestsFailure();
	}
}
