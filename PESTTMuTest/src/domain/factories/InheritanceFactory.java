package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.IMutationOperators;

public class InheritanceFactory extends AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case IHD:
			return null;
		case IHI:
			return null;
		case IOD:
			return null;
		case IOP:
			return null;
		case IOR:
			return null;
		case ISK:
			return null;
		case IPC:
			return null;
		}
		return null;
	}

}
