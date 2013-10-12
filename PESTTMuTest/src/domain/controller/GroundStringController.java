package domain.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.management.ManagerGroundString;
import domain.management.ManagerMutationOperators;
import domain.mutation.operators.IMutationOperators;

public class GroundStringController extends Observable {
	private ManagerGroundString managerGroundString;
	private ASTNode selectedGroundString = null;

	public GroundStringController(ManagerMutationOperators operatorManager) {
		this.managerGroundString = new ManagerGroundString(operatorManager);
	}

	public void initializeListGroundString() {
		managerGroundString.initializeListGroundString();
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

	public void evaluateASTNode(ASTNode node) {
		managerGroundString.evaluateASTNode(node);
	}

	public List<IMutationOperators> getOperatorsApplicable() {
		return managerGroundString.getOperatorsApplicable(selectedGroundString);
	}

	public List<IMutationOperators> getOperatorsApplicable(ASTNode node) {
		return managerGroundString.getOperatorsApplicable(node);
	}
}
