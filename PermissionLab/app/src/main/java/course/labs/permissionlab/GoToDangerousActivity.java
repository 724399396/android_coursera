package course.labs.permissionlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by liwwli on 16-5-9.
 */
public class GoToDangerousActivity extends Activity {

    private static final String TAG = "Lab-Permissions";

    private static final String DANGEROUS_ACTIVITY_ACTION = "course.labs.permissions.DANGEROUS_ACTIVITY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_to_dangerous_activity);

        Button startDangerousActivityButton = (Button) findViewById(R.id.start_dangerous_activity_button);
        startDangerousActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDangerousActivity();
            }
        });
    }

    private void startDangerousActivity() {
        Log.i(TAG, "Entered startDangerousActivity()");

        startActivity(new Intent(DANGEROUS_ACTIVITY_ACTION));
    }
}
