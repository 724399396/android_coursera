package coursera.labs.uilabs;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by liwwli on 16-5-11.
 */
public class Test2DateTimeTest extends ActivityInstrumentationTestCase2<ToDoManagerActivity> {

    private Solo solo;

    public Test2DateTimeTest() {
        super(ToDoManagerActivity.class);
    }

    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testRun() {
        int delay = 2000;

        assertTrue("DateTimeTest failed:" +
                        "Section One:" +
                        "ToDoManagerActivity did not correctly load.",
                solo.waitForActivity(ToDoManagerActivity.class, 2000));

        solo.clickOnActionBarItem(0x1);

        solo.sleep(delay);

        solo.clickOnView(solo.getView(R.id.footerView));

        // Wait for activity: 'course.labs.todomanager.AddToDoActivity'
        assertTrue("DateTimeTest failed:" +
                        "Section One:" +
                        "AddToDoActivity did not correctly load.",
                solo.waitForActivity(AddToDoActivity.class));

        solo.hideSoftKeyboard();

        solo.clearEditText((android.widget.EditText) solo.
                getView(R.id.title));

        solo.enterText((android.widget.EditText) solo.
                getView(R.id.title), "t1");

        solo.hideSoftKeyboard();

        solo.clickOnView(solo.getView(R.id.statusDone));

        solo.clickOnView(solo.getView(R.id.lowPriority));

        solo.clickOnView(solo.getView(R.id.date_picker_button));

        solo.waitForDialogToOpen(10000);

        solo.clearEditText((android.widget.EditText) solo.
                getView("numberpicker_input"));

        solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "Feb");

        solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input", 1));

        solo.enterText((android.widget.EditText) solo.getView("numberpicker_input", 1), "28");

        solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input", 2));

        solo.enterText((android.widget.EditText) solo.getView("numberpicker_input", 2), "2014");

        solo.setDatePicker(0, 2014, 1, 28);

        solo.clickOnView(solo.getView(android.R.id.button1));

        solo.sleep(delay);

        solo.clickOnView(solo.getView(R.id.time_picker_button));

        solo.waitForDialogToOpen(10000);

        solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input"));

        solo.enterText((android.widget.EditText) solo.getView("numberpicker_input"), "9");

        solo.clearEditText((android.widget.EditText) solo.getView("numberpicker_input", 1));

        solo.enterText((android.widget.EditText) solo.getView("numberpicker_input", 1), "19");

        solo.setTimePicker(0, 9, 19);

        solo.clickOnView(solo.getView(android.R.id.button1));

        solo.sleep(delay);

        solo.clickOnView(solo.getView(R.id.submitButton));

        assertTrue("DateTimeTest failed:" +
                        "Section One:" +
                        "ToDoManagerActivity did not load correctly",
                solo.waitForActivity(
                        ToDoManagerActivity.class, delay));

        assertTrue("DateTimeTest failed:" +
                "Section Two:" +
                "Did not change status correctly", solo.isCheckBoxChecked(0));

        // Checks to make sure the priority was correctly set
        assertTrue("DateTimeTest failed:" +
                "Section Two:" +
                "Did not correctly set priority", solo.searchText("[lL][oO][wW]"));

        // Checks to make sure the Date was correctly set
        assertTrue("DateTimeTest failed:" +
                "Section Two:" +
                "Did not correctly set the date", solo.searchText("2014-02-28"));

        // Checks to make sure the Time was correctly set
        assertTrue("DateTimeTest failed:" +
                "Section Two:" +
                "Did not correctly set the time", solo.searchText("09:19:00"));

    }
}
