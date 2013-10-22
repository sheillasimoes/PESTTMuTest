package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.operators.AccessModifierChange;
import domain.mutation.operators.IMutationOperators;

public class InformationHidingFactory extends AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case AMC:
			return new AccessModifierChange();
		default:
			break;
		}
		return null;
	}

}
