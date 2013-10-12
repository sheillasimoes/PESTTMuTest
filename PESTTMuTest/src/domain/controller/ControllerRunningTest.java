package domain.controller;

import domain.management.ManagerRunningTests;

public class ControllerRunningTest {
	private ManagerRunningTests managerRunningTests = null;

	public ControllerRunningTest() {
		managerRunningTests = new ManagerRunningTests();
	}

	public void runTest(Class<?> classes) {
		managerRunningTests.runTest(classes);
	}
}
