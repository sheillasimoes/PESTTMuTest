package ui.handler;

import main.activator.Activator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import domain.constants.Description;

public class SeeAllMutantsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Activator.getDefault().changeTypeViewResult(Description.TYPE_VIEW_ALL_MUTANTS);
		return null;
	}

}
