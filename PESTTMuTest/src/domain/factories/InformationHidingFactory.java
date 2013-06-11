package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.IMutationOperators;

public class InformationHidingFactory extends AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case AMC:
			return null;
		}
		return null;
	}

}
