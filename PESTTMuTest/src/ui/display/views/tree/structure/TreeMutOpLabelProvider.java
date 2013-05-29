/**
 * 
 */
package ui.display.views.tree.structure;

import org.eclipse.jface.viewers.LabelProvider;

import org.eclipse.swt.graphics.Image;

/**
 * @author Sheila Simoes
 * 
 */
public class TreeMutOpLabelProvider extends LabelProvider {

	public String getText(Object obj) {
		return obj.toString();
	}

	public Image getImage(Object obj) {
		return null;
	}

}
