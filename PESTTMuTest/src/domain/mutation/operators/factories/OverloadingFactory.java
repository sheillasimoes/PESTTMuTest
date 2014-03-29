package domain.mutation.operators.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.operators.IMutationOperators;

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
		default:
			break;
		}
		return null;
	}

}
