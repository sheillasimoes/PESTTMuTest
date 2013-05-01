/**
 * 
 */
package ui.display.views.tree.structure;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Sheila Simoes
 * 
 */
public class TreeMutOpLabelProvider extends LabelProvider {

	public String getText(Object obj) {
		return obj.toString();
	}

	public Image getImage(Object obj) {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		if (obj instanceof AbstractTree)
			imageKey = ISharedImages.IMG_OBJS_DND_LEFT_MASK;
		return PlatformUI.getWorkbench().getSharedImages().getImage("tree_mode.gif");
	}
}
