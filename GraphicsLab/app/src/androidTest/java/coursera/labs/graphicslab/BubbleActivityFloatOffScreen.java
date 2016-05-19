package coursera.labs.graphicslab;

import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import com.robotium.solo.Solo;

/**
 * Created by weili on 16-5-19.
 */
public class BubbleActivityFloatOffScreen extends
        ActivityInstrumentationTestCase2<BubbleActivity> {

    private Solo solo;

    public BubbleActivityFloatOffScreen() {
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
        int shortDelay = 250, delay = 2000;

        // Wait for activity: BubbleActivity
        solo.waitForActivity(BubbleActivity.class, delay);

        solo.clickOnActionBarItem(R.id.menu_single_speed);

        solo.sleep(delay);

        solo.clickOnScreen(250.0f, 250.0f);

        boolean bubbleAppeared = solo.getCurrentViews(
                BubbleActivity.BubbleView.class).size() > 0;

        for (int i = 0; i < 8 && !bubbleAppeared; i++) {
            solo.sleep(shortDelay);
            bubbleAppeared = solo.getCurrentViews(BubbleActivity.BubbleView.class).size() > 0;
        }

        assertTrue("Bubble hasn't appeared", bubbleAppeared);

        solo.sleep(delay);

        // Assert that the bubble has left the screen
        assertEquals(
                "Bubble hasn't left the screen",
                0,
                solo.getCurrentViews(
                        BubbleActivity.BubbleView.class)
                        .size());
    }
}
