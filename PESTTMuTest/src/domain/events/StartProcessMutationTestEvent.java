package domain.events;

import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.ProjectController;
import domain.constants.Messages;
import ui.dialog.ProcessMessage;

public class StartProcessMutationTestEvent {
	public void execute(Object[] elements,
			MutationOperatorsController operatorsController,
			GroundStringController groundStringController,
			ProjectController projectController) {
		// checks if any was selected mutation operator
		if (elements.length == 0) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_SELECT_ELEMENTS_TREE);
		} else {

			if (groundStringController.getSelectedGroundString() != null
					|| operatorsController.getSelectedIMutOperator() != null
					|| projectController.getProjectNameSelected() != null) {

				operatorsController.setSelectedIMutOperator(null);
				groundStringController.setSelectedGroundString(null);
				projectController.setProjectNameSelected(null);
			}
			operatorsController.createMutationOperators(elements);
			groundStringController.initializeListGroundString();
			projectController.createCopiesProjects();

			// valida se existem projetos java no workspace
			if (projectController.getProjectNames().size() == 0) {
				ProcessMessage.INSTANCE.showInformationMessage("Info",
						Messages.NOT_FIND_PROJECTS);
			}
		}
	}
}
