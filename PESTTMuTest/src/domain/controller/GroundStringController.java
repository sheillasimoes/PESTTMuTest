package domain.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.groundString.GroundString;
import domain.groundString.ManagerGroundString;
import domain.mutation.operators.IMutationOperators;
import domain.mutation.operators.ManagerMutationOperators;

public class GroundStringController extends Observable {
	private ManagerGroundString managerGroundString;
	private GroundString selectedGroundString;

	public GroundStringController(ManagerMutationOperators operatorManager) {
		this.managerGroundString = new ManagerGroundString(operatorManager);
	}

	public void initializeListGroundString() {
		managerGroundString.initializeListGroundString();
	}

	public List<GroundString> getListGroundString() {
		return managerGroundString.getListGroundString();
	}

	public void addObserverGroundString(Observer o) {
		managerGroundString.addObserver(o);
	}

	public void deleteObserverGroundString(Observer o) {
		managerGroundString.deleteObserver(o);
	}

	public void setSelectedGroundString(GroundString selectGroundString) {
		this.selectedGroundString = selectGroundString;
		setChanged();
		notifyObservers();
	}

	public GroundString getSelectedGroundString() {
		return selectedGroundString;
	}

	public void evaluateASTNode(ASTNode node) {
		managerGroundString.evaluateASTNode(node);
	}

	public List<IMutationOperators> getOperatorsApplicable() {
		return managerGroundString.getOperatorsApplicable(selectedGroundString);
	}

	public List<IMutationOperators> getOperatorsApplicable(
			GroundString selectGroundString) {
		return managerGroundString.getOperatorsApplicable(selectGroundString);
	}
}
