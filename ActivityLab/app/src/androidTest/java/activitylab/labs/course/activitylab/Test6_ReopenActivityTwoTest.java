package activitylab.labs.course.activitylab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class Test6_ReopenActivityTwoTest extends ActivityInstrumentationTestCase2<ActivityOne> {
	
	private Solo solo;
	private int timeout = 20000;
	private int sleep = 1000;

	public Test6_ReopenActivityTwoTest() {
		super(ActivityOne.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	// Executes the ReopenActivityTwoTest
	public void testRun() {
		
		// ==================== Section One =====================
		// Wait for activity: 'ActivityOne'
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section One:" +
				   "ActivityOne did not load correctly",
				   solo.waitForActivity(ActivityOne.class, timeout));
		
		solo.waitForView(R.id.bLaunchActivityTwo);		
		
		// ==================== Section Two =====================
		// Click on Start Activity Two
		solo.clickOnView(solo.getView(R.id.bLaunchActivityTwo));
		
		// Wait for activity: 'ActivityTwo'
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Two:" +
				   "ActivityTwo did not load correctly",
				   solo.waitForActivity(ActivityTwo.class, timeout));
		
		solo.waitForView(R.id.bClose);
		
		solo.sleep(sleep);
		
		// ==================== Section Three =====================
		// Click on Close Activity
		solo.clickOnView(solo.getView(R.id.bClose));

		// Wait for activity: 'ActivityOne'
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Three:" +
				   "ActivityTwo did not close correctly",
				   solo.waitForActivity(ActivityOne.class, timeout));
		
		solo.waitForView(R.id.bLaunchActivityTwo);
		
		solo.sleep(sleep);
		
		// ==================== Section Four =====================
		// Click on Start Activity Two
		solo.clickOnView(solo.getView(R.id.bLaunchActivityTwo));
		
		// Wait for activity: 'ActivityTwo'
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Four:" +
				   "ActivityTwo did not reopen correctly after being closed",
				   solo.waitForActivity(ActivityTwo.class, timeout));
		
		solo.waitForView(R.id.bClose);
		
		// Check for proper counts
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Four:" +
				   "onCreate() count was off for ActivityTwo after being reopened for a second time.",
				   solo.waitForText("onCreate\\(\\) calls: 1"));
		
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Four:" +
				   "onStart() count was off for ActivityTwo after being reopened for a second time.",
				   solo.waitForText("onStart\\(\\) calls: 1"));
		
		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Four:" +
				   "onResume() count was off for ActivityTwo after being reopened for a second time.",
				   solo.waitForText("onResume\\(\\) calls: 1"));

		assertTrue("ReopenActivityTwoTest failed:" +
				   "Section Four:" +
				   "onRestart() count was off for ActivityTwo after being reopened for a second time.",
				   solo.waitForText("onRestart\\(\\) calls: 0"));


	}

}
