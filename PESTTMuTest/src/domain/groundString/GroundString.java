package domain.groundString;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.util.ToStringASTNode;

public class GroundString {
	private ASTNode groundString;

	public GroundString(ASTNode groundString) {
		this.groundString = groundString;
	}

	public ASTNode getGroundString() {
		return groundString;
	}

	@Override
	public String toString() {
		return ToStringASTNode.toString(groundString);
	}
}
