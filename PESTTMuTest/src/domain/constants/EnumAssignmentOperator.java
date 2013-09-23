/**
 * 
 */
package domain.constants;

import org.eclipse.jdt.core.dom.Assignment;

/**
 * @author sheilla
 * 
 */
public enum EnumAssignmentOperator {

	ASSIGN(Assignment.Operator.ASSIGN), PLUS_ASSIGN(
			Assignment.Operator.PLUS_ASSIGN), MINUS_ASSIGN(
			Assignment.Operator.MINUS_ASSIGN), TIMES_ASSIGN(
			Assignment.Operator.TIMES_ASSIGN), DIVIDE_ASSIGN(
			Assignment.Operator.DIVIDE_ASSIGN), BIT_AND_ASSIGN(
			Assignment.Operator.BIT_AND_ASSIGN), BIT_OR_ASSIGN(
			Assignment.Operator.BIT_OR_ASSIGN), BIT_XOR_ASSIGN(
			Assignment.Operator.BIT_XOR_ASSIGN), REMAINDER_ASSIGN(
			Assignment.Operator.REMAINDER_ASSIGN), LEFT_SHIFT_ASSIGN(
			Assignment.Operator.LEFT_SHIFT_ASSIGN), RIGHT_SHIFT_SIGNED_ASSIGN(
			Assignment.Operator.RIGHT_SHIFT_SIGNED_ASSIGN), RIGHT_SHIFT_UNSIGNED_ASSIGN(
			Assignment.Operator.RIGHT_SHIFT_UNSIGNED_ASSIGN);

	// Representa a string correspondente ao operador
	private Assignment.Operator operator;

	EnumAssignmentOperator(Assignment.Operator operator) {
		this.operator = operator;
	}

	public Assignment.Operator getAssignmentOperator() {
		return operator;
	}
}