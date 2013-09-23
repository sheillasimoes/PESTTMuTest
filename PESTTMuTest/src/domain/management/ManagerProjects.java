package domain.management;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import domain.ast.visitors.SourceCodeVisitor;
import domain.controller.GroundStringController;
import domain.projects.ExploreProject;
import domain.projects.ManagerCopyProjects;
import domain.projects.listener.MyResourceChangeReporter;

public class ManagerProjects {

	private ExploreProject exploreProject = null;
	private ManagerCopyProjects copyProjects = null;
	private SourceCodeVisitor sourceCodeVisitor = null;
	private boolean projectsChanged = false;

	public ManagerProjects(GroundStringController groundStringController) {
		sourceCodeVisitor = new SourceCodeVisitor(groundStringController);
		exploreProject = new ExploreProject(sourceCodeVisitor);
		copyProjects = new ManagerCopyProjects();
		projectsChanged = false;

		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				new MyResourceChangeReporter(this));

	}

	public void analyseProjects() {
		// validate state of copies and projects
		if (copyProjects.getListNameCopies().size() > 0 && isProjectsChanged()) {
			copyProjects.deleteAllCopiesProjects();
			createCopiesProjects();
		} else if (copyProjects.getListNameCopies().size() == 0) {
			createCopiesProjects();
		}

		// get copies projects to scan
		IProject[] copiesProjects = copyProjects.getCopiesProjects();
		for (IProject copy : copiesProjects) {
			exploreProject.analyseProject(copy);
		}

	}

	/**
	 * create copies of the Java projects
	 */
	private void createCopiesProjects() {
		IProject[] allProjects = exploreProject.getProjects();
		for (IProject project : allProjects) {
			if (validateProject(project)) {
				copyProjects.createCopyProject(project);
			}
		}
	}

	/**
	 * Valida se o projeto esta aberto e se e javanature
	 * 
	 * @param project
	 * @return
	 */
	private boolean validateProject(IProject project) {
		boolean flag = false;
		// if project is open
		if (project.isOpen()) {
			// Check if we have a Java project
			try {
				if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
					flag = true;
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;

	}

	/**
	 * @return the projectsChanged
	 */
	public boolean isProjectsChanged() {
		return projectsChanged;
	}

	/**
	 * @param projectsChanged
	 *            the projectsChanged to set
	 */
	public void setProjectsChanged(boolean projectsChanged) {
		this.projectsChanged = projectsChanged;
	}

	public boolean isCopyProject(String nameProject) {
		return copyProjects.isCopyProject(nameProject);
	}

	public void deleteAllCopiesProjects() {
		copyProjects.deleteAllCopiesProjects();
	}

}
