package domain.management;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.eclipse.jdt.core.dom.ASTNode;

import ui.constants.DesignationMutationOperators;
import ui.constants.GroupDesignationMutationOperators;
import ui.display.views.tree.structure.AbstractTree;
import domain.factories.MutationOperatorsFactory;
import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;

public class ManagerMutationOperators extends Observable {
	private Object[] selectedOperator;
	private List<IMutationOperators> listOperators;
	private Object[] checkedElements;

	public ManagerMutationOperators() {
		this.listOperators = null;
		this.selectedOperator = null;
		this.checkedElements = null;
	}

	/**
	 * Cria instancias de IMutationOperators de acordo com os elements
	 * 
	 * @param elements
	 */
	public void createMutationOperators(Object[] elements) {
		setSelectedOperators(elements);
		MutationOperatorsFactory factory = new MutationOperatorsFactory();
		listOperators = factory.getInstanceOfOperators(selectedOperator);
	}

	/**
	 * Dado uma node verifica se existe algum operador de mutação que pode ser
	 * aplicado
	 * 
	 * @param node
	 * @return true se pode ser aplicado e false caso contrario
	 */
	public boolean anyOperatorApplied(ASTNode node) {
		boolean flag = false;
		for (IMutationOperators operator : listOperators) {
			if (operator.isOperatorApplicable(node)) {
				flag = true;
				return flag;
			}
		}
		return flag;
	}

	public boolean verifyChangesOperators(Object[] checkedElements) {
		setCheckedElements(checkedElements);
		return compare();
	}

	/**
	 * Devolve uma lista com os mutation operators selected
	 * 
	 * @return
	 */
	public List<IMutationOperators> getMutationOperators() {
		return listOperators;
	}

	/**
	 * Altera a lista dos operadores selecionados
	 * 
	 * @param operatorSelected
	 */
	public void setSelectedOperators(Object[] operatorSelected) {
		this.selectedOperator = operatorSelected;
		setChanged();
		notifyObservers();
	}

	/**
	 * Devolve um array com os elementos selecionados
	 * 
	 * @return
	 */
	public Object[] getSelectedOperators() {
		return selectedOperator;
	}

	public Object[] getCheckedElements() {
		return checkedElements;
	}

	public void setCheckedElements(Object[] checkedElements) {
		this.checkedElements = checkedElements;
	}

	/**
	 * 
	 * @param operator
	 * @param node
	 * @return
	 */
	public List<Mutation> getMutations(IMutationOperators operator, ASTNode node) {
		return operator.getMutations(node);
	}

	/**
	 * Obtém a lista com os operadores de mutação que podem ser aplicados a um
	 * determinado nó da AST
	 * 
	 * @param node
	 * @return
	 */
	public List<IMutationOperators> getOperatorsApplicable(ASTNode node) {
		List<IMutationOperators> list = new LinkedList<>();
		for (IMutationOperators operator : listOperators) {
			if (operator.isOperatorApplicable(node)) {
				list.add(operator);
			}
		}
		return list;
	}

	private boolean compare() {
		boolean flag = false;

		int i = 0, j = 0;
		while (i < selectedOperator.length && j < checkedElements.length) {
			AbstractTree tree1 = (AbstractTree) selectedOperator[i];
			AbstractTree tree2 = (AbstractTree) checkedElements[j];

			if (tree1.getData() instanceof GroupDesignationMutationOperators) {
				i++;
				tree1 = (AbstractTree) selectedOperator[i];

			}

			if (tree2.getData() instanceof GroupDesignationMutationOperators) {
				j++;
				tree2 = (AbstractTree) checkedElements[j];

			}

			if (tree1.getData() instanceof DesignationMutationOperators
					&& tree2.getData() instanceof DesignationMutationOperators
					&& !tree1.getData().equals(tree2.getData())) {
				return false;
			} else if (tree1.getData() instanceof DesignationMutationOperators
					&& tree2.getData() instanceof DesignationMutationOperators
					&& tree1.getData().equals(tree2.getData())) {
				flag = true;
			}
			i++;
			j++;
		}

		return i == selectedOperator.length && j == checkedElements.length
				&& flag;

	}

}
