package domain.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

import domain.constants.Description;

public class InfoProjectHelper {

	public static IProject getProject(ICompilationUnit unit) {
		return (IProject) unit.getJavaProject().getResource();
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
				.replaceAll(Description.PART_NAME_COPY_PROJECT, "")
				+ ".java";
	}
}
