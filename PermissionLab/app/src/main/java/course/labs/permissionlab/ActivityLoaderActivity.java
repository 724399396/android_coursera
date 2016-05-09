package course.labs.permissionlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityLoaderActivity extends Activity {

    private static final String TAG = "Lab-Permissions";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_activity);

        Button startBookmarkButton = (Button) findViewById(R.id.start_bookmarks_button);
        startBookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBookMarksActivity();
            }
        });

    }

    private void startBookMarksActivity() {
        Log.i(TAG, "Entered startBookMarksActivity");

        Intent intent = new Intent(this, BookMarksActivity.class);
        startActivity(intent);
    }
}
