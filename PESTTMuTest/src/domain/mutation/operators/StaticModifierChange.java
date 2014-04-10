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
		int pos = getPosStaticModifier(declaration.modifiers());
		if (isStaticMofifier(declaration.modifiers(), pos))
			listMutants.add(new Mutation(node, this, pos,
					Modifier.ModifierKeyword.STATIC_KEYWORD));
		else
			listMutants.add(new Mutation(node, this, pos, null));
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
				(int) mutation.getData(),
				(Modifier.ModifierKeyword) mutation.getOriginalData());
	}

	@Override
	public void undoActionOperator(Mutation mutation) {
		ASTChangeHelper.alterStaticModifier(
				(FieldDeclaration) mutation.getASTNode(),
				(int) mutation.getData(),
				(Modifier.ModifierKeyword) mutation.getOriginalData());
	}

	@SuppressWarnings("rawtypes")
	private int getPosStaticModifier(List modifiers) {
		int i = 0;
		for (Object obj : modifiers) {
			if (obj instanceof Modifier
					&& (((Modifier) obj).isStatic() || ((Modifier) obj)
							.isFinal())) {
				return i;
			}
			i++;
		}
		return i;
	}

	@SuppressWarnings("rawtypes")
	private boolean isStaticMofifier(List modifiers, int pos) {
		if (pos < modifiers.size()
				&& ((Modifier) modifiers.get(pos)).isStatic()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Static Modifier Change";
	}

}
