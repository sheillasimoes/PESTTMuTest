package domain.mutation.operators;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.SuperFieldAccess;

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
		Expression leftExpression = assignmentNode.getLeftHandSide();
		Expression rightExpression = assignmentNode.getRightHandSide();
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();
		// System.out.println("value leftexpression"
		// + leftExpression.resolveConstantExpressionValue().toString());
		for (EnumAssignmentOperator opr : EnumAssignmentOperator.values()) {
			if (!opr.getAssignmentOperator().equals(
					assignmentNode.getOperator())) {
				/*
				 * verifica as divisoes por zero as mutacoes validas p as
				 * classes String e Boolean e p o tipo primitivo boolean
				 */
				if ((opr.name().equals(
						EnumAssignmentOperator.DIVIDE_ASSIGN.name())
						&& rightExpression instanceof NumberLiteral && ((NumberLiteral) rightExpression)
						.getToken().equals("0"))
						|| (leftExpression instanceof Name && ((leftExpression
								.resolveTypeBinding().isPrimitive() && validePrimitiveBoolean(
								leftExpression.resolveTypeBinding(), opr.name())) || (leftExpression
								.resolveTypeBinding().isClass() && (valideTypeString(
								leftExpression.resolveTypeBinding(), opr.name()) || valideTypeBoolean(
								leftExpression.resolveTypeBinding(), opr.name())))))) {
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
			Expression leftExpression = assignment.getLeftHandSide();
			Expression rightExpression = assignment.getRightHandSide();
			// binding
			ITypeBinding bindingLeft = leftExpression.resolveTypeBinding();

			if (rightExpression instanceof NullLiteral
					|| rightExpression instanceof ClassInstanceCreation
					|| isFinal(leftExpression)
					|| bindingLeft.isArray()
					|| bindingLeft.isInterface()
					|| bindingLeft.isEnum()
					|| bindingLeft.isTypeVariable()
					|| (bindingLeft.isClass() && !CLASS_EXCEPTION
							.contains(bindingLeft.getQualifiedName()))
					|| (!isDefined(node))) {
				flag = false;
			}
			return flag;
		}
		return flag;
	}

	private boolean isDefined(ASTNode node) {
		Object o = node.getProperty("PESTT_VAR_INIT");
		return o == null ? true : ((boolean) o);
	}

	private boolean isFinal(Expression expression) {
		String modifiers = "";
		if (expression instanceof ArrayAccess) {
			Name name = getName(expression);
			modifiers = name == null ? "" : Modifier.toString(name
					.resolveBinding().getModifiers());
		} else if (expression instanceof FieldAccess) {
			modifiers = Modifier.toString(((FieldAccess) expression)
					.resolveFieldBinding().getModifiers());
		} else if (expression instanceof SuperFieldAccess) {
			modifiers = Modifier.toString(((SuperFieldAccess) expression)
					.resolveFieldBinding().getModifiers());
		} else if (expression instanceof Name) {
			modifiers = Modifier.toString(((Name) expression).resolveBinding()
					.getModifiers());
		}
		return modifiers.contains("final");
	}

	private boolean validePrimitiveBoolean(ITypeBinding binding,
			String assignmentOperator) {
		if (binding.getName().equals("boolean")
				&& !assignmentOperator.equals(EnumAssignmentOperator.ASSIGN
						.name())
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.BIT_AND_ASSIGN.name())
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.BIT_OR_ASSIGN.name())
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.BIT_XOR_ASSIGN.name())) {
			return true;
		} else
			return false;
	}

	private boolean valideTypeString(ITypeBinding binding,
			String assignmentOperator) {
		if (binding.getQualifiedName().equals("java.lang.String")
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.PLUS_ASSIGN.name())
				&& !assignmentOperator.equals(EnumAssignmentOperator.ASSIGN
						.name())) {
			return true;
		} else
			return false;
	}

	private boolean valideTypeBoolean(ITypeBinding binding,
			String assignmentOperator) {
		if (binding.getQualifiedName().equals("java.lang.Boolean")
				&& !assignmentOperator.equals(EnumAssignmentOperator.ASSIGN
						.name())
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.BIT_AND_ASSIGN.name())
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.BIT_OR_ASSIGN.name())
				&& !assignmentOperator
						.equals(EnumAssignmentOperator.BIT_XOR_ASSIGN.name())) {
			return true;
		} else
			return false;
	}

	private Name getName(Expression expression) {
		if (expression instanceof ArrayAccess) {
			getName(((ArrayAccess) expression).getArray());
		} else if (expression instanceof Name)
			return (Name) expression;
		return null;
	}

	@Override
	public String toString() {
		return "Assignment Operator Replacement";
	}

}
