package fragmentlab.labs.course.fragmentlab;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by weili on 16-5-6.
 */
public class PhoneTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public PhoneTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        int delay = 2000;
        assertTrue("MainActivity not found", solo.waitForActivity(MainActivity.class, delay));

        assertTrue("text1 not found", solo.waitForView(android.R.id.text1));

        solo.clickOnView(solo.getView(android.R.id.text1));

        assertTrue("ladygaga feed_view not found", solo.waitForView(solo.getView(R.id.feed_view)));

        assertTrue("'the audience cheering!' is not shown!",
                solo.searchText("the audience cheering!"));

        solo.goBack();

        assertTrue("text1 not found", solo.waitForView(android.R.id.text1));

        solo.clickOnView(solo.getView(android.R.id.text1, 1));

        assertTrue("feed_view! is not shown!", solo.waitForView(solo.getView(R.id.feed_view)));

        assertTrue("'save me from school' is not shown!", solo.searchText("save me from school"));

        solo.goBack();

        assertTrue("text1 not found", solo.waitForView(android.R.id.text1));

        solo.clickOnView(solo.getView(android.R.id.text1, 2));

        assertTrue("feed_view not shown", solo.waitForView(solo
                .getView(R.id.feed_view)));

        // Assert that: 'I love you guys so much' is shown
        assertTrue("'I love you guys so much' is not shown!",
                solo.searchText("I love you guys so much"));
    }
}
