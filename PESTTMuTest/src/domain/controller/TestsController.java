package domain.controller;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.junit.JUnitCore;

import domain.tests.instrument.JUnitTestRunListener;

public class TestsController implements Observer {
	private JUnitTestRunListener listener;

	public TestsController() {
		addListener();
		System.out.println("construtor testsController");
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@SuppressWarnings("deprecation")
	public void addListener() {
		listener = new JUnitTestRunListener();
		listener.addObserver(this);
		JUnitCore.addTestRunListener(listener);
		System.out.println("addListener");
	}

	public void removeListener() {
		listener.deleteObserver(this);
	}

}
