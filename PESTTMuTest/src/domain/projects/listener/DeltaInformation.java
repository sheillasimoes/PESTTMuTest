package domain.projects.listener;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;

import domain.projects.ManagerProjects;

public class DeltaInformation implements IResourceDeltaVisitor {
	private ManagerProjects managerProjects;

	public DeltaInformation(ManagerProjects managerProjects) {
		this.managerProjects = managerProjects;
	}

	public boolean visit(IResourceDelta delta) {
		IResource res = delta.getResource();
		switch (delta.getKind()) {
		case IResourceDelta.CHANGED:
			if (res instanceof IProject
					&& !managerProjects.isCopyProject(delta.getFullPath()
							.toString())) {
				managerProjects.setProjectsChanged(true);
			}
			break;
		}
		return true; // visit the children
	}

}
