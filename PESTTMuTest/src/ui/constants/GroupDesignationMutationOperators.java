/**
 * 
 */
package ui.constants;

/**
 * @author Sheila Simoes
 * 
 */
public enum GroupDesignationMutationOperators {
	
	TRADITIONAL_OPERATOR("Traditional Operator"),
	INFORMATION_HIDING("Information Hiding (Access Control)"),
	INHERITANCE("Inheritance"),
	POLYMORPHISM("Polymorphism"),
	OVERLOADING("Overloading"),
	JAVA_SPECIFIC_FEATURES("Java Specific Features"),
	COMMON_PROGRAMMING_MISTAKES("Common Programming Mistakes");
	
	private final String designation;

	GroupDesignationMutationOperators(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	@Override
	public String toString() {
		return designation;
	}
}
