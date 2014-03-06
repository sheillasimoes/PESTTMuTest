package domain.util;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class ToStringASTNode {
	public static String toString(ASTNode node) {
		if (node instanceof MethodDeclaration) {
			return toStringMethodDeclaration(node);
		} else if (node instanceof FieldDeclaration) {
			return toStringFieldDeclaration(node);
		} else
			return node.toString();
	}

	@SuppressWarnings("rawtypes")
	private static String toStringMethodDeclaration(ASTNode node) {
		MethodDeclaration methodDeclaration = (MethodDeclaration) node;
		StringBuilder text = new StringBuilder();
		List listAux = methodDeclaration.modifiers();
		// get modifiers
		for (int i = 0; i < listAux.size(); i++) {
			text.append(listAux.get(i).toString() + " ");
		}
		// return type
		if (methodDeclaration.getReturnType2() != null) {
			text.append(methodDeclaration.getReturnType2().toString() + " ");
		}
		// name method
		text.append(methodDeclaration.getName().toString() + "(");

		// get parameters
		listAux = methodDeclaration.parameters();
		for (int i = 0; i < listAux.size(); i++) {
			if (i == (listAux.size() - 1))
				text.append(listAux.get(i).toString());
			else
				text.append(listAux.get(i).toString() + ", ");
		}

		text.append(")");
		return text.toString();
	}

	@SuppressWarnings("rawtypes")
	private static String toStringFieldDeclaration(ASTNode node) {
		FieldDeclaration fieldDeclaration = (FieldDeclaration) node;
		StringBuilder text = new StringBuilder();
		List listAux = fieldDeclaration.modifiers();
		// get modifiers
		for (int i = 0; i < listAux.size(); i++) {
			text.append(listAux.get(i).toString() + " ");
		}

		// type
		text.append(fieldDeclaration.getType().toString() + " ");

		// get variable
		listAux = fieldDeclaration.fragments();
		for (int i = 0; i < listAux.size(); i++) {
			if (i == (listAux.size() - 1))
				text.append(listAux.get(i).toString() + ";");
			else
				text.append(listAux.get(i).toString() + ", ");
		}
		return text.toString();
	}
}
