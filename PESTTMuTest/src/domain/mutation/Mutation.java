/**
 * 
 */
package domain.mutation;

import org.eclipse.jdt.core.dom.ASTNode;

public class Mutation {

	//
	private ASTNode node;

	//
	private IMutationOperators mutationOperator;

	//
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
		mutationOperator.applyMutation(this);
	}
}
