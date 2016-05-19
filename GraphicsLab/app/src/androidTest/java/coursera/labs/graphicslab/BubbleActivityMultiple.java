package coursera.labs.graphicslab;

import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import com.robotium.solo.Solo;

/**
 * Created by weili on 16-5-19.
 */
public class BubbleActivityMultiple extends
        ActivityInstrumentationTestCase2<BubbleActivity> {
    private Solo solo;

    public BubbleActivityMultiple() {
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

        solo.waitForActivity(BubbleActivity.class, delay);

        solo.clickOnActionBarItem(R.id.menu_still_mode);

        solo.sleep(delay);

        solo.clickOnScreen(100, 100);

        solo.sleep(delay);

        assertEquals("Bubble han't appeared", 1, solo.getCurrentViews(BubbleActivity.BubbleView.class).size());

        solo.clickOnScreen(500, 500);

        solo.sleep(delay);

        assertEquals("There should be two bubbles o the-screen",
                2,
                solo.getCurrentViews(BubbleActivity.BubbleView.class).size());
    }
}
