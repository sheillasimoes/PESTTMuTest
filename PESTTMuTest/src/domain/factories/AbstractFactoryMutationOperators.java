package domain.factories;

import domain.mutation.operators.IMutationOperators;

/**
 * 
 * @author Sheila Simoes
 * 
 */
public abstract class AbstractFactoryMutationOperators {
	public abstract IMutationOperators createMutationOperator(Object acronym);
}
