package coursera.labs.uilabs;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by liwwli on 16-5-11.
 */
public class Test4ResetTest extends ActivityInstrumentationTestCase2<ToDoManagerActivity> {

    private Solo solo;

    public Test4ResetTest() {
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

        assertTrue("ResetTest failed:" + "Section One:"
                        + "ToDoManagerActivity did not correctly load.",
                solo.waitForActivity(
                        ToDoManagerActivity.class, delay));

        solo.clickOnActionBarItem(0x1);

        solo.sleep(delay);

        solo.clickOnView(solo.getView(R.id.footerView));

        assertTrue("ResetTest failed:" + "Section One:"
                        + "AddToDoActivity did not correctly load.",
                solo.waitForActivity(
                        AddToDoActivity.class, delay));

        solo.hideSoftKeyboard();

        solo.clickOnView(solo.getView(R.id.statusDone));

        solo.clickOnView(solo.getView(R.id.highPriority));

        solo.clickOnView(solo.getView(R.id.resetButton));

        solo.sleep(delay);

        solo.clickOnView(solo.getView(R.id.submitButton));


        assertTrue("ResetTest failed:" + "Section Two:"
                        + "AddToDoActivity did not correctly load.",
                solo.waitForActivity(
                        AddToDoActivity.class, delay));

        assertFalse("ResetTest failed:" + "Section Two:"
                        + "Title of ToDo Task was not correctly reset.",
                solo.searchText("t2"));

        // Makes sure that the check box is not checked
        assertFalse("ResetTest failed:" + "SectionTwo:"
                        + "Done status of ToDo Task was not correctly reset",
                solo.isCheckBoxChecked(0));

        // Makes sure that the priority was reset to Medium
        assertTrue("ResetTest failed:" + "Section Two:"
                        + "Priority of ToDo Task was not correctly reset",
                solo.searchText("[mM][eE][dD]"));

        // Clicks on the Done box
        solo.clickOnCheckBox(0);

        // Makes sure that was able to correctly change completion status from
        // ToDoManagerActivity
        assertTrue(
                "ResetTest failed:"
                        + "Section Two:"
                        + "Was not able to modify Done status of ToDo Task from ToDoManagerActivity",
                solo.isCheckBoxChecked(0));


    }
}
