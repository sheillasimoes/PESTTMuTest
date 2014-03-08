package domain.mutation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import domain.util.FileChangeHelper;

public class ManagerMutations {
	private AST ast;
	private CompilationUnit cUnit;
	private ICompilationUnit unit;
	private ICompilationUnit workingCopy;
	private ASTRewrite rewrite;

	/**
	 * Initialize informations about node
	 * 
	 * @param node
	 */
	public void initialize(ASTNode node) {
		cUnit = (CompilationUnit) node.getRoot();
		unit = (ICompilationUnit) cUnit.getJavaElement();
		ast = cUnit.getAST();
		rewrite = ASTRewrite.create(ast);
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
	public List<Mutation> getMutantsToDisplay(ASTNode node,
			List<Mutation> mutations) {
		LinkedList<Mutation> mutationResult = new LinkedList<Mutation>();
		initialize(node);
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
		return FileChangeHelper.findCompilationErrors(workingCopy) ? false
				: true;
	}
}