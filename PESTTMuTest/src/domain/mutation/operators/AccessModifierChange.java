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
import domain.util.ASTUtil;
import domain.util.InfoProjectHelper;
import domain.util.ToStringASTNode;

public class AccessModifierChange implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		BodyDeclaration declaration = (BodyDeclaration) node;
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();
		boolean flagNoneModifier = false;

		// save original modifier
		Modifier modifier = null;
		System.out.println(InfoProjectHelper.getFullyQualifiedName(node) + " "
				+ ToStringASTNode.toString(node) + " "
				+ ASTUtil.getLineNumber(node));
		if (ToStringASTNode.toString(node).contains(
				"@Deprecated public static final char INDEXED_DELIM=")) {
			// nao tem modifiers
			if (declaration.modifiers().size() == 0
					|| haveModifierNone(declaration.modifiers())) {
				flagNoneModifier = true;
				modifier = null;

			} else {
				modifier = getFirstModifier(declaration.modifiers());
				// create mutantion with none modifier
				listMutants.add(new Mutation(declaration, this, null, modifier
						.getKeyword()));
			}

			for (EnumModifierKeyword modifierKeyword : EnumModifierKeyword
					.values()) {
				if (flagNoneModifier) {
					listMutants.add(new Mutation(declaration, this,
							modifierKeyword.getModifierKeyword(), null));
				} else if (!modifierKeyword.getModifierKeyword().equals(
						modifier.getKeyword())) {
					listMutants.add(new Mutation(declaration, this,
							modifierKeyword.getModifierKeyword(), modifier
									.getKeyword()));
				}

			}
		}
		return listMutants;
	}

	private boolean haveModifierNone(List listModifier) {
		boolean hasAnnotation = false;
		int i = 0;
		do {
			if (listModifier.get(i) instanceof Modifier) {
				if (!((Modifier) listModifier.get(i)).getKeyword().equals(
						EnumModifierKeyword.PRIVATE_KEYWORD
								.getModifierKeyword())
						&& !((Modifier) listModifier.get(i)).getKeyword()
								.equals(EnumModifierKeyword.PUBLIC_KEYWORD
										.getModifierKeyword())
						&& !((Modifier) listModifier.get(i)).getKeyword()
								.equals(EnumModifierKeyword.PROTECTED_KEYWORD
										.getModifierKeyword())) {
					return true;
				} else
					return false;
			} else {
				hasAnnotation = true;
			}
			i++;
		} while (i < listModifier.size());

		return hasAnnotation;
	}

	private Modifier getFirstModifier(List listModifier) {
		Modifier modifier = null;
		boolean flag = false;
		int i = 0;
		do {
			if (listModifier.get(i) instanceof Modifier) {
				flag = true;
				modifier = (Modifier) listModifier.get(i);
			}
			i++;
		} while (!flag && i < listModifier.size());
		return modifier;
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
