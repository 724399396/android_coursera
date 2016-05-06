package activitylab.labs.course.activitylab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class Test1_StartActivityOneTest extends
		ActivityInstrumentationTestCase2<ActivityOne> {

	private Solo solo;
	private int timeout = 20000;
	private int sleep = 1000;

	public Test1_StartActivityOneTest() {
		super(ActivityOne.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	// Execution of StartActivityOneTest
	public void testRun() {

		// ==================== Section One =====================
		// Wait for activity: 'ActivityOne'
		assertTrue("StartActivityOneTest failed: " + "Section One:"
				+ "ActivityOne did not correctly load", solo.waitForActivity(
				ActivityOne.class, timeout));

		
		// ==================== Section Two =====================
		// Check for proper counts
		assertTrue("StartActivityOneTest failed:" + "Section Two:"
				+ "onCreate() count was off for ActivityOne",
				solo.waitForText("onCreate\\(\\) calls: 1"));
		assertTrue("StartActivityOneTest failed:" + "Section Two:"
				+ "onStart() count was off for ActivityOne",
				solo.waitForText("onStart\\(\\) calls: 1"));
		assertTrue("StartActivityOneTest failed:" + "Section Two:"
				+ "onResume() count was off for ActivityOne",
				solo.waitForText("onResume\\(\\) calls: 1"));
		assertTrue("StartActivityOneTest failed:" + "Section Two:"
				+ "onRestart() count was off for ActivityOne",
				solo.waitForText("onRestart\\(\\) calls: 0"));

	}

}
