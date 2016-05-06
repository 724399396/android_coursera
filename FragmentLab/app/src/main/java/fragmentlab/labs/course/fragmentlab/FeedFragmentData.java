package fragmentlab.labs.course.fragmentlab;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by weili on 16-5-6.
 */
public class FeedFragmentData {
    private static final String TAG = "FeedFragmentData";
    private static final int[] IDS = { R.raw.ladygaga, R.raw.rebeccablack, R.raw.taylorswift };

    private SparseArray<String> mFeeds = new SparseArray<>();
    private Context mContext;

    public FeedFragmentData(Context context) {
        mContext = context;
        loadFeeds();
    }

    private void loadFeeds() {
        for (int id : IDS) {
            InputStream inputStream = mContext.getResources().openRawResource(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer("");

            // read raw data from resource file
            try {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            } catch (IOException e) {
                Log.i(TAG, "IOException");
            }

            // Convert raw data into a String
            JSONArray feed = null;
            try {
                feed = new JSONArray(buffer.toString());
            } catch (JSONException e) {
                Log.i(TAG, "JSONException");
            }

            mFeeds.put(id, procFeed(feed));
        }
    }

    // Convert JSON formatted data to a String
    private String procFeed(JSONArray feed) {
        String name = "";
        String tweet = "";

        StringBuffer textFeed = new StringBuffer("");

        for (int j = 0; j < feed.length(); j++) {
            try {
                tweet = feed.getJSONObject(j).getString("text");
                JSONObject user = (JSONObject) feed.getJSONObject(j).get("user");
                name = user.getString("name");
            } catch (JSONException e) {
                Log.i(TAG, "JSONException while processing feed");
            }
            textFeed.append(name + " - " + tweet + "\n\n");
        }
        return textFeed.toString();
    }

    String getFeed(int position) {
        return mFeeds.get(IDS[position]);
    }
}
