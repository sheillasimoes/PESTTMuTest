package domain.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.management.ManagerGroundString;
import domain.management.ManagerMutationOperators;

public class GroundStringController extends Observable {
	private ManagerGroundString managerGroundString;
	private ManagerMutationOperators operatorManager;
	private ASTNode selectedGroundString = null;

	public GroundStringController(ManagerMutationOperators operatorManager) {
		this.operatorManager = operatorManager;
		this.managerGroundString = new ManagerGroundString();
	}

	public List<ASTNode> getListGroundString() {
		return managerGroundString.getListGroundString();
	}

	public void addObserverGroundString(Observer o) {
		managerGroundString.addObserver(o);
	}

	public void deleteObserverGroundString(Observer o) {
		managerGroundString.deleteObserver(o);
	}

	public void setSelectedGroundString(ASTNode selectGroundString) {
		this.selectedGroundString = selectGroundString;
		setChanged();
		notifyObservers();
	}

	public ASTNode getSelectedGroundString() {
		return selectedGroundString;
	}

	public void setManagerGroundString(ManagerGroundString managerGroundString) {
		this.managerGroundString = managerGroundString;
	}

	public ManagerGroundString getManagerGroundString() {
		return managerGroundString;
	}

	public void initializeListGroundString() {
		managerGroundString.initializeListGroundString();
	}

	/**
	 * 
	 * @param node
	 */
	public void evaluateASTNode(ASTNode node) {
		managerGroundString.evaluateASTNode(node, operatorManager);
	}

}
