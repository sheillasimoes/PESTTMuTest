package domain.factories;

import domain.mutation.IMutationOperators;

/**
 * 
 * @author Sheila Simoes
 * 
 */
public abstract class AbstractFactoryMutationOperators {
	public abstract IMutationOperators createMutationOperator(Object acronym);
}
