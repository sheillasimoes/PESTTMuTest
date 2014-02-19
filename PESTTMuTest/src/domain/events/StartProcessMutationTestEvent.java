package domain.events;

import domain.controller.GroundStringController;
import domain.controller.MutationOperatorsController;
import domain.controller.MutationsController;
import domain.controller.ProjectController;
import domain.constants.Messages;
import ui.dialog.ProcessMessage;

public class StartProcessMutationTestEvent {
	public void execute(Object[] elements,
			MutationOperatorsController operatorsController,
			GroundStringController groundStringController,
			ProjectController projectController,
			MutationsController mutationsController) {
		// checks if any was selected mutation operator
		if (elements.length == 0) {
			ProcessMessage.INSTANCE.showInformationMessage("Info",
					Messages.NOT_SELECT_ELEMENTS_TREE);
		} else {

			if (groundStringController.getSelectedGroundString() != null
					|| operatorsController.getSelectedIMutOperator() != null
					|| projectController.getProjectNameSelected() != null
					|| mutationsController.getClass() != null) {

				operatorsController.setSelectedIMutOperator(null);
				groundStringController.setSelectedGroundString(null);
				projectController.setProjectNameSelected(null);
				mutationsController.setSelectedMutation(null);

			}
			operatorsController.createMutationOperators(elements);
			groundStringController.initializeListGroundString();
			mutationsController.deleteTestResult();
			projectController.createCopiesProjects();

			// valida se existem projetos java no workspace
			if (projectController.getProjectNames().size() == 0) {
				ProcessMessage.INSTANCE.showInformationMessage("Info",
						Messages.NOT_FIND_PROJECTS);
			}
		}
	}
}
