/**
 * 
 */
package domain.mutation;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.operators.IMutationOperators;
import domain.util.ASTUtil;
import domain.util.ToStringASTNode;

public class Mutation {

	// Node where it will be applied to mutation
	private ASTNode node;

	// object that applied the mutation
	private IMutationOperators mutationOperator;

	// That mutation should be applied
	private Object data;

	// data before application operator mutation
	private Object originalData;

	//
	private String mutant;

	private String nodeToString;

	private int lineNumber;

	public Mutation(ASTNode node, IMutationOperators mutationOperator,
			Object data, Object originalData) {
		this.node = node;
		this.mutationOperator = mutationOperator;
		this.data = data;
		this.originalData = originalData;
		mutant = "";
		nodeToString = ToStringASTNode.toString(node);
		lineNumber = ASTUtil.getLineNumber(node);
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
		mutant = ToStringASTNode.toString(node);
	}

	public void undoActionMutationOperator() {
		mutationOperator.undoActionOperator(this);
	}

	/**
	 * @return the nodeToString
	 */
	public String getNodeToString() {
		return nodeToString;
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	@Override
	public String toString() {
		return mutant;
	}
}
