/**
 * 
 */
package domain.mutation;

import org.eclipse.jdt.core.dom.ASTNode;

public class Mutation {

	// Nó onde será aplicada a mutacao
	private ASTNode node;

	// objeto que aplicara a mutacao
	private IMutationOperators mutationOperator;

	// Que mutacao deve ser aplicada
	private Object data;

	public Mutation(ASTNode node, IMutationOperators mutationOperator,
			Object data) {
		this.node = node;
		this.mutationOperator = mutationOperator;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public ASTNode getASTNode() {
		return node;
	}

	public void applyMutation() {
		node = mutationOperator.applyMutation(this);
	}

	@Override
	public String toString() {
		return node.toString();
	}
}
