package domain.management;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.operators.IMutationOperators;

public class ManagerGroundString extends Observable {
	private ManagerMutationOperators operatorManager = null;
	private List<ASTNode> listGroundString = null;

	public ManagerGroundString(ManagerMutationOperators operatorManager) {
		this.operatorManager = operatorManager;
		listGroundString = new LinkedList<>();
	}

	/**
	 * Devolve a lista de GroundString
	 * 
	 * @return
	 */
	public List<ASTNode> getListGroundString() {
		return listGroundString;

	}

	/**
	 * Adiciona uma GroundString
	 * 
	 * @param node
	 */
	private void addGroundString(ASTNode node) {
		listGroundString.add(node);
		setChanged();
		notifyObservers(node);

	}

	/**
	 * Inicializa a lista que contem GroundString
	 */
	public void initializeListGroundString() {
		listGroundString = new LinkedList<ASTNode>();
		setChanged();
		notifyObservers();
	}

	/**
	 * 
	 * @param node
	 */
	public void evaluateASTNode(ASTNode node) {
		if (isGroundString(node)) {
			addGroundString(node);
		}
	}

	public List<IMutationOperators> getOperatorsApplicable(ASTNode node) {
		return operatorManager.getOperatorsApplicable(node);
	}

	private boolean isGroundString(ASTNode node) {
		return operatorManager.anyOperatorApplied(node);
	}
}
