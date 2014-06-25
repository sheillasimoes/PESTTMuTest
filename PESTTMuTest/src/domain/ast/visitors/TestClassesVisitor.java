package domain.ast.visitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import domain.constants.Description;

public class TestClassesVisitor extends ASTVisitor {
	private boolean flag;
	private boolean ignoreClass;

	@Override
	public boolean visit(MarkerAnnotation node) {
		if (node.toString().equals(Description.MARKER_ANNOTATION_TEST) && !flag) {
			flag = true;
			return false;
		} else if (flag || ignoreClass) {
			return false;
		}

		return super.visit(node);
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		if (!node.isInterface() && node.getSuperclassType() != null
				&& isAbstractClass(node)
				&& isATestClass(node.getSuperclassType().resolveBinding())) {
			ignoreClass = true;
			return false;
		} else if (!node.isInterface() && node.getSuperclassType() != null
				&& !isAbstractClass(node)
				&& isATestClass(node.getSuperclassType().resolveBinding())) {
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

	/**
	 * @return the ignoreClass
	 */
	public boolean isIgnoreClass() {
		return ignoreClass;
	}

	/**
	 * @param ignoreClass
	 *            the ignoreClass to set
	 */
	public void setIgnoreClass(boolean ignoreClass) {
		this.ignoreClass = ignoreClass;
	}

	private boolean isATestClass(ITypeBinding node) {
		if (node.getBinaryName().equals(Description.TYPE_CLASS_EXTENDS_TEST)
				|| node.getBinaryName().equals(
						Description.TYPE_CLASS_EXTENDS_TESTSUITE)) {
			return true;
		} else if (node.getBinaryName().equals("java.lang.Object")) {
			return false;
		} else
			return isATestClass(node.getSuperclass());
	}

	@SuppressWarnings("rawtypes")
	private boolean isAbstractClass(TypeDeclaration node) {
		List modifiers = node.modifiers();
		for (int i = 0; i < modifiers.size(); i++) {
			if (modifiers.get(i) instanceof Modifier
					&& ((Modifier) modifiers.get(i)).isAbstract())
				return true;
		}
		return false;
	}
}
