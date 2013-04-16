package domain.mutation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;

import domain.constants.EnumAssignmentOperator;

public class TesteASR extends MutationOperators {

	@Override
	public List<ASTNode> getMutation(ASTNode node) {
		/* No (AST) original */
		Assignment assignmentNode = (Assignment) node;

		/* No (AST) onde serao aplicadas as mutacoes */
		Assignment nodeMutant = assignmentNode;

		/* List com todas as mutacoes geradas */
		List<ASTNode> listMutant = new LinkedList<ASTNode>();

		for (EnumAssignmentOperator opr : EnumAssignmentOperator.values()) {
			if (!opr.getStrAssignmentOper().equals(
					assignmentNode.getOperator().toString())) {
				nodeMutant.setOperator(Assignment.Operator.toOperator(opr
						.getStrAssignmentOper()));
				listMutant.add(nodeMutant);
			}

		}

		return listMutant;
	}

}
