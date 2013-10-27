package domain.mutation.operators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.ThisExpression;

import domain.mutation.Mutation;
import domain.util.ASTChangeHelper;

public class ThisKeywordDeletion implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		Assignment assignment = (Assignment) node;

		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();
		String newName = ((SimpleName) assignment.getRightHandSide())
				.getIdentifier();
		listMutants.add(new Mutation(assignment, this, assignment.getAST()
				.newSimpleName(newName), (FieldAccess) assignment
				.getLeftHandSide()));
		return listMutants;
	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		if (node instanceof Assignment
				&& ((Assignment) node).getLeftHandSide() instanceof FieldAccess
				&& ((Assignment) node).getRightHandSide() instanceof SimpleName) {
			FieldAccess fieldAccess = (FieldAccess) ((Assignment) node)
					.getLeftHandSide();
			SimpleName simpleName = (SimpleName) ((Assignment) node)
					.getRightHandSide();
			IVariableBinding binding = (IVariableBinding) simpleName
					.resolveBinding();
			if (fieldAccess.getExpression() instanceof ThisExpression
					&& binding.isParameter()
					&& ((SimpleName) fieldAccess.getName()).getIdentifier()
							.equals(simpleName.getIdentifier())) {

				return true;
			}
		}
		return false;
	}

	@Override
	public void applyOperator(Mutation mutation) {
		ASTChangeHelper.alterThisKeywordDeletion(
				(Assignment) mutation.getASTNode(),
				(Expression) mutation.getData());

	}

	@Override
	public void undoActionOperator(Mutation mutation) {
		ASTChangeHelper.alterThisKeywordDeletion(
				(Assignment) mutation.getASTNode(),
				(Expression) mutation.getOriginalData());
	}

	@Override
	public String toString() {
		return "This Keyword Deletion";
	}

}
