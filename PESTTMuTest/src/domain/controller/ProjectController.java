package domain.controller;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.management.ManagerProjects;

public class ProjectController {
	private ManagerProjects managerProjects;

	public ProjectController(GroundStringController groundStringController) {
		managerProjects = new ManagerProjects(groundStringController);
	}

	public void analyseProject() {
		managerProjects.analyseProjects();
	}

	public boolean hasTestClasses(String nameProject) {
		return managerProjects.hasTestClasses(nameProject);
	}

	public List<Class<?>> getTestClasses(ASTNode node) {
		return managerProjects.getTestClasses(node);
	}
}
