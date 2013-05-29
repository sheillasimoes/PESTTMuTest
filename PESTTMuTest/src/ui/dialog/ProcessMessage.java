package ui.dialog;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

public enum ProcessMessage {
	INSTANCE;

	public void showInformationMessage(String title, String message) {
		MessageDialog.openInformation(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), title, message);

	}
}
