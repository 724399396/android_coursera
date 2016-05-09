package course.labs.permissionlab;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by liwwli on 16-5-9.
 */
public class PermissionEnforcementTest
        extends ActivityInstrumentationTestCase2<ActivityLoaderActivity> {
    private Solo solo;

    public PermissionEnforcementTest() {
        super(ActivityLoaderActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


    public void testRun() {
        solo.waitForActivity(ActivityLoaderActivity.class, 2000);

        PackageManager pm = getActivity().getPackageManager();
        try {
            ActivityInfo activityInfo = pm.getActivityInfo(new ComponentName(
                    "course.labs.dangerousapp",
                    "course.labs.dangerousapp.DangerousActivity"), 0);
            assertTrue(
                    "PermissionEnforcementTest:" +
                            "Section One:" +
                            "course.labs.permissions.DANGEROUS_ACTIVITY_PERM not enforced by DangerousActivity",
                    null != activityInfo && null != activityInfo.permission
                    && activityInfo.permission.equals("course.labs.permissions.DANGEROUS_ACTIVITY_PERM"));
        } catch (PackageManager.NameNotFoundException e) {
            fail("PermissionEnforcementTest:" + "" +
                    "Section One:" +
            "DangerousActivity not found");
        }
    }
}
