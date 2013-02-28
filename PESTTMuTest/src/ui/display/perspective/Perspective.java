package ui.display.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import ui.constants.Description;

public class Perspective implements IPerspectiveFactory {

	IPageLayout layout;

	public Perspective() {
		super();
	}

	@Override
	public void createInitialLayout(IPageLayout layout) {
		this.layout = layout;
		createInitialLayout();
	}

	private void createInitialLayout() {
		addViews();
	}

	private void addViews() {
		// Get the editor area.
		String editorArea = layout.getEditorArea();

		// Top right:
		IFolderLayout topRight = layout.createFolder("topRight",
				IPageLayout.RIGHT, 0.70f, editorArea);
		topRight.addView(Description.VIEW_MUTATION_OPERATOR);

		// Botton:
		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, 0.70f, editorArea);
		bottom.addView(Description.VIEW_MUTANTS);
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addView(IPageLayout.ID_TASK_LIST);

		// Top left:
		IFolderLayout topLeft = layout.createFolder("topLeft",
				IPageLayout.LEFT, 0.30f, editorArea);
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
	}

}
