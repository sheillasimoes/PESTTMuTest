package ui.display.views.tree.structure;

import ui.constants.GroupDesignationMutationOperators;

public enum DefTreeMutationOperators {
	INSTANCE;

	public AbstractTree getTreeMutationOperators() {

		AbstractTree invisibleRoot = new AbstractTree("");
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.TRADITIONAL_OPERATOR));
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.INFORMATION_HIDING));
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.INHERITANCE));
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.POLYMORPHISM));
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.OVERLOADING));
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.JAVA_SPECIFIC_FEATURES));
		invisibleRoot
				.addChild(TreeMutationOperatorsFactory
						.createTreeGroupMutationOperators(GroupDesignationMutationOperators.COMMON_PROGRAMMING_MISTAKES));
		return invisibleRoot;

	}

}
