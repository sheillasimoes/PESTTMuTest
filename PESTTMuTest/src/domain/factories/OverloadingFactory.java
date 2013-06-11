package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.IMutationOperators;

public class OverloadingFactory extends AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case OMR:
			return null;
		case OMD:
			return null;
		case OAO:
			return null;
		case OAN:
			return null;
		}
		return null;
	}

}
