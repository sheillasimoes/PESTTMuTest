package domain.projects;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import domain.ast.visitors.SourceCodeVisitor;
import domain.util.ASTUtil;

public class ExploreProject {
	private SourceCodeVisitor sourceCodeVisitor;
	private TestClassesProjects testClassesProjects;

	public ExploreProject(SourceCodeVisitor sourceCodeVisitor,
			TestClassesProjects testClassesProjects) {
		this.sourceCodeVisitor = sourceCodeVisitor;
		this.testClassesProjects = testClassesProjects;
	}

	/**
	 * Get the project of worksapce
	 * 
	 * @return
	 */
	public IProject[] getProjects() {
		// get workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// get all projects in the workspace
		IProject[] allProjects = root.getProjects();

		return allProjects;
	}

	public void analyseProject(IProject project) {
		// get the Packages of the project
		IPackageFragment[] packageFragments = null;

		try {
			packageFragments = JavaCore.create(project).getPackageFragments();
			for (IPackageFragment mypackage : packageFragments) {
				// get source file
				if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
					// get .java source file
					createAST(mypackage, project.getName());

				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public IProject getProject(ASTNode node) {
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		IJavaProject javaProject = unit.getJavaProject();
		return javaProject.getProject();
	}

	/**
	 * Create the AST for the ICompilationUnits
	 * 
	 * @param mypackage
	 */
	private void createAST(IPackageFragment mypackage, String nameProject) {
		try {
			for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
				// create the AST for the ICompilationUnits
				CompilationUnit parse = ASTUtil.parse(unit);

				if (!testClassesProjects.isTestClass(parse, nameProject,
						unit.getElementName())) {
					parse.accept(sourceCodeVisitor);
				}

			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
