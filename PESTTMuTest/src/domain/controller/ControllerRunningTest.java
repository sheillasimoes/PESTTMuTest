package domain.controller;

import java.util.List;

import domain.projects.test.ManagerRunningTests;

public class ControllerRunningTest {
	private ManagerRunningTests managerRunningTests = null;

	public ControllerRunningTest() {
		managerRunningTests = new ManagerRunningTests();
	}

	public void runTest(Class<?> clazz) {
		managerRunningTests.runTest(clazz);
	}

	public List<String> getTestsFailed() {
		return managerRunningTests.getTestsFailed();
	}

	public void clearData() {
		managerRunningTests.clearData();
	}

	/**
	 * @return the countTime
	 */
	public long getCountTime() {
		return managerRunningTests.getCountTime();
	}

	/**
	 * @param countTime
	 *            the countTime to set
	 */
	public void setCountTime(long countTime) {
		managerRunningTests.setCountTime(countTime);
		;
	}
}
