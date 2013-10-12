package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.operators.IMutationOperators;

public class CommonProgrammingMistakesFactory extends
		AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case EOA:
			return null;
		case EOC:
			return null;
		case EAM:
			return null;
		case EMM:
			return null;
		}
		return null;
	}

}
