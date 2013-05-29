/**
 * 
 */
package domain.mutation;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

/**
 * @author sheilla
 * 
 */
public interface IMutationOperators {
	public List<Mutation> getMutations(ASTNode node);

	public boolean isOperatorApplicable(ASTNode node);

	public void applyMutation(Mutation mutation);
}
