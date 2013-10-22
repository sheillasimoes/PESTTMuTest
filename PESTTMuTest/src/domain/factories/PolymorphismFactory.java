package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.operators.IMutationOperators;

public class PolymorphismFactory extends AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case PNC:
			return null;
		case PMD:
			return null;
		case PPD:
			return null;
		case PRV:
			return null;
		default:
			break;
		}
		return null;
	}

}
