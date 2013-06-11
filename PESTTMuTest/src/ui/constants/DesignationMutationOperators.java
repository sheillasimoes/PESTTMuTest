package ui.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Ver se irei precisar desta class
 * 
 */
public enum DesignationMutationOperators {
	
	// Traditional Operator
	ABS("Absolute Value Insertion", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR), 
	AOR("Arithmetic Operator Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	ROR("Relational Operator Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	COR("Conditional Operator Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	SOR("Shift Operator Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),// VER SE ESTE OPERADOR PODE SER APLICADO EM JAVA
	LOR("Logical Operator Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),// VER SE ESTE OPERADOR PODE SER APLICADO EM JAVA
	ASR("Assignment Operator Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	UOI("Unary Operator Insertion", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	UOD("Unary Operator Deletion", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	SVR("Scalar Variable Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	BSR("Bomb Statement Replacement", GroupDesignationMutationOperators.TRADITIONAL_OPERATOR),
	
	// Information Hiding (Access Control)
	AMC("Access Modifier Change", GroupDesignationMutationOperators.INFORMATION_HIDING),
	
	// Inheritance
	IHD("Hiding Variable Deletion", GroupDesignationMutationOperators.INHERITANCE),
	IHI("Hiding Variable Insertion", GroupDesignationMutationOperators.INHERITANCE),
	IOD("Overriding Method Deletion", GroupDesignationMutationOperators.INHERITANCE),
	IOP("Overridden Method Calling Position Change", GroupDesignationMutationOperators.INHERITANCE),
	IOR("Overridden Method Rename", GroupDesignationMutationOperators.INHERITANCE),
	ISK("Super Keyword Deletion", GroupDesignationMutationOperators.INHERITANCE), 
	IPC("Explicit Call of a Parent’s Constructor Deletion", GroupDesignationMutationOperators.INHERITANCE),
	
	// Polymorphism
	PNC("New Method Call with Child Class Type", GroupDesignationMutationOperators.POLYMORPHISM),
	PMD("Member Variable Declaration with Parent Class Type", GroupDesignationMutationOperators.POLYMORPHISM),
	PPD("Parameter Variable Declaration with Child Class Type", GroupDesignationMutationOperators.POLYMORPHISM),
	PRV("Reference Assignment with Other Compatible Type", GroupDesignationMutationOperators.POLYMORPHISM),
	
	// Overloading
	OMR("Overloading Method Contents Change", GroupDesignationMutationOperators.OVERLOADING),
	OMD("Overloading Method Deletion", GroupDesignationMutationOperators.OVERLOADING),
	OAO("Argument Order Change", GroupDesignationMutationOperators.OVERLOADING),
	OAN("Argument Number Change", GroupDesignationMutationOperators.OVERLOADING),
	
	// Java Specific Features
	JTD("This Keyword Deletion", GroupDesignationMutationOperators.JAVA_SPECIFIC_FEATURES),
	JSC("Static Modifier Change", GroupDesignationMutationOperators.JAVA_SPECIFIC_FEATURES),
	JID("Member Variable Initialization Deletion", GroupDesignationMutationOperators.JAVA_SPECIFIC_FEATURES),
	JDC("Java-supported Default Constructor Create", GroupDesignationMutationOperators.JAVA_SPECIFIC_FEATURES),
	
	// Common Programming Mistakes
	EOA("Reference Assignment and Content Assignment", GroupDesignationMutationOperators.COMMON_PROGRAMMING_MISTAKES),
	EOC("Reference Comparison and Content Comparison Replacement", GroupDesignationMutationOperators.COMMON_PROGRAMMING_MISTAKES),
	EAM("Accessor Method Change", GroupDesignationMutationOperators.COMMON_PROGRAMMING_MISTAKES), 
	EMM("Modifier Method Change", GroupDesignationMutationOperators.COMMON_PROGRAMMING_MISTAKES);
	
	private final String designation;
	private final GroupDesignationMutationOperators group;

	DesignationMutationOperators(String designation, GroupDesignationMutationOperators group) {
		this.designation = designation;
		this.group = group;
	}

	public String getDesignation() {
		return designation;
	}
	
	public GroupDesignationMutationOperators getGroup(){
		return group;
	}
	
	public static List<DesignationMutationOperators> getElementsOfGroup(GroupDesignationMutationOperators group) {
		List<DesignationMutationOperators> list = new ArrayList<>();
		DesignationMutationOperators[] elements = values();
		for (DesignationMutationOperators element : elements) {
			if (element.getGroup().equals(group)) {
				list.add(element);
			}
		}
		return list;
	}
	
	@Override
	public String toString() {
		return designation;
	}
}
