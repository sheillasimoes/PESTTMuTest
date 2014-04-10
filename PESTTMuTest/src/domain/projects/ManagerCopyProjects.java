package domain.projects;

import java.util.LinkedList;
import java.util.Observable;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

import domain.constants.Description;

public class ManagerCopyProjects extends Observable {
	private LinkedList<String> listNameCopies;
	private IWorkspaceRoot root;

	public ManagerCopyProjects() {
		listNameCopies = new LinkedList<String>();
		root = ResourcesPlugin.getWorkspace().getRoot();
	}

	/**
	 * Add in workspace a copy of project
	 * 
	 * @param project
	 * @requires project != null
	 */
	public void createCopyProject(IProject project) {
		if (isCopyProject(project.getName())) {
			// deleteCopy(project);
		} else {
			// name copy of the project
			String copyName = project.getName()
					+ Description.PART_NAME_COPY_PROJECT;

			// checks if there are any old copies of the project
			if (existCopyProject(project)) {
				deleteCopy(root.getProject(copyName));
			}

			// create a new copy of project
			IProgressMonitor progressMonitor = new NullProgressMonitor();
			String fullPathCopy = project.getFullPath().toString()
					+ Description.PART_NAME_COPY_PROJECT;

			IPath newPath = new Path(fullPathCopy);
			try {
				// copy the project
				project.copy(newPath, true, progressMonitor);

				// hidden the copy of the project
				// root.getProject(copyName).setHidden(true);
				root.refreshLocal(IResource.DEPTH_ZERO, progressMonitor);

			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// add to list the name of the copy
			setListNameCopies(copyName);
		}

	}

	public IProject getCopyProject(String nameProject) {
		return root.getProject(nameProject
				.concat(Description.PART_NAME_COPY_PROJECT));
	}

	/**
	 * Delete all copies projects
	 */
	public void deleteAllCopiesProjects() {
		for (String name : listNameCopies) {
			IProject project = root.getProject(name);
			deleteCopy(project);
		}
		listNameCopies.clear();
	}

	/**
	 * 
	 * @param nameProject
	 * @return
	 */
	public boolean isCopyProject(String nameProject) {
		return nameProject.contains(Description.PART_NAME_COPY_PROJECT);
	}

	/**
	 * @return the listNameCopies
	 */
	public LinkedList<String> getListNameCopies() {
		return listNameCopies;
	}

	/**
	 * @param listNameCopies
	 *            the listNameCopies to set
	 */
	public void setListNameCopies(String nameCopy) {
		listNameCopies.add(nameCopy);
		setChanged();
		notifyObservers();
	}

	private boolean existCopyProject(IProject project) {
		String oldNameCopy = project.getName().concat(
				Description.PART_NAME_COPY_PROJECT);
		IProject oldCopy = root.getProject(oldNameCopy);

		return root.exists(oldCopy.getFullPath());
	}

	/**
	 * 
	 * @param project
	 */
	private void deleteCopy(IProject copyProject) {
		try {
			copyProject.delete(IResource.DEPTH_INFINITE, null);
			root.refreshLocal(IResource.DEPTH_ZERO, null);

			// verifica se o nome do projeto apagado esta na lista
			if (listNameCopies.contains(copyProject.getName())) {
				listNameCopies.remove(copyProject.getName());
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
