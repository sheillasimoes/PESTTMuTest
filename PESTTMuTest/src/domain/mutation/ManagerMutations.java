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
	 * 
	 * @param mutation
	 * @return
	 */
	public void generatingMutant(Mutation mutation) {
		mutation.applyMutationOperator();
		System.out.println("mutante " + mutation.toString());
		try {
			workingCopy = unit.getWorkingCopy(null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// apply change in CompilationUnit
		FileChangeHelper.changeICompilationUnit(workingCopy, rewrite, cUnit);
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
			generatingMutant(mutation);
			// verifies that the mutation generating errors in file change
			if (FileChangeHelper.validateChangeInFile(workingCopy)) {
				mutationResult.add(mutation);
			}
			// Destroy working copy
			FileChangeHelper.discardWorkingCopy(workingCopy);

			// undo change in CompilationUnit
			mutation.undoActionMutationOperator();
		}
		return mutationResult;
	}

	/**
	 * Initialize informations about node
	 * 
	 * @param node
	 */
	private void initialize(ASTNode node) {
		cUnit = (CompilationUnit) node.getRoot();
		unit = (ICompilationUnit) cUnit.getJavaElement();
		ast = cUnit.getAST();
		rewrite = ASTRewrite.create(ast);

	}

	public boolean applyMutant(Mutation mutation) {
		initialize(mutation.getASTNode());
		boolean flag = false;

		// generating mutant
		generatingMutant(mutation);
		// save change to the file
		FileChangeHelper.saveChange(workingCopy);
		// compile project to verifies that the mutation generating errors
		if (FileChangeHelper.findCompilationErrors(workingCopy)) {
			flag = false;
			// undo change in project
			mutation.undoActionMutationOperator();
			FileChangeHelper.undoChangeICompilationUnit(workingCopy, rewrite,
					cUnit);
		} else {
			flag = true;
		}
		FileChangeHelper.discardWorkingCopy(workingCopy);
		return flag;
	}

	public void undoMutant(Mutation mutation) {
		mutation.undoActionMutationOperator();
		try {
			workingCopy = unit.getWorkingCopy(null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileChangeHelper
				.undoChangeICompilationUnit(workingCopy, rewrite, cUnit);

	}
}