package ui.constants;

/**
 * Constantes relacionadas com as tabelas que fazem parte das views
 * 
 * @author sheilla
 * 
 */
public enum TableViewers {
	/* id of table */
	MUTANTSTABLE, GROUNDSTRINGTABLE, MUTATIONOPAPPLTABLE, ANALYSEMUTANTSTABLE, TESTCLASSESFAILEDTABLE, PROJECTTABLE;

	/* the name of column */
	public static final String COLUMN_EQUIVALENT_MUTANT = "isEquivalent";
	public static final String COLUMN_MUTANT_STATE = "Detected";
	public static final String COLUMN_MUTANT = "Mutant";
	public static final String COLUMN_MUTATION_OP_APPL = "Mutation Operator Applicable";
	public static final String COLUMN_GROUND_STRING = "Ground String";
	public static final String COLUMN_PROJECT_NAME = "Project";
	public static final String COLUMN_RESOURCE = "Resource";
	public static final String COLUMN_STATUS = "Status";
	public static final String COLUMN_NAME_TEST_CLASSES = "Test Classes Killed Mutant - test classe (test case)";
	public static final String COLUMN_LINE = "Line";

	/* the width of columns */
	public static final int COLUMN_WIDTH = 413;

}
