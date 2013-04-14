package domain.ast.visitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PrefixExpression;

public class TesteVisitor extends ASTVisitor {
	private List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	private String operand;

	@Override
	public boolean visit(MethodDeclaration node) {
		methods.add(node);
		return super.visit(node);
	}

	public List<MethodDeclaration> getMethods() {
		return methods;
	}

	@Override
	public boolean visit(PrefixExpression node) {
		operand = node.getOperand().toString();
		return super.visit(node);
	}

	public String getOperand() {
		return operand;
	}
	
}
