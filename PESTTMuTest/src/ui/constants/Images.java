package ui.constants;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class Images {

	public static final String LIVEMUTANT = "liveMutant.gif";
	public static final String KILLEDMUTANT = "killedMutant.gif";

	public static Image getImage(Class<?> classe, String file) {
		// assume that the current class is called View.java
		Bundle bundle = FrameworkUtil.getBundle(classe);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();

	}
}
