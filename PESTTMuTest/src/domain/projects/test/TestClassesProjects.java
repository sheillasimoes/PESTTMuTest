package domain.projects.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.launching.JavaRuntime;

import domain.ast.visitors.TestClassesVisitor;

public class TestClassesProjects {
	private List<String> listTestClasses;
	private TestClassesVisitor testClassesVisitor;

	public TestClassesProjects() {
		testClassesVisitor = new TestClassesVisitor();
		listTestClasses = new LinkedList<String>();
	}

	private void findTestCases(CompilationUnit unit) {
		testClassesVisitor.setFlag(false);
		testClassesVisitor.setIgnoreClass(false);
		unit.accept(testClassesVisitor);
	}

	public boolean isTestClass(CompilationUnit cUnit) {
		// find test cases in a specific file
		findTestCases(cUnit);
		return (testClassesVisitor.isFlag() || testClassesVisitor
				.isIgnoreClass());
	}

	public void addTestClass(ICompilationUnit unit) {
		// filtrar as classes de teste abstrata q devem ser ignoradas
		if (!testClassesVisitor.isIgnoreClass()) {
			// get fully qualified name
			String fullyQualifiedName = unit.findPrimaryType()
					.getFullyQualifiedName();
			// add new test class
			listTestClasses.add(fullyQualifiedName);
		}

	}

	private URLClassLoader getClassLoader(IJavaProject project) {
		String[] classPathEntries;
		try {
			classPathEntries = JavaRuntime
					.computeDefaultRuntimeClassPath(project);
			List<URL> urlList = new ArrayList<URL>();
			for (String path : classPathEntries)
				urlList.add(new Path(path).toFile().toURI().toURL());
			URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
			return new URLClassLoader(urls, this.getClass().getClassLoader());
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
		// checks for test classes in the project
		if (hasTestClasses()) {
			URLClassLoader newClassLoader = getClassLoader(project);
			for (String name : listTestClasses) {
				Class<?> classFile = null;
				try {
					classFile = newClassLoader.loadClass(name);
					listClass.add(classFile);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listClass;
	}

	public void deleteListTestClasses() {
		listTestClasses.clear();
	}

	public boolean hasTestClasses() {
		return listTestClasses.size() > 0 ? true : false;
	}

}
