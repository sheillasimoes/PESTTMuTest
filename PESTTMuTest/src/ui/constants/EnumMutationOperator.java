package ui.constants;

/**
 * Ver se irei precisar desta class
 * 
 */
public enum EnumMutationOperator {
	/* Acronym of mutation operator */
	ABS, AOR, ROR, COR, SOR, LOR, ASR, UOI, UOD, SVR, BSR, AMC, IHD, IHI, IOD, IOP, IOR, ISK, 
	IPC, PNC, PMD, PPD, PRV, OMR, OMD, OAO, OAN, JTD, JSC, JID, JDC, EOA, EOC, EAM, 
	EMM;

	/* Description of mutation operator */
	// Traditional Operator
	public final static String ABS_DESCRIPTION = "Absolute Value Insertion";
	public final static String AOR_DESCRIPTION = "Arithmetic Operator Replacement";
	public final static String ROR_DESCRIPTION = "Relational Operator Replacement";
	public final static String COR_DESCRIPTION = "Conditional Operator Replacement";
	public final static String SOR_DESCRIPTION = "Shift Operator Replacement"; // VER SE ESTE OPERADOR PODE SER APLICADO EM JAVA
	public final static String LOR_DESCRIPTION = "Logical Operator Replacement"; // VER SE ESTE OPERADOR PODE SER APLICADO EM JAVA
	public final static String ASR_DESCRIPTION = "Assignment Operator Replacement";
	public final static String UOI_DESCRIPTION = "Unary Operator Insertion";
	public final static String UOD_DESCRIPTION = "Unary Operator Deletion";
	public final static String SVR_DESCRIPTION = "Scalar Variable Replacement";
	public final static String BSR_DESCRIPTION = "Bomb Statement Replacement";

	// Information Hiding (Access Control)
	public final static String AMC_DESCRIPTION = "Access modifier change";

	// Inheritance
	public final static String IHD_DESCRIPTION = "Hiding variable deletion";
	public final static String IHI_DESCRIPTION = "Hiding variable insertion";
	public final static String IOD_DESCRIPTION = "Overriding method deletion";
	public final static String IOP_DESCRIPTION = "Overridden method calling position change";
	public final static String IOR_DESCRIPTION = "Overridden method rename";
	public final static String ISK_DESCRIPTION = "super keyword deletion";
	public final static String IPC_DESCRIPTION = "Explicit call of a parent’s constructor deletion";

	// Polymorphism
	public final static String PNC_DESCRIPTION = "new method call with child class type";
	public final static String PMD_DESCRIPTION = "Member variable declaration with parent class type";
	public final static String PPD_DESCRIPTION = "Parameter variable declaration with child class type";
	public final static String PRV_DESCRIPTION = "Reference assignment with other compatible type";

	// Overloading
	public final static String OMR_DESCRIPTION = "Overloading method contents change";
	public final static String OMD_DESCRIPTION = "Overloading method deletion";
	public final static String OAO_DESCRIPTION = "Argument order change";
	public final static String OAN_DESCRIPTION = "Argument number change";

	// Java specific features
	public final static String JTD_DESCRIPTION = "this keyword deletion";
	public final static String JSC_DESCRIPTION = "static modifier change";
	public final static String JID_DESCRIPTION = "Member variable initialization deletion";
	public final static String JDC_DESCRIPTION = "Java-supported default constructor create";

	// Common programming mistakes
	public final static String EOA_DESCRIPTION = "Reference assignment and content assignment";
	public final static String EOC_DESCRIPTION = "Reference comparison and content comparison replacement";
	public final static String EAM_DESCRIPTION = "Accessor method change";
	public final static String EMM_DESCRIPTION = "Modifier method change";

}
