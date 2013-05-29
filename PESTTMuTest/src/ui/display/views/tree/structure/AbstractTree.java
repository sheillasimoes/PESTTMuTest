/**
 * 
 */
package ui.display.views.tree.structure;

import java.util.LinkedList;

/**
 * @author Sheilla Simoes
 * 
 */
public class AbstractTree {
	private String acronym;
	private String description;
	private AbstractTree parent;
	private LinkedList<AbstractTree> children;

	/**
	 * 
	 */
	public AbstractTree(String description, String acronym) {
		this.acronym = acronym;
		this.description = description;
		children = new LinkedList<AbstractTree>();
	}

	/**
	 * @return the name
	 */
	public String getAcronym() {
		return acronym;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * @return the treeParent
	 */
	public AbstractTree getParent() {
		return parent;
	}

	/**
	 * @param treeParent
	 *            the treeParent to set
	 */
	public void setParent(AbstractTree parent) {
		this.parent = parent;
	}

	public void addChild(AbstractTree child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(AbstractTree child) {
		children.remove(child);
		child.setParent(null);
	}

	public AbstractTree[] getChildren() {
		return (AbstractTree[]) children.toArray(new AbstractTree[children
				.size()]);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	public String toString() {
		return description;
	}

}
