package domain.controller;

import java.util.List;
import java.util.Observer;

import org.eclipse.core.resources.IMarker;

import domain.projects.ManagerProjects;

public class ProjectController {
	private ManagerProjects managerProjects;

	public ProjectController(GroundStringController groundStringController) {
		managerProjects = new ManagerProjects(groundStringController);
	}

	public void analyseProject(String nameProject) {
		managerProjects.analyseProject(nameProject);
	}

	public void deleteAllCopiesProjects() {
		managerProjects.deleteAllCopiesProjects();
	}

	public void createCopiesProjects() {
		managerProjects.createCopiesProjects();
	}

	public boolean hasTestClasses(String nameProject) {
		return managerProjects.hasTestClasses(nameProject);
	}

	public List<String> getProjectNames() {
		return managerProjects.getProjectNames();
	}

	public List<Class<?>> getTestClasses() {
		return managerProjects.getTestClasses();
	}

	public String getProjectNameSelected() {
		return managerProjects.getProjectNameSelected();
	}

	public void setProjectNameSelected(String projectName) {
		managerProjects.setProjectNameSelected(projectName);
	}

	public IMarker[] getMarkers() {
		return managerProjects.getMarkers();
	}

	public void addObserverManagerProjects(Observer o) {
		managerProjects.addObserver(o);
	}

	public void deleteObserverManagerProjects(Observer o) {
		managerProjects.deleteObserver(o);
	}

	public void addObserverCopyProject(Observer o) {
		managerProjects.addObserverCopyProject(o);
	}

	public void deleteObserverCopyProject(Observer o) {
		managerProjects.deleteObserverCopyProject(o);
	}

	public long getTimeAnalyseProject() {
		return managerProjects.getTimeAnalyseProject();
	}
}
