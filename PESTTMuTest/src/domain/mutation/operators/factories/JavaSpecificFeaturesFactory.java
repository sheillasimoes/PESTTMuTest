package domain.mutation.operators.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.operators.IMutationOperators;
import domain.mutation.operators.MemberVariableInitializationDeletion;
import domain.mutation.operators.StaticModifierChange;
import domain.mutation.operators.ThisKeywordDeletion;

public class JavaSpecificFeaturesFactory extends
		AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case JTD:
			return new ThisKeywordDeletion();
		case JSC:
			return new StaticModifierChange();
		case JID:
			return new MemberVariableInitializationDeletion();
		case JDC:
			return null;
		default:
			break;
		}
		return null;
	}

}
