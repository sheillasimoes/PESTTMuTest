package domain.mutation.operators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import domain.constants.EnumModifierKeyword;
import domain.mutation.Mutation;
import domain.util.ASTChangeHelper;

public class AccessModifierChange implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		BodyDeclaration declaration = (BodyDeclaration) node;
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();
		boolean flagNoneModifier = false;

		// original modifier
		Modifier modifier;
		if (declaration.modifiers().size() == 0
				|| haveModifierNone((Modifier) declaration.modifiers().get(0))) {
			flagNoneModifier = true;
			modifier = null;

		} else {
			modifier = (Modifier) declaration.modifiers().get(0);
			// create mutantion with none modifier
			listMutants.add(new Mutation(declaration, this, null, modifier
					.getKeyword()));
		}

		for (EnumModifierKeyword modifierKeyword : EnumModifierKeyword.values()) {
			if (!flagNoneModifier
					&& !modifierKeyword.getModifierKeyword().equals(
							modifier.getKeyword())) {
				listMutants.add(new Mutation(declaration, this, modifierKeyword
						.getModifierKeyword(), modifier.getKeyword()));
			} else {
				listMutants.add(new Mutation(declaration, this, modifierKeyword
						.getModifierKeyword(), null));
			}
		}

		return listMutants;
	}

	private boolean haveModifierNone(Modifier modifier) {
		boolean flag = false;
		if (!modifier.getKeyword().equals(
				EnumModifierKeyword.PRIVATE_KEYWORD.getModifierKeyword())
				&& !modifier.getKeyword()
						.equals(EnumModifierKeyword.PUBLIC_KEYWORD
								.getModifierKeyword())
				&& !modifier.getKeyword().equals(
						EnumModifierKeyword.PROTECTED_KEYWORD
								.getModifierKeyword())) {
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
	public void applyOperator(Mutation mutation) {
		ASTChangeHelper.alterModifierKeyword(
				(BodyDeclaration) mutation.getASTNode(),
				(Modifier.ModifierKeyword) mutation.getData(),
				(Modifier.ModifierKeyword) mutation.getOriginalData());

	}

	@Override
	public void undoActionOperator(Mutation mutation) {
		ASTChangeHelper.alterModifierKeyword(
				(BodyDeclaration) mutation.getASTNode(),
				(Modifier.ModifierKeyword) mutation.getOriginalData(),
				(Modifier.ModifierKeyword) mutation.getData());
	}

	@Override
	public String toString() {
		return "Access Modifier Change";
	}

}
