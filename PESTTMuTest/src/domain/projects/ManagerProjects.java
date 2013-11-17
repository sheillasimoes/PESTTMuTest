package domain.projects;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import domain.constants.Messages;
import ui.dialog.ProcessMessage;
import domain.ast.visitors.SourceCodeVisitor;
import domain.constants.Description;
import domain.controller.GroundStringController;
import domain.projects.listener.MyResourceChangeReporter;
import domain.projects.test.TestClassesProjects;

public class ManagerProjects extends Observable {

	private ExploreProject exploreProject;
	private ManagerCopyProjects copyProjects;
	private SourceCodeVisitor sourceCodeVisitor;
	private TestClassesProjects testClassesProjects;
	private boolean projectsChanged;
	private String projectNameSelected;

	public ManagerProjects(GroundStringController groundStringController) {
		sourceCodeVisitor = new SourceCodeVisitor(groundStringController);
		testClassesProjects = new TestClassesProjects();
		exploreProject = new ExploreProject(sourceCodeVisitor,
				testClassesProjects);
		copyProjects = new ManagerCopyProjects();
		projectsChanged = false;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				new MyResourceChangeReporter(this));

	}

	/**
	 * create copies of the Java projects
	 */
	public void createCopiesProjects() {
		if (validateStateCopies()) {
			IProject[] allProjects = exploreProject.getProjects();

			for (IProject project : allProjects) {
				if (validateProject(project)) {
					copyProjects.createCopyProject(project);
				}
			}
		}
	}

	public void analyseProject(String projectNameSelected) {
		if (isProjectsChanged()) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.PROJECT_CHANGE);
		} else {
			exploreProject.analyseProject(copyProjects
					.getCopyProject(projectNameSelected));
		}
	}

	public List<String> getProjectNames() {
		List<String> projects = new LinkedList<String>();
		for (String name : copyProjects.getListNameCopies()) {
			projects.add(name
					.replaceAll(Description.PART_NAME_COPY_PROJECT, ""));
		}

		return projects;
	}

	public List<Class<?>> getTestClasses() {
		IJavaProject project = JavaCore.create(copyProjects
				.getCopyProject(projectNameSelected));
		List<Class<?>> files = testClassesProjects.getTestClasses(project);
		return files;
	}

	/**
	 * @return the projectsChanged
	 */
	public boolean isProjectsChanged() {
		return projectsChanged;
	}

	public boolean hasTestClasses(String nameProject) {
		return testClassesProjects.hasTestClasses();
	}

	public boolean isCopyProject(String nameProject) {
		return copyProjects.isCopyProject(nameProject);
	}

	public void deleteAllCopiesProjects() {
		copyProjects.deleteAllCopiesProjects();
	}

	public String getProjectNameSelected() {
		return projectNameSelected;
	}

	public void setProjectNameSelected(String projectNameSelected) {
		this.projectNameSelected = projectNameSelected;
		setChanged();
		notifyObservers();
	}

	/**
	 * @param projectsChanged
	 *            the projectsChanged to set
	 */
	public void setProjectsChanged(boolean projectsChanged) {
		this.projectsChanged = projectsChanged;
	}

	public void addObserverCopyProject(Observer o) {
		copyProjects.addObserver(o);
	}

	public void deleteObserverCopyProject(Observer o) {
		copyProjects.deleteObservers();
	}

	private boolean validateStateCopies() {
		// validate state of copies and projects
		if (isProjectsChanged()) {
			copyProjects.deleteAllCopiesProjects();
			testClassesProjects.deleteListTestClasses();
			setProjectsChanged(false);
			return true;
		}

		return false;
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
}
