package domain.util;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.text.edits.MalformedTreeException;

public class FileChangeHelper {

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

	public static boolean findCompilationErrors(ICompilationUnit unit,
			IProgressMonitor progressMonitor, IMarker[] markersIni) {
		IMarker[] markers = null;
		IProject project = InfoProjectHelper.getProject(unit);

		try {
			// build project
			project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD,
					progressMonitor);
			// find compilation errors
			markers = project.findMarkers(
					IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, true,
					IResource.DEPTH_INFINITE);
			if (markersIni.length == markers.length) {
				return false;
			} else
				for (IMarker marker : markers) {
					Integer severityType = (Integer) marker
							.getAttribute(IMarker.SEVERITY);
					if (severityType.intValue() == IMarker.SEVERITY_ERROR) {
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
		discardWorkingCopy(workingCopy);

	}
}
