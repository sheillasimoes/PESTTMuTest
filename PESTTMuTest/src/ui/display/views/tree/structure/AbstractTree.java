/**
 * 
 */
package ui.display.views.tree.structure;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Sheilla Simoes
 * 
 */
public class AbstractTree {
	private Object data;
	private AbstractTree parent;
	private List<AbstractTree> children;

	/**
	 * 
	 */
	public AbstractTree(Object data) {
		this.data = data;
		children = new LinkedList<AbstractTree>();
	}

	public Object getData() {
		return data;
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

	public void addChildren(List<AbstractTree> children) {
		for (AbstractTree child : children) {
			addChild(child);
		}
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
