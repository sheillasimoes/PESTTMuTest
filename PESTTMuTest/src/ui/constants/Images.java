package ui.constants;

import main.activator.Activator;

import org.eclipse.swt.graphics.Image;

public class Images {

	public static final String LIVEMUTANT = "icons/liveMutant.gif";
	public static final String KILLEDMUTANT = "icons/killedMutant.gif";

	public static Image getImage(String file) {

		return Activator.getImageDescriptor(file).createImage();
	}
}
