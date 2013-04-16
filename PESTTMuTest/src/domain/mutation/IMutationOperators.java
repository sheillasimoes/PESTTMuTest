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
	public List<ASTNode> getMutation(ASTNode node);
}
