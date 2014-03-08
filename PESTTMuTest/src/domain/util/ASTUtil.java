package domain.util;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTUtil {
	/**
	 * Reads a ICompilationUnit and creates the AST DOM for manipulating the
	 * Java source file
	 * 
	 * @param unit
	 * @return
	 */
	public static CompilationUnit parse(ICompilationUnit unit) {
		// create a AST of the source code
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		return (CompilationUnit) parser.createAST(null);
	}

	public static int getLineNumber(ASTNode node) {
		CompilationUnit unit = (CompilationUnit) node.getRoot();
		if (node instanceof BodyDeclaration
				&& ((BodyDeclaration) node).getJavadoc() != null) {
			return unit.getLineNumber(node.getStartPosition()
					+ ((BodyDeclaration) node).getJavadoc().getLength()) + 1;
		} else
			return unit.getLineNumber(node.getStartPosition());
	}
}
