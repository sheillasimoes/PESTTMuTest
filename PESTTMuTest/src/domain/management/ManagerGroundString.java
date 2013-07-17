package domain.management;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.eclipse.jdt.core.dom.ASTNode;

public class ManagerGroundString extends Observable {

	private List<ASTNode> listGroundString;

	public ManagerGroundString() {
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
	public void addGroundString(ASTNode node) {
		listGroundString.add(node);
		setChanged();
		notifyObservers();
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
	public void evaluateASTNode(ASTNode node, ManagerMutationOperators manager) {
		if (isGroundString(node, manager)) {
			addGroundString(node);
		}
	}

	private boolean isGroundString(ASTNode node,
			ManagerMutationOperators manager) {
		return manager.anyOperatorApplied(node);
	}
}
