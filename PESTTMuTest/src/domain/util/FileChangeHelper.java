package domain.util;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.text.edits.MalformedTreeException;

public class FileChangeHelper {

	// public void changeFile(ICompilationUnit workingCopy, ASTRewrite rewrite,
	// CompilationUnit cUnit) {
	// changeICompilationUnit(workingCopy, rewrite, cUnit);
	// }

	public static void changeICompilationUnit(ICompilationUnit workingCopy,
			ASTRewrite rewrite, CompilationUnit cUnit) {

		try {
			// Modificar o buffer
			IBuffer buffer = ((IOpenable) workingCopy).getBuffer();
			buffer.setContents(cUnit.toString());

		} catch (JavaModelException | MalformedTreeException e) {
			// | BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean validateChangeInFile(ICompilationUnit workingCopy) {
		CompilationUnit parse = ASTUtil.parse(workingCopy);
		boolean flag = true;
		IProblem[] problems = parse.getProblems();
		if (problems.length == 0) {
			return flag;
		} else {
			for (IProblem problem : problems) {
				if (problem.isError()) {
					flag = false;
					return flag;
				}
			}
		}
		return flag;
	}

	public static boolean findCompilationErrors(ICompilationUnit unit) {
		IMarker[] markers = null;
		IProject project = InfoProjectHelper.getProject(unit);
		try {
			// build project
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
			// find compilation errors
			markers = project.findMarkers(
					IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, true,
					IResource.DEPTH_INFINITE);
			System.out.println(" size markers " + markers.length);
			for (IMarker marker : markers) {
				Integer severityType = (Integer) marker
						.getAttribute(IMarker.SEVERITY);
				if (severityType.intValue() == IMarker.SEVERITY_ERROR) {
					System.out.println("error " + marker.getType());
					return true;
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static void saveChange(ICompilationUnit workingCopy) {
		try {
			// reconcile
			workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);
			// Commit changes
			workingCopy.commitWorkingCopy(false, null);

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void discardWorkingCopy(ICompilationUnit workingCopy) {

		try {
			// Destroy working copy
			workingCopy.discardWorkingCopy();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void undoChangeICompilationUnit(ICompilationUnit workingCopy,
			ASTRewrite rewrite, CompilationUnit cUnit) {
		changeICompilationUnit(workingCopy, rewrite, cUnit);
		saveChange(workingCopy);
		try {
			// build project
			InfoProjectHelper.getProject(workingCopy).build(
					IncrementalProjectBuilder.INCREMENTAL_BUILD, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		discardWorkingCopy(workingCopy);

	}
}
