package domain.mutation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import domain.util.FileChangeHelper;

public class ManagerMutations {
	private CompilationUnit cUnit;
	private ICompilationUnit unit;
	private ICompilationUnit workingCopy;
	private ASTRewrite rewrite;
	private IMarker[] markers;
	private IProgressMonitor progressMonitor;

	/**
	 * Initialize informations about node
	 * 
	 * @param node
	 */
	public void initialize(ASTNode node, IMarker[] markers) {
		cUnit = (CompilationUnit) node.getRoot();
		unit = (ICompilationUnit) cUnit.getJavaElement();
		// rewrite = ASTRewrite.create(ast);
		this.markers = markers;
		try {
			workingCopy = unit.getWorkingCopy(null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param node
	 * @param mutations
	 * @return
	 */
	public List<Mutation> getMutantsToDisplay(List<Mutation> mutations) {
		LinkedList<Mutation> mutationResult = new LinkedList<Mutation>();
		for (Mutation mutation : mutations) {
			// generating mutant
			if (generatingMutant(mutation)) {
				mutationResult.add(mutation);
			}
			// undo change in CompilationUnit
			mutation.undoActionMutationOperator();
		}
		FileChangeHelper
				.undoChangeICompilationUnit(workingCopy, rewrite, cUnit);
		return mutationResult;
	}

	public boolean applyMutant(Mutation mutation) {
		return generatingMutant(mutation);
	}

	public void undoMutant() {
		FileChangeHelper
				.undoChangeICompilationUnit(workingCopy, rewrite, cUnit);

	}

	/**
	 * 
	 * @param mutation
	 * @return
	 */
	private boolean generatingMutant(Mutation mutation) {
		mutation.applyMutationOperator();
		// apply change in CompilationUnit
		FileChangeHelper.changeICompilationUnit(workingCopy, rewrite, cUnit);

		// save change to the file
		FileChangeHelper.saveChange(workingCopy);

		// compile project to verifies that the mutation generating errors
		return FileChangeHelper.findCompilationErrors(workingCopy,
				progressMonitor, markers) ? false : true;
		// return true;
	}
}