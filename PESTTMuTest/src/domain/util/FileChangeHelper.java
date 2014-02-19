package domain.util;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
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

	public static boolean changeIsValid(ICompilationUnit workingCopy) {
		CompilationUnit parse = ASTUtil.parse(workingCopy);
		boolean flag = false;
		IProblem[] problems = parse.getProblems();

		if (problems.length == 0) {
			flag = true;
		} else {
			for (IProblem problem : problems) {
				// System.out.println("message problem " + problem.getMessage()
				// + " id problem" + problem.getID());
				if (problem.isError()) {
					return flag;
				} else {
					flag = true;
				}

			}
		}
		return flag;
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
