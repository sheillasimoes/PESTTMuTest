package domain.controller;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ui.constants.Messages;
import ui.dialog.ProcessMessage;

public class ControllerMain {

	public ControllerMain() {

	}

	public void runMutationOperators(Object[] elements) {
		// get workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();

		// get all projects
		IProject[] allProjects = root.getProjects();

		// if no projects
		if (allProjects.length == 0)
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_FIND_PROJECTS);

		else { // otherwise
			for (IProject project : allProjects) {
				scanProject(project);
			}
		}
	}

	private void scanProject(IProject project) {
		// get the Packages of the project
		IPackageFragment[] packageFragments = null;
		try {
			packageFragments = JavaCore.create(project).getPackageFragments();

			for (IPackageFragment mypackage : packageFragments) {
				// get source file
				if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
					for (ICompilationUnit unit : mypackage
							.getCompilationUnits()) {
						System.out.println(unit.toString());
						ASTParser parser = ASTParser.newParser(AST.JLS4);
						parser.setKind(ASTParser.K_COMPILATION_UNIT);
						parser.setSource(unit);
						parser.setResolveBindings(true);
						CompilationUnit parse = (CompilationUnit) parser
								.createAST(null);
						System.out.println(unit.getElementName() + " "
								+ unit.getSource() + " "
								+ unit.getElementType());
					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
