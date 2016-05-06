package course.labs.intentslab;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by liwwli on 16-5-6.
 */
public class ExplicitlyLoadedActivity extends Activity {

    static private final String TAG = "Lab-Intents";

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.explicitly_loaded_activity);

        // Get a reference to the EditText field
        mEditText = (EditText) findViewById(R.id.editText);

        Button enterButton = (Button) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterClicked();
            }
        });
    }

    private void enterClicked() {
        Log.i(TAG, "Entered enterClicked()");
        // TODO - Save user provided input from the EditText field
        String userInput = mEditText.getText().toString();
        // TODO - Create a new intent and save the input from the EditText field as an extra
        Intent intent = new Intent(Intent.EXTRA_TEXT, Uri.parse(userInput));
        // TODO - Set Activity's result with result code RESULT_OK
        setResult(RESULT_OK, intent);
        // TODO - Finish the Activity
        finish();
    }
}
