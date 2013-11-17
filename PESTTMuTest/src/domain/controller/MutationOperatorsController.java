package domain.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.mutation.operators.ManagerMutationOperators;

public class MutationOperatorsController extends Observable {

	private ManagerMutationOperators managerMutationOperators;
	private IMutationOperators selectedIMutOperator;

	public MutationOperatorsController() {
		managerMutationOperators = new ManagerMutationOperators();
		selectedIMutOperator = null;
	}

	public void createMutationOperators(Object[] elements) {
		managerMutationOperators.createMutationOperators(elements);
	}

	public boolean anyOperatorApplied(ASTNode node) {
		return managerMutationOperators.anyOperatorApplied(node);
	}

	public void setManagerMutationOperators(
			ManagerMutationOperators managerMutationOperators) {
		this.managerMutationOperators = managerMutationOperators;
	}

	public ManagerMutationOperators getManagerMutationOperators() {
		return managerMutationOperators;
	}

	public IMutationOperators getSelectedIMutOperator() {
		return selectedIMutOperator;
	}

	public void setSelectedIMutOperator(IMutationOperators selectedOperator) {
		this.selectedIMutOperator = selectedOperator;
		setChanged();
		notifyObservers();
	}

	public Object[] getSelectedOperators() {
		return managerMutationOperators.getSelectedOperators();
	}

	/**
	 * Verifica se houve alguma alteracao dos operadores de mutacao selecionados
	 * 
	 * @param checkedElements
	 * @return
	 */
	public boolean verifyChangesOperators(Object[] checkedElements) {
		return managerMutationOperators.verifyChangesOperators(checkedElements);
	}

	public void addObserverMutationOperators(Observer o) {
		managerMutationOperators.addObserver(o);
	}

	public void deleteObserverMutationOperators(Observer o) {
		managerMutationOperators.deleteObserver(o);
	}

	public List<Mutation> getMutations(ASTNode node) {
		return selectedIMutOperator.getMutations(node);
	}
}
