package domain.mutation;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

public abstract class MutationOperators implements IMutationOperators {

	@Override
	public abstract List<ASTNode> getMutation(ASTNode node);

}
