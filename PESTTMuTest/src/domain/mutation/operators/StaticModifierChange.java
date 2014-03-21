package domain.mutation.operators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import domain.mutation.Mutation;
import domain.util.ASTChangeHelper;

public class StaticModifierChange implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		FieldDeclaration declaration = (FieldDeclaration) node;
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();

		if (containsStatic(declaration.modifiers()))
			listMutants.add(new Mutation(node, this, null,
					Modifier.ModifierKeyword.STATIC_KEYWORD));
		else
			listMutants.add(new Mutation(node, this,
					Modifier.ModifierKeyword.STATIC_KEYWORD, null));
		return listMutants;
	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		return node instanceof FieldDeclaration;
	}

	@Override
	public void applyOperator(Mutation mutation) {
		ASTChangeHelper.alterStaticModifier(
				(FieldDeclaration) mutation.getASTNode(),
				(Modifier.ModifierKeyword) mutation.getData());
	}

	@Override
	public void undoActionOperator(Mutation mutation) {
		ASTChangeHelper.alterStaticModifier(
				(FieldDeclaration) mutation.getASTNode(),
				(Modifier.ModifierKeyword) mutation.getOriginalData());
	}

	@SuppressWarnings("rawtypes")
	private boolean containsStatic(List modifiers) {
		for (Object obj : modifiers) {
			if (obj instanceof Modifier
					&& ((Modifier) obj).getKeyword().equals(
							Modifier.ModifierKeyword.STATIC_KEYWORD)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Static Modifier Change";
	}

}
