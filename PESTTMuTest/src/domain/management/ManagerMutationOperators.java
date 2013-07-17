package domain.management;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.factories.MutationOperatorsFactory;
import domain.mutation.IMutationOperators;

public class ManagerMutationOperators extends Observable {
	private Object[] operatorSelected;
	private List<IMutationOperators> listOperators;

	public ManagerMutationOperators() {
		this.listOperators = null;
		this.operatorSelected = null;
	}

	/**
	 * Cria instancias de IMutationOperators de acordo com os elements
	 * 
	 * @param elements
	 */
	public void createMutationOperators(Object[] elements) {
		setOperatorsSelected(elements);
		MutationOperatorsFactory factory = new MutationOperatorsFactory();
		listOperators = factory.getInstanceOfOperators(operatorSelected);
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
	public void setOperatorsSelected(Object[] operatorSelected) {
		this.operatorSelected = operatorSelected;
		setChanged();
		notifyObservers();
	}

	/**
	 * Devolve um array com os elementos selecionados
	 * 
	 * @return
	 */
	public Object[] getOperatorsSelected() {
		return operatorSelected;
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

}
