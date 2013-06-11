package domain.factories;

import java.util.ArrayList;
import java.util.List;

import domain.mutation.IMutationOperators;

import ui.constants.DesignationMutationOperators;
import ui.constants.GroupDesignationMutationOperators;
import ui.display.views.tree.structure.AbstractTree;

public class MutationOperatorsFactory {

	public List<IMutationOperators> getInstanceOfOperators(Object[] elements) {
		List<IMutationOperators> listOperators = new ArrayList<IMutationOperators>();

		for (Object element : elements) {
			AbstractTree selectedOperator = (AbstractTree) element;
			if (selectedOperator.getData() instanceof DesignationMutationOperators) {
				AbstractTree groupOperator = selectedOperator.getParent();
				listOperators.add(createOperator(selectedOperator.getData(),
						groupOperator.getData()));
			}
		}
		return listOperators;
	}

	private IMutationOperators createOperator(Object acronym, Object goup) {
		GroupDesignationMutationOperators groupOperators = (GroupDesignationMutationOperators) goup;

		switch (groupOperators) {
		case TRADITIONAL_OPERATOR:
			return new TraditionalOperatorFactory()
					.createMutationOperator(acronym);
		case INFORMATION_HIDING:
			return new InformationHidingFactory()
					.createMutationOperator(acronym);
		case INHERITANCE:
			return new InheritanceFactory().createMutationOperator(acronym);
		case POLYMORPHISM:
			return new PolymorphismFactory().createMutationOperator(acronym);
		case OVERLOADING:
			return new OverloadingFactory().createMutationOperator(acronym);
		case JAVA_SPECIFIC_FEATURES:
			return new JavaSpecificFeaturesFactory()
					.createMutationOperator(acronym);
		case COMMON_PROGRAMMING_MISTAKES:
			return new CommonProgrammingMistakesFactory()
					.createMutationOperator(acronym);
		}
		return null;
	}
}
