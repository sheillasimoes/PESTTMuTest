package main.activator;

import java.net.URLClassLoader;
import java.util.List;
import java.util.Observer;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import domain.mutation.Mutation;
import domain.mutation.operators.IMutationOperators;
import domain.util.InfoProjectHelper;
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

	public URLClassLoader classLoader;

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

	public void startProcessTest() {
		pesttMuTest.startProcessTest();
	}

	public void runAllMutations() {
		pesttMuTest.runAllMutations();
	}

	public void runRandomMutations() {
		pesttMuTest.runRandomMutations();
	}

	public String getProjectName(ASTNode node) {
		return pesttMuTest.getProjectName(node);
	}

	public String getFullyQualifiedName(ASTNode node) {
		return InfoProjectHelper.getFullyQualifiedName(node);
	}

	public void addObserverGroundStringController(Observer o) {
		pesttMuTest.addObserverGroundStringController(o);
	}

	public void deleteObserverGroundStringController(Observer o) {
		pesttMuTest.deleteObserverGroundStringController(o);
	}

	public void addObserverOperatorsController(Observer o) {
		pesttMuTest.addObserverOperatorsController(o);
	}

	public void deleteObserverOperatorsController(Observer o) {
		pesttMuTest.deleteObserverOperatorsController(o);
	}

	public void addObserverGroundString(Observer o) {
		pesttMuTest.addObserverGroundString(o);
	}

	public void deleteObserverGroundString(Observer o) {
		pesttMuTest.deleteObserverGroundString(o);
	}

	public void addObserverMutationOperators(Observer o) {
		pesttMuTest.addObserverMutationOperators(o);
	}

	public void deleteObserverMutationOperators(Observer o) {
		pesttMuTest.deleteObserverMutationOperators(o);
	}

	public List<ASTNode> getListGroundString() {
		return pesttMuTest.getListGroundString();
	}

	public void setSelectedGroundString(ASTNode node) {
		pesttMuTest.setSelectedGroundString(node);
	}

	public ASTNode getSelectedGroundString() {
		return pesttMuTest.getSelectedGroundString();
	}

	public List<IMutationOperators> getOperatorsApplicable() {
		return pesttMuTest.getOperatorsApplicable();
	}

	public void setSelectedIMutOperator(IMutationOperators operator) {
		pesttMuTest.setSelectedIMutOperator(operator);
	}

	public IMutationOperators getSelectedIMutOperator() {
		return pesttMuTest.getSelectedIMutOperator();
	}

	public void verifyChangesOperators() {
		pesttMuTest.verifyChangesOperators();
	}

	public List<Mutation> getMutantsToDisplay() {
		return pesttMuTest.getMutantsToDisplay();
	}

}
