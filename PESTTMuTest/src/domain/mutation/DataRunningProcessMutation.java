package domain.mutation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import domain.controller.GroundStringController;
import domain.util.InfoProjectHelper;

public class DataRunningProcessMutation implements Observer {
	private Map<String, List<ASTNode>> data = null;

	public DataRunningProcessMutation(
			GroundStringController groundStringController) {
		data = new HashMap<String, List<ASTNode>>();
		groundStringController.addObserverGroundString(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg != null) {
			ASTNode groundString = (ASTNode) arg;
			String nameProject = InfoProjectHelper.getNameProject(groundString);
			addData(nameProject, groundString);
		} else {
			clearData();
		}
	}

	public Set<String> getSetNamesProjects() {
		return data.keySet();
	}

	public List<ASTNode> getGroundStringFromProject(String nameProject) {
		return data.containsKey(nameProject) ? data.get(nameProject) : null;
	}

	private void clearData() {
		data.clear();

	}

	private void addData(String nameProject, ASTNode groundString) {
		if (!data.containsKey(nameProject)) {
			List<ASTNode> list = new LinkedList<ASTNode>();
			list.add(groundString);
			data.put(nameProject, list);
		} else {
			if (!data.get(nameProject).contains(groundString)) {
				data.get(nameProject).add(groundString);
			}
		}
	}

}
