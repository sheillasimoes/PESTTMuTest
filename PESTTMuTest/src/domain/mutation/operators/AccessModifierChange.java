package domain.mutation.operators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import domain.constants.EnumModifierKeyword;
import domain.mutation.Mutation;

public class AccessModifierChange implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		BodyDeclaration declaration = null;
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();

		// initialize ASTNode to work
		if (node instanceof MethodDeclaration) {
			declaration = (MethodDeclaration) node;
		} else if (node instanceof FieldDeclaration) {
			declaration = (FieldDeclaration) node;
		}

		// get modifiers
		List<?> modifiers = declaration.modifiers();

		// create mutations
		Mutation mutation = null;

		if (modifiers.size() == 0
				|| haveModifierNone((Modifier) modifiers.get(0))) {
			for (EnumModifierKeyword modifierKeyword : EnumModifierKeyword
					.values()) {
				mutation = new Mutation(declaration, this,
						modifierKeyword.getModifierKeyword(), null);
				listMutants.add(mutation);

			}
		} else {
			// original modifier
			Modifier modifier = (Modifier) modifiers.get(0);
			// create mutantion with none modifier
			mutation = new Mutation(declaration, this, null,
					modifier.getKeyword());
			listMutants.add(mutation);
//			for (EnumModifierKeyword modifierKeyword : EnumModifierKeyword
//					.values()) {
//				(!modifierKeyword.getAssignmentOperator().equals(
//						assignmentNode.getOperator())) {
//			}
		}

		return listMutants;
	}

	private boolean haveModifierNone(Modifier modifier) {
		boolean flag = false;
		if (!modifier.getKeyword().equals(
				EnumModifierKeyword.PRIVATE_KEYWORD.getModifierKeyword()
						.equals(modifier.getKeyword()))
				&& !modifier.getKeyword().equals(
						EnumModifierKeyword.PUBLIC_KEYWORD.getModifierKeyword()
								.equals(modifier.getKeyword()))
				&& !modifier.getKeyword().equals(
						EnumModifierKeyword.PROTECTED_KEYWORD
								.getModifierKeyword().equals(
										modifier.getKeyword()))) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		boolean flag = false;
		if (node instanceof MethodDeclaration
				|| node instanceof FieldDeclaration) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void applyOperator(Mutation mutation, ASTRewrite rewrite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void undoActionOperator(Mutation mutation, ASTRewrite rewrite) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "Access Modifier Change";
	}

}
