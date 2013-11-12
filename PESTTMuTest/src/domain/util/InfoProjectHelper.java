package domain.util;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import domain.constants.Description;

public class InfoProjectHelper {

	public static IJavaProject getProject(ASTNode node) {
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		return unit.getJavaProject();
	}

	public static String getProjectName(ASTNode node) {
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		String nameProject = unit.getJavaProject().getElementName();
		return nameProject;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static String getFullyQualifiedName(ASTNode node) {
		ICompilationUnit unit = (ICompilationUnit) ((CompilationUnit) node
				.getRoot()).getJavaElement();
		return unit.findPrimaryType().getFullyQualifiedName()
				.replaceAll(Description.PART_NAME_COPY_PROJECT, "");
	}
}
