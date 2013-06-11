package domain.controller;

import java.util.List;

import domain.manage.ManageMutationOperators;
import domain.mutation.IMutationOperators;

public class MutationOperatorsController {

	private ManageMutationOperators manageMutationOperators;

	public MutationOperatorsController() {
		manageMutationOperators = new ManageMutationOperators();
	}

	public void setOperatorsSelected(Object[] elements) {
		manageMutationOperators.setOperatorsSelected(elements);
	}

	public void createMutationOperators() {
		manageMutationOperators.createMutationOperators();
	}

	public List<IMutationOperators> getInstanceOfOperators() {
		return manageMutationOperators.getInstanceOfOperators();
	}

}
