package main.activator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import ui.display.views.tree.structure.TreeMutationOperators;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "PESTTMuTest"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	private PESTTMuTest pesttMuTest;

	/**
	 * The constructor
	 */
	public Activator() {
		pesttMuTest = new PESTTMuTest();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public TreeMutationOperators getTreeViewer() {
		return pesttMuTest.getTreeViewer();
	}

	public void setTreeViewer(TreeMutationOperators treeViewer) {
		pesttMuTest.setTreeViewer(treeViewer);
	}

	public void runMutationOperators() {
		pesttMuTest.runMutationOperators();
	}

}
