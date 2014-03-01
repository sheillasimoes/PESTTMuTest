package domain.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import domain.constants.Description;

public class TestClassesVisitor extends ASTVisitor {
	private boolean flag;

	public TestClassesVisitor() {
		flag = false;
	}

	@Override
	public boolean visit(MarkerAnnotation node) {
		if (node.toString().equals(Description.MARKER_ANNOTATION_TEST) && !flag) {
			flag = true;
			return false;
		} else if (flag) {
			return false;
		}

		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		if (!node.isInterface()
				&& node.getSuperclassType() != null
				&& node.getSuperclassType().resolveBinding().getQualifiedName()
						.equals(Description.TYPE_CLASS_EXTENDS_TEST)) {
			flag = true;
			return false;
		}
		return super.visit(node);
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
}
