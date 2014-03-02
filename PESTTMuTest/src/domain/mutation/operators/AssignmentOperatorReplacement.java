package domain.mutation.operators;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;

import domain.constants.EnumAssignmentOperator;
import domain.mutation.Mutation;
import domain.util.ASTChangeHelper;

public class AssignmentOperatorReplacement implements IMutationOperators {
	private static final List<String> CLASS_EXCEPTION = Arrays.asList(
			"java.lang.String", "java.lang.Boolean", "java.lang.Float",
			"java.lang.Integer", "java.lang.Long", "java.lang.Short",
			"java.lang.Double");

	@Override
	public List<Mutation> getMutations(ASTNode node) {

		Assignment assignmentNode = (Assignment) node;
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();

		for (EnumAssignmentOperator opr : EnumAssignmentOperator.values()) {
			if (!opr.getAssignmentOperator().equals(
					assignmentNode.getOperator())) {
				/*
				 * verifica as divisoes por zero as mutacoes validas p as
				 * classes String e Boolean e p o tipo primitivo boolean
				 */
				if ((opr.name().equals(
						EnumAssignmentOperator.DIVIDE_ASSIGN.name())
						&& assignmentNode.getRightHandSide() instanceof NumberLiteral && ((NumberLiteral) assignmentNode
							.getRightHandSide()).getToken().equals("0"))
						|| (assignmentNode.getLeftHandSide() instanceof Name && ((assignmentNode
								.getLeftHandSide().resolveTypeBinding()
								.isPrimitive()
								&& assignmentNode.getLeftHandSide()
										.resolveTypeBinding().getName()
										.equals("boolean")
								&& !opr.name().equals(
										EnumAssignmentOperator.ASSIGN.name())
								&& !opr.name().equals(
										EnumAssignmentOperator.BIT_AND_ASSIGN
												.name())
								&& !opr.name().equals(
										EnumAssignmentOperator.BIT_OR_ASSIGN
												.name()) && !opr.name().equals(
								EnumAssignmentOperator.BIT_XOR_ASSIGN.name())) || (assignmentNode
								.getLeftHandSide().resolveTypeBinding()
								.isClass() && ((assignmentNode
								.getLeftHandSide().resolveTypeBinding()
								.getQualifiedName().equals("java.lang.String")
								&& !opr.name().equals(
										EnumAssignmentOperator.PLUS_ASSIGN
												.name()) && !opr.name().equals(
								EnumAssignmentOperator.ASSIGN.name())) || (assignmentNode
								.getLeftHandSide().resolveTypeBinding()
								.getQualifiedName().equals("java.lang.Boolean")
								&& !opr.name().equals(
										EnumAssignmentOperator.ASSIGN.name())
								&& !opr.name().equals(
										EnumAssignmentOperator.BIT_AND_ASSIGN
												.name())
								&& !opr.name().equals(
										EnumAssignmentOperator.BIT_OR_ASSIGN
												.name()) && !opr.name().equals(
								EnumAssignmentOperator.BIT_XOR_ASSIGN.name()))))))) {
					continue;
				}

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
	public void applyOperator(Mutation mutation) {
		Assignment assignmentNode = (Assignment) mutation.getASTNode();
		Operator operator = (Operator) mutation.getData();
		ASTChangeHelper.alterAssignmentOperator(assignmentNode, operator);

	}

	@Override
	public void undoActionOperator(Mutation mutation) {
		Assignment assignmentNode = (Assignment) mutation.getASTNode();
		Operator operator = (Operator) mutation.getOriginalData();
		ASTChangeHelper.alterAssignmentOperator(assignmentNode, operator);

	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		boolean flag = false;
		if (node instanceof Assignment) {
			flag = true;
			Assignment assignment = (Assignment) node;
			if (assignment.getRightHandSide() instanceof NullLiteral
					|| (assignment.getLeftHandSide() instanceof Name && (assignment
							.getLeftHandSide().resolveTypeBinding().isArray()
							|| assignment.getLeftHandSide()
									.resolveTypeBinding().isInterface()
							|| assignment.getLeftHandSide()
									.resolveTypeBinding().isEnum() || (assignment
							.getLeftHandSide().resolveTypeBinding().isClass() && !CLASS_EXCEPTION
							.contains(assignment.getLeftHandSide()
									.resolveTypeBinding().getQualifiedName()))))) {
				flag = false;
			}
			return flag;
		}
		return flag;
	}

	@Override
	public String toString() {
		return "Assignment Operator Replacement";
	}

}
