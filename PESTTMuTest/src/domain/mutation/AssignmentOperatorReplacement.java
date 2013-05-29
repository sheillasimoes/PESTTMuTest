package domain.mutation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;

import domain.constants.EnumAssignmentOperator;

public class AssignmentOperatorReplacement implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		/* No (AST) original */
		Assignment assignmentNode = (Assignment) node;

		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();

		for (EnumAssignmentOperator opr : EnumAssignmentOperator.values()) {
			if (!opr.getStrAssignmentOper().equals(
					assignmentNode.getOperator().toString())) {
				Mutation mutation = new Mutation(node, this,
						opr.getStrAssignmentOper());
				listMutants.add(mutation);
			}

		}

		return listMutants;
	}

	@Override
	public void applyMutation(Mutation mutation) {
		Assignment assignmentNode = (Assignment) mutation.getASTNode();
		assignmentNode.setOperator(Assignment.Operator
				.toOperator((String) mutation.getData()));
	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		return node instanceof Assignment; // node.getNodeType() ==
											// ASTNode.ASSIGNMENT;
	}

}
