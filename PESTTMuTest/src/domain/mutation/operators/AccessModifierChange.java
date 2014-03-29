package domain.mutation.operators;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		// List com todas as mutacoes geradas
		List<Mutation> listMutants = new LinkedList<Mutation>();

		// save original modifier
		Modifier.ModifierKeyword modifier = null;
		int pos = getPosFirstModifier(declaration.modifiers());
		Map<Modifier.ModifierKeyword, Integer> hash = null;

		// create mutation with none modifier
		if (!isModifierNone(declaration.modifiers(), pos)) {
			modifier = ((Modifier) declaration.modifiers().get(pos))
					.getKeyword();
			hash = new HashMap<Modifier.ModifierKeyword, Integer>();
			hash.put(null, pos);
			listMutants.add(new Mutation(declaration, this, hash, modifier));
		}

		for (EnumModifierKeyword modifierKeyword : EnumModifierKeyword.values()) {
			if (modifier == null
					|| !modifierKeyword.getModifierKeyword().equals(modifier)) {
				hash = new HashMap<Modifier.ModifierKeyword, Integer>();
				hash.put(modifierKeyword.getModifierKeyword(), pos);
				listMutants
						.add(new Mutation(declaration, this, hash, modifier));
			}

		}

		return listMutants;
	}

	@SuppressWarnings("rawtypes")
	private int getPosFirstModifier(List listModifier) {
		int i = 0;
		for (Object obj : listModifier) {
			if (obj instanceof Modifier)
				return i;
			i++;
		}
		return i;
	}

	@SuppressWarnings("rawtypes")
	private boolean isModifierNone(List listModifier, int pos) {
		if (listModifier.size() == 0
				|| listModifier.size() == pos
				|| (!((Modifier) listModifier.get(pos)).getKeyword().equals(
						EnumModifierKeyword.PRIVATE_KEYWORD
								.getModifierKeyword())
						&& !((Modifier) listModifier.get(pos)).getKeyword()
								.equals(EnumModifierKeyword.PUBLIC_KEYWORD
										.getModifierKeyword()) && !((Modifier) listModifier
							.get(pos)).getKeyword().equals(
						EnumModifierKeyword.PROTECTED_KEYWORD
								.getModifierKeyword())))
			return true;
		return false;
	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		boolean flag = false;
		if ((node instanceof MethodDeclaration && !((MethodDeclaration) node)
				.isConstructor()) || node instanceof FieldDeclaration) {
			flag = true;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void applyOperator(Mutation mutation) {
		HashMap<Modifier.ModifierKeyword, Integer> setValues = (HashMap<Modifier.ModifierKeyword, Integer>) mutation
				.getData();
		Modifier.ModifierKeyword modifierKeyword = (Modifier.ModifierKeyword) setValues
				.keySet().toArray()[0];
		ASTChangeHelper.alterModifierKeyword(
				(BodyDeclaration) mutation.getASTNode(), modifierKeyword,
				(Modifier.ModifierKeyword) mutation.getOriginalData(),
				setValues.get(modifierKeyword));

	}

	@SuppressWarnings("unchecked")
	@Override
	public void undoActionOperator(Mutation mutation) {
		HashMap<Modifier.ModifierKeyword, Integer> setValues = (HashMap<Modifier.ModifierKeyword, Integer>) mutation
				.getData();
		Modifier.ModifierKeyword modifierKeyword = (Modifier.ModifierKeyword) setValues
				.keySet().toArray()[0];
		ASTChangeHelper.alterModifierKeyword(
				(BodyDeclaration) mutation.getASTNode(),
				(Modifier.ModifierKeyword) mutation.getOriginalData(),
				modifierKeyword, setValues.get(modifierKeyword));
	}

	@Override
	public String toString() {
		return "Access Modifier Change";
	}

}
