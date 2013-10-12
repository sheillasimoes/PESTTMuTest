package domain.util;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class InfoProjectHelper {

	public static IJavaProject getProject(ASTNode node) {
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		return unit.getJavaProject();
	}

	public static String getNameProject(ASTNode node) {
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		String nameProject = unit.getJavaProject().getElementName();
		return nameProject;
	}
}
