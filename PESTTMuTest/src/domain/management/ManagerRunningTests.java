package domain.management;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import main.activator.Activator;

public class ManagerRunningTests {
	private Class<?> jUnitCore;
	private Object jUnitCoreObj;
	
	public ManagerRunningTests() {
		try {
			jUnitCore = Activator.getDefault().classLoader.loadClass("org.junit.runner.JUnitCore");
			jUnitCoreObj = jUnitCore.newInstance();
			Class<?> parm = Activator.getDefault().classLoader.loadClass("org.junit.runner.notification.RunListener");
			Method m = jUnitCore.getDeclaredMethod("addListener", new Class[] {parm});
			Class<?> parm1 = Activator.getDefault().classLoader.loadClass("domain.tests.instrument.JUnitTestRunListener");
			m.invoke(jUnitCoreObj,parm1.newInstance());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	public void runTest(Class<?> clazz) {
		Method m;
		try {
			m = jUnitCore.getDeclaredMethod("run", new Class[] {Class[].class});
			m.invoke(jUnitCoreObj, (Object) new Class[] {clazz});
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
