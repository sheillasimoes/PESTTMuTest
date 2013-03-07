package ui.display.views;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import domain.ast.visitors.Messages;
import domain.ast.visitors.TesteVisitor;

public class ViewMutants extends ViewPart {

	public ViewMutants() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// get workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();

		Label l = new Label(parent, SWT.HORIZONTAL | SWT.H_SCROLL);
		IProject[] setProj = root.getProjects();
		if (setProj.length == 0)
			l.setText(Messages.NOT_FIND_PROJECTS); // caso ñ haja projetos
													// refinar esta parte
		else
			l.setText(setProj[0].getName());
		IProject proj = setProj[0];

		// obter as packages do projeto
		try {
			IPackageFragment[] packageFragments = JavaCore.create(proj)
					.getPackageFragments();
			// obter a package source do projeto
			for (IPackageFragment mypackage : packageFragments) {
				if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
					// obter os ficheiros .java
					for (ICompilationUnit unit : mypackage
							.getCompilationUnits()) {
						ASTParser parser = ASTParser.newParser(AST.JLS4);
						parser.setKind(ASTParser.K_COMPILATION_UNIT);
						parser.setSource(unit);
						parser.setResolveBindings(true);
						CompilationUnit parse = (CompilationUnit) parser
								.createAST(null);
						TesteVisitor testeVisitor = new TesteVisitor();
						parse.accept(testeVisitor);

						for (MethodDeclaration method : testeVisitor
								.getMethods()) {
							// l = new Label(parent, SWT.VERTICAL |
							// SWT.H_SCROLL);
							l.setText("Method name: " + method.getName()
									+ " Return type: "
									+ method.getReturnType2() + "\n");
						}
					}
				}
			}

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
