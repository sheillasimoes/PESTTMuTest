/**
 * 
 */
package ui.handler;

import main.activator.Activator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author Sheilla Simoes
 * 
 */
public class SelectAllMutationOperatorsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Activator.getDefault().getTreeViewer().checkAllMutationOperators();

		return null;
	}
}
