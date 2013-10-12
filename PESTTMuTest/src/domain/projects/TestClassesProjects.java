package domain.projects;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.junit.JUnitCore;
import org.eclipse.jdt.launching.JavaRuntime;

import domain.ast.visitors.TestClassesVisitor;

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

	public boolean isTestClass(CompilationUnit cUnit, ICompilationUnit unit,
			String nameProject) {
		// find test cases in a specific file
		findTestCases(cUnit);
		boolean flag = testClassesVisitor.isFlag();
		// if it exists, add the list of test classes
		if (flag) {
			// get fully qualified name
			String fullyQualifiedName = unit.findPrimaryType()
					.getFullyQualifiedName();
			// add new test class
			addTestClass(nameProject, fullyQualifiedName);
		}
		return flag;
	}

	private URLClassLoader getClassLoader(IJavaProject project) {
		String[] classPathEntries;
		try {
			classPathEntries = JavaRuntime
					.computeDefaultRuntimeClassPath(project);
			List<URL> urlList = new ArrayList<URL>();
			for (int i = 0; i < classPathEntries.length; i++) {
				String entry = classPathEntries[i];
				IPath path = new Path(entry);
				URL url = path.toFile().toURI().toURL();
				urlList.add(url);
			}
			ClassLoader parentClassLoader = project.getClass().getClassLoader();
			URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
			return new URLClassLoader(urls, JUnitCore.class.getClassLoader());
//			return new URLClassLoader(urls, parentClassLoader);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Class<?>> getTestClasses(IJavaProject project) {
		// list with files .class
		List<Class<?>> listClass = new ArrayList<Class<?>>();

		// name project
		String nameProject = project.getElementName();
		// checks for test classes in the project
		if (hasTestClasses(nameProject)) {
			List<String> listQualifiedNames = listTestClasses.get(nameProject);
			URLClassLoader classLoader = getClassLoader(project);
			try {
				System.out.println("ceninha: " + classLoader.loadClass("org.JUnit.Test"));//.equals(Test.class));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (String name : listQualifiedNames) {
				Class<?> classFile = null;
				try {
					classFile = classLoader.loadClass(name);
					listClass.add(classFile);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return listClass;
	}

	public void clearData() {
		listTestClasses.clear();
	}

	public boolean hasTestClasses(String nameProject) {
		return listTestClasses.containsKey(nameProject);
	}

	private void addTestClass(String nameProject, String nameClassFile) {
		if (!listTestClasses.containsKey(nameProject)) {
			List<String> list = new LinkedList<String>();
			list.add(nameClassFile);
			listTestClasses.put(nameProject, list);
		} else {
			if (!listTestClasses.get(nameProject).contains(nameClassFile)) {
				listTestClasses.get(nameProject).add(nameClassFile);
			}
		}
	}
}
