package domain.ast.visitors;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;

import domain.mutation.AssignmentOperatorReplacement;
import domain.mutation.IMutationOperators;

public class SourceCodeVisitor extends ASTVisitor {
	private List<ASTNode> listGroundString;

	public SourceCodeVisitor() {
		listGroundString = new LinkedList();
	}

	@Override
	public boolean visit(Assignment node) {
		AssignmentOperatorReplacement operatorReplacement = new AssignmentOperatorReplacement();
		listGroundString.add(node);
		return super.visit(node);
	}

	public List<ASTNode> getList() {
		return listGroundString;
	}

}
