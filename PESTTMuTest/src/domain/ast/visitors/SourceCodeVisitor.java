package domain.ast.visitors;

import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

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

	public void teste(Assignment node) {
		System.out.println("flag before" + node.getFlags());
		// criação de ASTRewrite
		AST ast = node.getAST();
		CompilationUnit cUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit unit = (ICompilationUnit) cUnit.getJavaElement();
		ASTRewrite rewrite = ASTRewrite.create(ast);

		// descrição da alteração
		Assignment.Operator f = Assignment.Operator.PLUS_ASSIGN;
		node.setOperator(f);
		rewrite.set(node, Assignment.OPERATOR_PROPERTY, f, null);
		System.out.println(cUnit.toString());

		// criação de um Documento
		Document document = null;

		// cálculo do novo código fonte
		try {
			// Criar a cópia de trabalho
			ICompilationUnit workingCopy;

			workingCopy = unit.getWorkingCopy(null);

			// document = new Document(workingCopy.getSource());//
			// getBuffer().getContents());

			// cálculo das edições de texto
			TextEdit edits = rewrite.rewriteAST(); // rewrite.rewriteAST(document,
													// unit.getJavaProject()
			// .getOptions(true));
			document = new Document(workingCopy.getSource());
			edits.apply(document);
			// atualização da unidade de compilação

			System.out.println(document.get());

			// Modificar o buffer e reconciliar
			IBuffer buffer = ((IOpenable) workingCopy).getBuffer();
			// System.out.println("antes" + buffer.getContents());
			// // // buffer.append("class X {}");
			buffer.setContents(cUnit.toString());
			System.out.println("depois" + buffer.getContents());
			// verificarErrosCompil(workingCopy);
			workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);

			// Consolidar as alterações
			workingCopy.commitWorkingCopy(false, null);

			// Destruir a cópia de trabalho
			workingCopy.discardWorkingCopy();
			System.out.println(document.get());
		} catch (MalformedTreeException | BadLocationException
				| JavaModelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
