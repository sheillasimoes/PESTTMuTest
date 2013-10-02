package domain.ast.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

import domain.constants.Description;

public class TestClassesVisitor extends ASTVisitor {
	private boolean flag;

	public TestClassesVisitor() {
		flag = false;
	}

	@Override
	public boolean visit(MarkerAnnotation node) {
		if (node.toString().equals(Description.MARKER_ANNOTATION_TEST) && !flag) {
			System.out.println("if " + node.toString() + " " + flag);
			flag = true;
			return false;
		} else if (flag) {
			System.out.println("else if " + node.toString() + " " + flag);
			return false;
		}

		System.out.println("vazio" + node.toString() + " " + flag);
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
