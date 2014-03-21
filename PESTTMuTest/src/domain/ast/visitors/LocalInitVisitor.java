package domain.ast.visitors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class LocalInitVisitor extends ASTVisitor {
	private Map<String, ASTNode> initVars;
	private String methodName;
	
	public LocalInitVisitor() {
		initVars = new HashMap<String, ASTNode>();
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		methodName = node.getName().getFullyQualifiedName();
		return super.visit(node);
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		initVars.put(node.getName().getFullyQualifiedName(), node.getInitializer() != null ? node : null);
		return super.visit(node);
	}

	@Override
	public boolean visit(Assignment node) {
		if (node.getLeftHandSide() instanceof SimpleName) {
			String varName = methodName + ((SimpleName) node.getLeftHandSide()).getFullyQualifiedName();
			if (initVars.containsKey(varName) && initVars.get(varName) == null) {
				initVars.put(varName, node);
				node.setProperty("PESTT_VAR_INIT", false);
			} else if (initVars.containsKey(varName) && initVars.get(varName) != null)
				node.setProperty("PESTT_VAR_INIT", true);
		}
		return super.visit(node);
	}
}
