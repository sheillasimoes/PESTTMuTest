package domain.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import domain.controller.GroundStringController;

public class SourceCodeVisitor extends ASTVisitor {
	private GroundStringController groundStringController;

	public SourceCodeVisitor(GroundStringController groundStringController) {
		this.groundStringController = groundStringController;

	}

	@Override
	public boolean visit(Assignment node) {
		groundStringController.evaluateASTNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(FieldDeclaration node) {
		groundStringController.evaluateASTNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		groundStringController.evaluateASTNode(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationStatement node) {
		groundStringController.evaluateASTNode(node);
		return super.visit(node);
	}
}
