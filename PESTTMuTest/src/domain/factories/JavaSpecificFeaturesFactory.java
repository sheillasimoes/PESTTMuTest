package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.IMutationOperators;

public class JavaSpecificFeaturesFactory extends
		AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case JTD:
			return null;
		case JSC:
			return null;
		case JID:
			return null;
		case JDC:
			return null;
		}
		return null;
	}

}
