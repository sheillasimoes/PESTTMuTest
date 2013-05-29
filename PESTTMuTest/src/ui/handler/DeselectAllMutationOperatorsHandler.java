package ui.handler;

import main.activator.Activator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import ui.display.views.tree.structure.TreeMutationOperators;

public class DeselectAllMutationOperatorsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TreeMutationOperators treeMutationOperators = Activator.getDefault()
				.getTreeViewer();
		treeMutationOperators.uncheckAllMutationOperators();

		return null;
	}

}
