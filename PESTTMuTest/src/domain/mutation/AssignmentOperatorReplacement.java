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

				// copy a ASTNode to apply a mutation
				Assignment newNode = (Assignment) node.copySubtree(
						node.getAST(), node);

				// create a mutation
				Mutation mutation = new Mutation(newNode, this,
						opr.getStrAssignmentOper());

				// apply the mutation
				mutation.applyMutation();

				listMutants.add(mutation);
			}
		}
		return listMutants;
	}

	@Override
	public ASTNode applyMutation(Mutation mutation) {
		Assignment assignmentNode = (Assignment) mutation.getASTNode();
		assignmentNode.setOperator(Assignment.Operator
				.toOperator((String) mutation.getData()));
		return assignmentNode;

	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		return node instanceof Assignment;
	}

	@Override
	public String toString() {
		return "Assignment Operator Replacement";
	}
}
