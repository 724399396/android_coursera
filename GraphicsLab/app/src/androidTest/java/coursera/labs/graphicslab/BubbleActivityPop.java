package coursera.labs.graphicslab;

import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import com.robotium.solo.Solo;

/**
 * Created by weili on 16-5-19.
 */
public class BubbleActivityPop extends
        ActivityInstrumentationTestCase2<BubbleActivity> {
    private Solo solo;

    public BubbleActivityPop() {
        super(BubbleActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            }
        });
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        int delay = 2000;

        // Wait for activity: 'course.labs.TouchLab.BubbleActivity'
        solo.waitForActivity(BubbleActivity.class,
                delay);

        // Set Still Mode
        solo.clickOnActionBarItem(R.id.menu_still_mode);

        solo.sleep(delay);

        // Click to create a bubble
        solo.clickOnScreen(250, 250);

        solo.sleep(delay);

        // Assert that a bubble was displayed
        assertEquals(
                "Bubble hasn't appeared",
                1,
                solo.getCurrentViews(
                        BubbleActivity.BubbleView.class)
                        .size());

        // Click to remove the same bubble
        solo.clickOnScreen(250, 250);

        solo.sleep(delay);

        // Assert that there are no more bubbles
        assertEquals(
                "The bubble was not popped",
                0,
                solo.getCurrentViews(
                        BubbleActivity.BubbleView.class)
                        .size());
    }
}
