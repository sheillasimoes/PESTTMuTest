package ui.display.views.table.structure;

import java.util.List;

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
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ui.constants.Messages;
import domain.ast.visitors.InputProgramVisitor;
import domain.mutation.AssignmentOperatorReplacement;

public class TempMutation_ASR {

	public void apresentarMutASR(Composite parent) {
		// get workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		System.out.print(workspace.toString());
		IWorkspaceRoot root = workspace.getRoot();

		IProject[] setProj = root.getProjects();
		if (setProj.length == 0)
			System.out.println(Messages.NOT_FIND_PROJECTS); // caso n haja projetos
													// refinar esta parte
		else {
			System.out.println(setProj[0].getName());
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
							InputProgramVisitor testeVisitor = new InputProgramVisitor();
							parse.accept(testeVisitor);

							/*for (Assignment node : testeVisitor.getMethods()) {
								AssignmentOperatorReplacement assignmentOperatorReplacement = new AssignmentOperatorReplacement();
								List<ASTNode> aux = assignmentOperatorReplacement
										.getMutation(node);

								l = new Label(parent, SWT.VERTICAL
										| SWT.H_SCROLL);
								// Assignment auxNode = (Assignment) aux.get(0);
								l.setText("Operador: " + aux.size());

							}*/
						}
					}
				}

			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
