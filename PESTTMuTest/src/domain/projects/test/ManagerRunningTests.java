package domain.projects.test;

import java.util.List;

import org.junit.runner.JUnitCore;

import domain.projects.listener.JUnitTestRunListener;

public class ManagerRunningTests {
	private JUnitCore junit;
	private JUnitTestRunListener listener;
	private long countTime = 0;

	public ManagerRunningTests() {
		junit = new JUnitCore();
		listener = new JUnitTestRunListener();
		junit.addListener(listener);
	}

	public void runTest(Class<?> clazz) {
		long startTime = System.currentTimeMillis();
		junit.run(clazz);
		long stopTime = System.currentTimeMillis();
		countTime += (stopTime - startTime);
	}

	public List<String> getTestsFailed() {
		return listener.getTestsFailed();
	}

	public void clearData() {
		listener.clearData();
	}

	/**
	 * @return the countTime
	 */
	public long getCountTime() {
		return countTime;
	}

	/**
	 * @param countTime the countTime to set
	 */
	public void setCountTime(long countTime) {
		this.countTime = countTime;
	}

}
