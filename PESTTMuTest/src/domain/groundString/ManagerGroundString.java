package domain.groundString;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.operators.IMutationOperators;
import domain.mutation.operators.ManagerMutationOperators;

public class ManagerGroundString extends Observable {
	private ManagerMutationOperators operatorManager;
	private List<GroundString> listGroundString;

	public ManagerGroundString(ManagerMutationOperators operatorManager) {
		this.operatorManager = operatorManager;
		listGroundString = new LinkedList<GroundString>();
	}

	/**
	 * Devolve a lista de GroundString
	 * 
	 * @return
	 */
	public List<GroundString> getListGroundString() {
		return listGroundString;// listGroundString;

	}

	/**
	 * Adiciona uma GroundString
	 * 
	 * @param node
	 */
	private void addGroundString(ASTNode node) {
		listGroundString.add(new GroundString(node));
		// setChanged();
		// notifyObservers(node);

	}

	/**
	 * Inicializa a lista que contem GroundString
	 */
	public void initializeListGroundString() {
		listGroundString = new LinkedList<GroundString>();
		// setChanged();
		// notifyObservers();
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

	public List<IMutationOperators> getOperatorsApplicable(
			GroundString groundString) {
		return operatorManager.getOperatorsApplicable(groundString
				.getGroundString());
	}

	private boolean isGroundString(ASTNode node) {
		return operatorManager.anyOperatorApplied(node);
	}
}
