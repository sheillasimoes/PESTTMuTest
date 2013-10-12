package domain.tests.instrument;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class JUnitTestRunListener extends RunListener {
	@Override
	public void testStarted(Description description) throws Exception {
		// TODO Auto-generated method stub
		super.testStarted(description);

	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("message " + failure.getMessage());
		/*+ " header "
				+ failure.getTestHeader() + " trace " + failure.getTrace()
				+ " methodname " + failure.getDescription().getMethodName()
				+ " DisplayName " + failure.getDescription().getDisplayName()
				+ " TestClass " + failure.getDescription().getTestClass());
				*/
		super.testFailure(failure);
	}

}
