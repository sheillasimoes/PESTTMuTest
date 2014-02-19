/**
 * 
 */
package domain.mutation;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.operators.IMutationOperators;

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
		mutant = "";
	}

	public Object getData() {
		return data;
	}

	public ASTNode getASTNode() {
		return node;
	}

	public Object getOriginalData() {
		return originalData;
	}

	/**
	 * @return the mutationOperator
	 */
	public IMutationOperators getMutationOperator() {
		return mutationOperator;
	}

	public void applyMutationOperator() {
		mutationOperator.applyOperator(this);
		mutant = node.toString();
	}

	public void undoActionMutationOperator() {
		mutationOperator.undoActionOperator(this);
	}

	@Override
	public String toString() {
		return mutant;
	}
}
