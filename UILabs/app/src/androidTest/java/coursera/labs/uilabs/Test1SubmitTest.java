package coursera.labs.uilabs;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by liwwli on 16-5-11.
 */
public class Test1SubmitTest extends ActivityInstrumentationTestCase2<ToDoManagerActivity> {

    private Solo solo;

    public Test1SubmitTest() {
        super(ToDoManagerActivity.class);
    }

    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    public void testRun() {
        int delay = 2000;

        assertTrue("SubmitTest failed:" +
                        "Section One:" +
                        "ToDoManagerActivity did not load correctly",
                solo.waitForActivity(ToDoManagerActivity.class, delay));

        // Click on action bar item to delete all items
        solo.clickOnActionBarItem(0x1);

        solo.sleep(delay);

        // Click on Add new ToDo Item
        solo.clickOnView(solo.getView(R.id.footerView));

        // Wait for activity: 'AddToDoActivity'
        assertTrue("Submit Test failed:" +
                        "Section One:" +
                        "AddToDoActivity did not correctly load.",
                solo.waitForActivity(AddToDoActivity.class));

        solo.hideSoftKeyboard();

        solo.clearEditText((android.widget.EditText) solo.
                getView(R.id.title));

        solo.enterText((android.widget.EditText) solo.
                getView(R.id.title), "t4");

        solo.hideSoftKeyboard();

        solo.clickOnView(solo.getView(R.id.statusDone));

        solo.clickOnView(solo.getView(R.id.lowPriority));

        solo.clickOnView(solo.getView(R.id.submitButton));


        // ================= Section Two ================
        // Wait for activity: 'ToDoManagerActivity'
        assertTrue("SubmitTest failed:" +
                        "Section Two:" +
                        "ToDoManagerActivity did not load correctly after pressing submit.",
                solo.waitForActivity(ToDoManagerActivity.class));

        assertTrue("SubmitTest failed:" +
                        "Section Two:" +
                        "Title was not correctly entered in the ToDoManager",
                solo.searchText("t4"));

        assertTrue("SubmitTest failed:" +
                        "Section Two:" +
                        "Priority was not correctly entered in the ToDoManager",
                solo.searchText("[lL][oO][wW]"));

        assertTrue("SubmitTest failed:" +
                        "Section Two:" +
                        "Did not correctly set completion status.",
                solo.isCheckBoxChecked(0));

    }
}
