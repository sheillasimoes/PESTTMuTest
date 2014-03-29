package domain.ast.visitors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class LocalInitVisitor extends ASTVisitor {
	private Map<String, ASTNode> initVars;

	public LocalInitVisitor() {
		initVars = new HashMap<String, ASTNode>();
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		if (isLocal(node.getName())) {
			initVars.put(node.getName().resolveBinding().getKey(),
					node.getInitializer() != null ? node : null);
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(Assignment node) {
		if (node.getLeftHandSide() instanceof Name
				&& isLocal((Name) node.getLeftHandSide())) {
			String varName = ((Name) node.getLeftHandSide()).resolveBinding()
					.getKey();
			if (initVars.containsKey(varName) && initVars.get(varName) == null) {
				initVars.put(varName, node);
				node.setProperty("PESTT_VAR_INIT", false);
			} else if (initVars.containsKey(varName)
					&& initVars.get(varName) != null)
				node.setProperty("PESTT_VAR_INIT", true);
		}
		return super.visit(node);
	}

	private boolean isLocal(Name node) {
		IVariableBinding variableBinding = null;
		if (node.resolveBinding() instanceof IVariableBinding) {
			variableBinding = (IVariableBinding) node.resolveBinding();
		}
		return variableBinding == null ? false : (!variableBinding.isField()
				&& !variableBinding.isParameter() && !variableBinding
				.isEnumConstant());
	}
}
