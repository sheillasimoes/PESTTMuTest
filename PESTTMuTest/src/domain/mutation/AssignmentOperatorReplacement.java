package domain.mutation;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import domain.constants.EnumAssignmentOperator;
import domain.util.ASTChangeHelper;

public class AssignmentOperatorReplacement implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {

		Assignment assignmentNode = (Assignment) node;

		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();

		for (EnumAssignmentOperator opr : EnumAssignmentOperator.values()) {
			if (!opr.getAssignmentOperator().equals(
					assignmentNode.getOperator())) {

				// create a mutation
				Mutation mutation = new Mutation(assignmentNode, this,
						opr.getAssignmentOperator(),
						assignmentNode.getOperator());

				listMutants.add(mutation);
			}
		}
		return listMutants;
	}

	@Override
	public void applyOperator(Mutation mutation, ASTRewrite rewrite) {
		Assignment assignmentNode = (Assignment) mutation.getASTNode();
		Operator operator = (Operator) mutation.getData();
		ASTChangeHelper.alterAssignmentOperator(assignmentNode, operator,
				rewrite);

	}

	@Override
	public void undoActionOperator(Mutation mutation, ASTRewrite rewrite) {
		Assignment assignmentNode = (Assignment) mutation.getASTNode();
		Operator operator = (Operator) mutation.getoriginalData();
		ASTChangeHelper.alterAssignmentOperator(assignmentNode, operator,
				rewrite);

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
