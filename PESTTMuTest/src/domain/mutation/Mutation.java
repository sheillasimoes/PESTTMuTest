/**
 * 
 */
package domain.mutation;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

public class Mutation {

	// Nó onde será aplicada a mutacao
	private ASTNode node;

	// objeto que aplicara a mutacao
	private IMutationOperators mutationOperator;

	// Que mutacao deve ser aplicada
	private Object data;

	// data before application operator mutation
	private Object originalData;

	//
	private String mutant;

	public Mutation(ASTNode node, IMutationOperators mutationOperator,
			Object data, Object originalData) {
		this.node = node;
		this.mutationOperator = mutationOperator;
		this.data = data;
		this.originalData = originalData;
		mutant = null;
	}

	public Object getData() {
		return data;
	}

	public ASTNode getASTNode() {
		return node;
	}

	public Object getoriginalData() {
		return originalData;
	}

	public void applyMutationOperator(ASTRewrite rewrite) {
		mutationOperator.applyOperator(this, rewrite);
		mutant = node.toString();
	}

	public void undoActionMutationOperator(ASTRewrite rewrite) {
		mutationOperator.undoActionOperator(this, rewrite);
	}

	@Override
	public String toString() {
		return mutant;
	}
}
