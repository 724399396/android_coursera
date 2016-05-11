package coursera.labs.uilabs;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by liwwli on 16-5-11.
 */
public class Test3CancelTest extends ActivityInstrumentationTestCase2<ToDoManagerActivity> {

    private Solo solo;

    public Test3CancelTest() {
        super(ToDoManagerActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        int delay = 2000;

        assertTrue("CancelTest failed:" +
                        "Section One:" +
                        "ToDoManagerActivity did not load correctly.",
                solo.waitForActivity(ToDoManagerActivity.class, delay));

        solo.clickOnActionBarItem(0x1);

        solo.sleep(delay);

        solo.clickOnView(solo.getView(R.id.footerView));

        assertTrue("CancelTest failed:" +
                        "Section One:" +
                        "AddToDoActivity did not load correctly.",
                solo.waitForActivity(AddToDoActivity.class));

        solo.hideSoftKeyboard();

        // Enter the text: 't3'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.title));

        solo.enterText((android.widget.EditText) solo
                .getView(R.id.title), "t3");

        // Hide the soft keyboard
        solo.hideSoftKeyboard();

        solo.clickOnView(solo.getView(R.id.statusDone));

        solo.clickOnView(solo.getView(R.id.highPriority));

        solo.clickOnView(solo.getView(R.id.cancelButton));

        assertTrue("Cancel test failed:" + "Section One:"
                        + "AddToDoActivity did not correctly load.",
                solo.waitForActivity(
                        AddToDoActivity.class, delay));

        solo.clickOnView(solo.getView(R.id.footerView));

        // Wait for activity: 'AddToDoActivity'
        assertTrue("CancelTest failed:" +
                        "Section One:" +
                        "AddToDoActivity did not load correctly.",
                solo.waitForActivity(AddToDoActivity.class));

        // Hide the soft keyboard
        solo.hideSoftKeyboard();

        // Enter the text: 't4'
        solo.clearEditText((android.widget.EditText) solo
                .getView(R.id.title));

        solo.enterText((android.widget.EditText) solo
                .getView(R.id.title), "t4");

        // Hide the soft keyboard
        solo.hideSoftKeyboard();

        // Click on Done:
        solo.clickOnView(solo.getView(R.id.statusDone));

        // Click on Low
        solo.clickOnView(solo.getView(R.id.lowPriority));

        // Click on Submit
        solo.clickOnView(solo
                .getView(R.id.submitButton));

        // ================ Section Two ===================

        // Wait for activity: 'ToDoManagerActivity'
        assertTrue("CancelTest failed:" +
                        "Section Two:" +
                        "ToDoManagerActivity did not load correctly.",
                solo.waitForActivity(ToDoManagerActivity.class));

        assertFalse("CancelTest failed:" +
                        "Section Two:" +
                        "Did not correctly cancel the creation of a ToDo Task.",
                solo.searchText("t3"));

        assertTrue("CancelTest failed:" +
                        "Section Two:" +
                        "Did not correctly set title of ToDo Task following cancel.",
                solo.searchText("t4"));

        assertTrue("CancelTest failed:" +
                        "Section Two:" +
                        "Did not correctly set priority of ToDo Task following cancel.",
                solo.searchText("[lL][oO][wW]"));

    }
}
