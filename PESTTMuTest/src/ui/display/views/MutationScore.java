package ui.display.views;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MutationScore extends ViewPart implements Observer {

	@Override
	public void createPartControl(Composite parent) {
		// Activator.getDefault().addObserverMutationTestResult(this);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("update!!");

	}

}
