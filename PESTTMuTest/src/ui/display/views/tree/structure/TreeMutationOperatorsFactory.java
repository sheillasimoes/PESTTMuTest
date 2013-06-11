package ui.display.views.tree.structure;

import java.util.ArrayList;
import java.util.List;

import ui.constants.DesignationMutationOperators;
import ui.constants.GroupDesignationMutationOperators;

public class TreeMutationOperatorsFactory {

	public static AbstractTree createTreeGroupMutationOperators(
			GroupDesignationMutationOperators group) {
		AbstractTree tree = null;
		switch (group) {
		case TRADITIONAL_OPERATOR:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		case INFORMATION_HIDING:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		case INHERITANCE:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		case POLYMORPHISM:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		case OVERLOADING:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		case JAVA_SPECIFIC_FEATURES:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		case COMMON_PROGRAMMING_MISTAKES:
			tree = new AbstractTree(group);
			tree.addChildren(getElementsOfGroup(group));
			return tree;
		}
		return tree;
	}

	private static List<AbstractTree> getElementsOfGroup(
			GroupDesignationMutationOperators group) {
		List<AbstractTree> list = new ArrayList<>();
		List<DesignationMutationOperators> listOperators = DesignationMutationOperators
				.getElementsOfGroup(group);
		for (DesignationMutationOperators element : listOperators) {
			list.add(new AbstractTree(element));
		}
		return list;
	}

}
