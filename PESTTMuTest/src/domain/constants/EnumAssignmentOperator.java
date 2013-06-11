/**
 * 
 */
package domain.constants;

/**
 * @author sheilla
 * 
 */
public enum EnumAssignmentOperator {
	PLUS_ASSIGN("+="), MINUS_ASSIGN("-="), TIMES_ASSIGN("*="), DIVIDE_ASSIGN(
			"/="), BIT_AND_ASSIGN("&="), BIT_OR_ASSIGN("|="), BIT_XOR_ASSIGN(
			"^="), REMAINDER_ASSIGN("%="), LEFT_SHIFT_ASSIGN("<<="), RIGHT_SHIFT_SIGNED_ASSIGN(
			">>="), RIGHT_SHIFT_UNSIGNED_ASSIGN(">>>=");

	/* Representa a string correspondente ao operador */
	private String strAssignmentOper;

	EnumAssignmentOperator(String str) {
		strAssignmentOper = str;
	}

	/**
	 * @return the strAssignmentOper
	 */
	public String getStrAssignmentOper() {
		return strAssignmentOper;
	}
}