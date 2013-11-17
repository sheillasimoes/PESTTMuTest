package domain.projects.listener;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;

import domain.projects.ManagerProjects;

public class MyResourceChangeReporter implements IResourceChangeListener {
	private ManagerProjects managerProjects;

	public MyResourceChangeReporter(ManagerProjects managerProjects) {
		this.managerProjects = managerProjects;
	}

	public void resourceChanged(IResourceChangeEvent event) {
		switch (event.getType()) {
		case IResourceChangeEvent.POST_CHANGE:
			try {
				event.getDelta().accept(new DeltaInformation(managerProjects));
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
	}
}
