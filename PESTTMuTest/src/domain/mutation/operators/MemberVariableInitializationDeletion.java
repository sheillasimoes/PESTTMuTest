package domain.mutation.operators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import domain.mutation.Mutation;
import domain.util.ASTChangeHelper;

public class MemberVariableInitializationDeletion implements IMutationOperators {

	@Override
	public List<Mutation> getMutations(ASTNode node) {
		VariableDeclarationStatement variable = (VariableDeclarationStatement) node;
		/* List com todas as mutacoes geradas */
		List<Mutation> listMutants = new LinkedList<Mutation>();
		for (Object obj : variable.fragments()) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) obj;
			if (fragment.getInitializer() != null) {
				listMutants.add(new Mutation(node, this, fragment, fragment
						.getInitializer()));
			}
		}

		return listMutants;
	}

	@Override
	public boolean isOperatorApplicable(ASTNode node) {
		if (node instanceof VariableDeclarationStatement) {
			for (Object obj : ((VariableDeclarationStatement) node).fragments()) {
				VariableDeclarationFragment fragment = (VariableDeclarationFragment) obj;
				if (fragment.getInitializer() != null)
					return true;
			}
		}
		return false;
	}

	@Override
	public void applyOperator(Mutation mutation) {
		ASTChangeHelper
				.alterVariableInitializationDeletion((VariableDeclarationFragment) mutation
						.getData());
	}

	@Override
	public void undoActionOperator(Mutation mutation) {
		ASTChangeHelper.undoVariableInitializationDeletion(
				(VariableDeclarationFragment) mutation.getData(),
				(Expression) mutation.getOriginalData());
	}

	@Override
	public String toString() {
		return "Member Variable Initialization Deletion";
	}

}
