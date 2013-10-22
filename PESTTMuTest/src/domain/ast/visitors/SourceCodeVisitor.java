package domain.ast.visitors;

import java.util.List;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import domain.constants.EnumModifierKeyword;
import domain.controller.GroundStringController;
import domain.util.FileChangeHelper;

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
		// groundStringController.evaluateASTNode(node);
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		// AST ast = cUnit.getAST();
		// ASTRewrite rewrite = ASTRewrite.create(ast);
		System.out.println("antes " + node.toString());
		BodyDeclaration d = (BodyDeclaration) node;
		System.out.println(d.getModifiers());
		List modifiers = node.modifiers();

		System.out.println();

		if (modifiers.size() == 0) {
			System.out.println("sem modifiers");
		} else {
			Modifier m = (Modifier) modifiers.get(0);
			if (!m.getKeyword().equals(
					EnumModifierKeyword.PUBLIC_KEYWORD.getModifierKeyword())
					&& !m.getKeyword().equals(
							EnumModifierKeyword.PRIVATE_KEYWORD
									.getModifierKeyword())
					&& !m.getKeyword().equals(
							EnumModifierKeyword.PROTECTED_KEYWORD
									.getModifierKeyword())) {
				System.out.println("passou");
			}
			// Modifier m = (Modifier) node.modifiers().get(0);
			// m.setKeyword(Modifier.ModifierKeyword.PRIVATE_KEYWORD);
			// ICompilationUnit copy;
			// try {
			// copy = unit.getWorkingCopy(null);
			// // FileChangeHelper.changeICompilationUnit(copy, rewrite,
			// // cUnit);
			// IBuffer buffer = ((IOpenable) copy).getBuffer();
			// buffer.setContents(cUnit.toString());
			// FileChangeHelper.saveChange(copy);
			// FileChangeHelper.discardWorkingCopy(copy);
			// System.out.println("depois " + node.toString());
			// System.out.println("compilation " + cUnit.toString());
			// } catch (JavaModelException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		// groundStringController.evaluateASTNode(node);
		return super.visit(node);
	}
}
