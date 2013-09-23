package domain.util;

import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

public class ASTChangeHelper {

	public static void alterAssignmentOperator(Assignment node,
			Assignment.Operator operator, ASTRewrite rewrite) {
		node.setOperator(operator);
		rewrite.set(node, Assignment.OPERATOR_PROPERTY, operator, null);
	}

}
