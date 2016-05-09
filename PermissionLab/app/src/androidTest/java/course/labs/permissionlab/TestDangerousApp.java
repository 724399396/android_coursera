package course.labs.permissionlab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by liwwli on 16-5-9.
 */
public class TestDangerousApp extends ActivityInstrumentationTestCase2<ActivityLoaderActivity> {
    private Solo solo;

    public TestDangerousApp() {
        super(ActivityLoaderActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        assertTrue("TestDangerousApp:" +
        "Section One:" +
        "ActivityLoaderActivity did not load correctly.",
                solo.waitForActivity(course.labs.permissionlab.ActivityLoaderActivity.class));

        solo.clickOnView(solo.getView(R.id.start_bookmarks_button));

        assertTrue("TestDangerousApp:" +
        "Section One:" +
        "BookmarksActivity did not load correctly",
                solo.waitForActivity(BookMarksActivity.class));

        solo.clickOnView(solo.getView(R.id.go_to_dangerous_activity_button));

        assertTrue("TestDangerousApp:" +
                        "Section Two:" +
                        "GoToDangerousActivity did not load correctly",
                solo.waitForActivity(GoToDangerousActivity.class));

        assertTrue("TestDangerousApp:" +
                        "Section Two:" +
                        "Dangerous Level activity button is not show.",
                solo.waitForText(java.util.regex.Pattern.quote("This button will load a Dangerous Level activity")));

        solo.clickOnView(solo.getView(R.id.start_dangerous_activity_button));
    }
}
