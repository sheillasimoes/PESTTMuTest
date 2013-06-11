package domain.manage;

import java.util.List;

import domain.factories.MutationOperatorsFactory;
import domain.mutation.IMutationOperators;

public class ManageMutationOperators {
	private Object[] operatorSelected;
	private List<IMutationOperators> listOperators;

	public ManageMutationOperators() {
		this.listOperators = null;
		this.operatorSelected = null;
	}

	public void createMutationOperators() {
		MutationOperatorsFactory factory = new MutationOperatorsFactory();
		listOperators = factory.getInstanceOfOperators(operatorSelected);
	}

	public List<IMutationOperators> getInstanceOfOperators() {
		return listOperators;
	}

	public void setOperatorsSelected(Object[] operatorSelected) {
		this.operatorSelected = operatorSelected;
	}

}
