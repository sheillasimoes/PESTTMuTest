package domain.management;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import domain.mutation.Mutation;
import domain.util.FileChangeHelper;

public class ManagerMutations {
	private AST ast = null;
	private CompilationUnit cUnit = null;
	private ICompilationUnit unit = null;
	private ICompilationUnit workingCopy = null;
	private ASTRewrite rewrite = null;
	private List<Mutation> mutations = null;

	/**
	 * 
	 * @param mutation
	 * @return
	 */
	public boolean generatingMutant(Mutation mutation) {
		mutation.applyMutationOperator(rewrite);
		boolean flag = validateMutant();
		return flag;
	}

	private boolean validateMutant() {
		try {
			workingCopy = unit.getWorkingCopy(null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// apply change in CompilationUnit
		FileChangeHelper.changeICompilationUnit(workingCopy, rewrite, cUnit);

		// checks if changes are valid
		boolean flag = FileChangeHelper.changeIsValid(workingCopy);
		return flag;
	}

	/**
	 * 
	 * @param node
	 * @param mutations
	 * @return
	 */
	public List<Mutation> getMutantsToDisplay(ASTNode node,
			List<Mutation> mutations) {
		initialize(node);

		for (Mutation mutation : mutations) {
			// generating mutant
			// verifies that the mutation generating errors
			if (generatingMutant(mutation)) {
				this.mutations.add(mutation);
			}
			// Destroy working copy
			FileChangeHelper.discardWorkingCopy(workingCopy);

			// undo change in CompilationUnit
			mutation.undoActionMutationOperator(rewrite);
		}

		return this.mutations;
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
		mutations = new LinkedList<Mutation>();

	}

	public void applyMutant(Mutation mutation) {
		initialize(mutation.getASTNode());
		try {
			System.out.println("unit antes de aplicar mutant \n"
					+ unit.getSource());
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cUnit antes de aplicar o mutant \n"
				+ cUnit.toString());
		// generating mutant
		// verifies that the mutation generating errors
		if (generatingMutant(mutation)) {
			FileChangeHelper.saveChange(workingCopy);
		} else {
			mutation.undoActionMutationOperator(rewrite);
		}

		FileChangeHelper.discardWorkingCopy(workingCopy);
		try {
			System.out.println("unit dps de aplicar mutant \n"
					+ unit.getSource());
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cUnit dps de aplicar o mutant \n"
				+ cUnit.toString());
	}

	public void undoMutant(Mutation mutation) {
		mutation.undoActionMutationOperator(rewrite);
		try {
			workingCopy = unit.getWorkingCopy(null);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileChangeHelper
				.undoChangeICompilationUnit(workingCopy, rewrite, cUnit);
		try {
			System.out.println("unit dps de undo mutant \n" + unit.getSource());
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("cUnit dps de undo o mutant \n" + cUnit.toString());
	}
}