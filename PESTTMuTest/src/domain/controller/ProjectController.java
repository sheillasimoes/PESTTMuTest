package domain.controller;

import domain.management.ManagerProjects;

public class ProjectController {
	private ManagerProjects managerProjects;

	public ProjectController(GroundStringController groundStringController) {
		managerProjects = new ManagerProjects(groundStringController);
	}

	public void analyseProject() {
		managerProjects.analyseProjects();
	}

	public void deleteAllCopiesProjects() {
		managerProjects.deleteAllCopiesProjects();
	}

}
