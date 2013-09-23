/**
 * 
 */
package domain.mutation;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

/**
 * @author sheilla
 * 
 */
public interface IMutationOperators {
	public List<Mutation> getMutations(ASTNode node);

	public boolean isOperatorApplicable(ASTNode node);

	public void applyOperator(Mutation mutation, ASTRewrite rewrite);

	public void undoActionOperator(Mutation mutation, ASTRewrite rewrite);

}
