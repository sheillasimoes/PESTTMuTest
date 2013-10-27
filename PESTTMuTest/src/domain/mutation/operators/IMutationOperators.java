/**
 * 
 */
package domain.mutation.operators;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.Mutation;

/**
 * @author sheilla
 * 
 */
public interface IMutationOperators {
	public List<Mutation> getMutations(ASTNode node);

	public boolean isOperatorApplicable(ASTNode node);

	public void applyOperator(Mutation mutation);

	public void undoActionOperator(Mutation mutation);

}
