package domain.factories;

import ui.constants.DesignationMutationOperators;
import domain.mutation.operators.AssignmentOperatorReplacement;
import domain.mutation.operators.IMutationOperators;

public class TraditionalOperatorFactory extends
		AbstractFactoryMutationOperators {

	@Override
	public IMutationOperators createMutationOperator(Object acronym) {
		DesignationMutationOperators desigOperator = (DesignationMutationOperators) acronym;

		switch (desigOperator) {
		case ABS:
			return null;
		case AOR:
			return null;
		case ROR:
			return null;
		case COR:
			return null;
		case SOR:
			return null;
		case LOR:
			return null;
		case ASR:
			return new AssignmentOperatorReplacement();
		case UOI:
			return null;
		case UOD:
			return null;
		case SVR:
			return null;
		case BSR:
			return null;
		default:
			break;
		}
		return null;
	}
}
