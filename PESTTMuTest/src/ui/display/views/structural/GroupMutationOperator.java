/**
 * 
 */
package ui.display.views.structural;

import java.util.LinkedList;

/**
 * @author Sheila Simoes
 * 
 */
public enum GroupMutationOperator {

	TRADITIONAL_OPERATOR("Traditional Operator",
			SetMutationOperation.INSTANCE.SetOperation("TO")), ACCESS_CONTROL(
			"Access Control", SetMutationOperation.INSTANCE
					.SetOperation("AC")), INHERITANCE("Inheritance",
			SetMutationOperation.INSTANCE.SetOperation("I")), POLYMORPHISM(
			"Polymorphism", SetMutationOperation.INSTANCE.SetOperation("P")), OVERLOADING(
			"Overloading", SetMutationOperation.INSTANCE.SetOperation("O")), JAVA_SPECIFIC_FEATURES(
			"Java specific features", SetMutationOperation.INSTANCE
					.SetOperation("JSF")), COMMON_PROG_MISTAKES(
			"Common programming mistakes", SetMutationOperation.INSTANCE
					.SetOperation("CPM"));

	private String NameCatg;
	private LinkedList<String> elements;

	private GroupMutationOperator(String NameCatg, LinkedList<String> elements) {
		this.NameCatg = NameCatg;
		this.elements = elements;
	}

	public String getNameCat() {
		return NameCatg;
	}

	public LinkedList<String> getElements() {
		return elements;
	}
}
