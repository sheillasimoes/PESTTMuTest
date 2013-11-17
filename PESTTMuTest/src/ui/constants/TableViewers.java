package ui.constants;

/**
 * Constantes relacionadas com as tabelas que fazem parte das views
 * 
 * @author sheilla
 * 
 */
public enum TableViewers {
	/* id of table */
	MUTANTSTABLE, GROUNDSTRINGTABLE, MUTATIONOPAPPLTABLE, ANALYSEMUTANTS, PROJECTTABLE;

	/* the name of column */
	public static final String COLUMN_MUTANT = "Mutant";
	public static final String COLUMN_MUTATION_OP_APPL = "Mutation Operator Applicable";
	public static final String COLUMN_GROUND_STRING = "Ground String";
	public static final String COLUMN_PROJECT_NAME = "Project";
	public static final String COLUMN_FULLY_QUALIFIED_NAME = "Fully qualified name";
	public static final String COLUMN_STATUS = "Status";

	/* the width of columns */
	public static final int COLUMN_WIDTH = 414;

}
