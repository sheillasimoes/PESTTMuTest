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
	public static void alterStaticModifier(FieldDeclaration node,
			Modifier.ModifierKeyword modifierKeyword) {
		int position = 0;
		if (modifierKeyword == null) {
			for (Object obj : node.modifiers()) {
				if (obj instanceof Modifier
						&& ((Modifier) obj).getKeyword().equals(
								Modifier.ModifierKeyword.STATIC_KEYWORD)) {
					node.modifiers().remove(position);
					break;
				}
				position++;
			}

		} else {
			int i = node.modifiers().size() - 1;
			if (node.modifiers().size() == 1
					&& node.modifiers().get(i)
							.equals(Modifier.ModifierKeyword.FINAL_KEYWORD)) {
				position = 0;
			} else if ((node.modifiers().size() == 1 && !node.modifiers()
					.get(i).equals(Modifier.ModifierKeyword.FINAL_KEYWORD))
					|| (node.modifiers().size() > 1 && node.modifiers().get(i)
							.equals(Modifier.ModifierKeyword.FINAL_KEYWORD))) {
				position = 1;
			} else if (node.modifiers().size() > 1
					&& !node.modifiers().get(i)
							.equals(Modifier.ModifierKeyword.FINAL_KEYWORD)) {
				position = 2;
			}
			node.modifiers().add(position,
					node.getAST().newModifier(modifierKeyword));
		}
	}

	@SuppressWarnings("unchecked")
	public static void alterModifierKeyword(BodyDeclaration node,
			Modifier.ModifierKeyword modifierKeyword,
			Modifier.ModifierKeyword originalModifierKeyword) {

		if (originalModifierKeyword == null) {
			node.modifiers().add(getIndexModifier(node.modifiers()),
					node.getAST().newModifier(modifierKeyword));

		} else if (modifierKeyword == null) {
			node.modifiers().remove(getIndexModifier(node.modifiers()));

		} else {
			Modifier m = (Modifier) node.modifiers().get(
					getIndexModifier(node.modifiers()));
			m.setKeyword(modifierKeyword);
		}

	}

	private static int getIndexModifier(List listModifier) {
		int i = 0;
		for (Object obj : listModifier) {
			if (obj instanceof Modifier) {
				return i;
			}
			i++;
		}
		return i;
	}
}
