package domain.util;

import java.util.List;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ASTChangeHelper {

	public static void alterAssignmentOperator(Assignment node,
			Assignment.Operator operator) {
		node.setOperator(operator);
	}

	public static void alterThisKeywordDeletion(Assignment node,
			Expression expression) {
		node.setLeftHandSide(expression);
	}

	public static void alterVariableInitializationDeletion(
			VariableDeclarationFragment fragment) {
		fragment.setInitializer(null);
	}

	public static void undoVariableInitializationDeletion(
			VariableDeclarationFragment fragemnet, Expression expression) {
		fragemnet.setInitializer(expression);
	}

	@SuppressWarnings("unchecked")
	public static void alterStaticModifier(FieldDeclaration node, int pos,
			Modifier.ModifierKeyword modifierKeyword) {
		if (containsStatic(node.modifiers(), pos)
				&& (modifierKeyword == null || modifierKeyword != null)) {
			node.modifiers().remove(pos);
		} else {
			node.modifiers().add(
					pos,
					node.getAST().newModifier(
							Modifier.ModifierKeyword.STATIC_KEYWORD));
		}

	}

	@SuppressWarnings("unchecked")
	public static void alterModifierKeyword(BodyDeclaration node,
			Modifier.ModifierKeyword modifierKeyword,
			Modifier.ModifierKeyword originalModifierKeyword, int pos) {
		if (originalModifierKeyword == null) {
			node.modifiers().add(pos,
					node.getAST().newModifier(modifierKeyword));

		} else if (modifierKeyword == null) {
			node.modifiers().remove(pos);

		} else {
			Modifier m = (Modifier) node.modifiers().get(pos);
			m.setKeyword(modifierKeyword);
		}
	}

	@SuppressWarnings("rawtypes")
	private static boolean containsStatic(List modifiers, int pos) {
		if (pos < modifiers.size()
				&& ((Modifier) modifiers.get(pos)).getKeyword().equals(
						Modifier.ModifierKeyword.STATIC_KEYWORD))
			return true;
		return false;
	}
}
