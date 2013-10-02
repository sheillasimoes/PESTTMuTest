package domain.projects;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import domain.ast.visitors.TestClassesVisitor;
import domain.constants.Description;

public class TestClassesProjects {
	private Map<String, List<String>> listTestClasses;
	private TestClassesVisitor testClassesVisitor;

	public TestClassesProjects() {
		testClassesVisitor = new TestClassesVisitor();
		listTestClasses = new HashMap<String, List<String>>();
	}

	private void findTestCases(CompilationUnit unit) {
		testClassesVisitor.setFlag(false);
		unit.accept(testClassesVisitor);
	}

	public boolean isTestClass(CompilationUnit cUnit, String nameProject,
			String nameFile) {
		// find test cases in a specific file
		findTestCases(cUnit);
		boolean flag = testClassesVisitor.isFlag();
		// if it exists, add the list of test classes
		if (flag) {
			// replace in nameFile .java to .class
			String nameClassFile = nameFile.replaceAll(
					Description.FILE_EXTENSION_JAVA,
					Description.FILE_EXTENSION_CLASS);

			// add new test class
			addTestClass(nameProject, nameClassFile);
		}
		return flag;
	}

	public List<IFile> getTestClasses(String nameProject) {
		// list with files .class
		List<IFile> listFiles = new LinkedList<IFile>();

		if (listTestClasses.containsKey(nameProject)) {
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(nameProject);
			// list with names of files .class
			List<String> nameFiles = listTestClasses.get(nameProject);
			for (String name : nameFiles) {
				IFile file = project.getFile(name);
				if (file.exists()) {
					listFiles.add(file);
				}
			}
			// Result result = JUnitCore.runClasses(file.getClass());
			// System.out.println("count failure " + result.getFailureCount());
			// for (Failure failure : result.getFailures()) {
			// System.out.println(failure.toString());
			// }
		}
		return listFiles;
	}

	/**
	 * @return the testClasses
	 */
	public Map<String, List<String>> getAllTestClasses() {
		return listTestClasses;
	}

	public void initializes() {
		listTestClasses.clear();
	}

	private void addTestClass(String nameProject, String nameClassFile) {
		if (!listTestClasses.containsKey(nameProject)) {
			List<String> list = new LinkedList<String>();
			list.add(nameClassFile);
			listTestClasses.put(nameProject, list);
		} else {
			if (!listTestClasses.get(nameProject).contains(nameClassFile))
				listTestClasses.get(nameProject).add(nameClassFile);
		}
	}
}
