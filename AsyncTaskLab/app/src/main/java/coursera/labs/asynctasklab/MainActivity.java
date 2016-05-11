package coursera.labs.asynctasklab;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity implements SelectionListener, DownloadFinishedListener {

    private static final String TAG_NAME = "name";
    private static final String TAG_USER = "user";
    private static final String TAG_TEXT = "text";
    private static final String TAG_FRIENDS_FRAGMENT = "friends_fragment";
    private static final String TAG_FEED_FRAGMENT = "feed_fragment";
    private static final String TAG_DOWNLOADER_FRAGMENT = "downloader_fragment";
    private static final String TAG_IS_DATA_AVAILABLE = "is_data_available";
    private static final String TAG_PROCESSED_FEEDS = "processed_feeds";
    static final String TAG_TWEET_DATA = "data";
    static final String TAG_FRIEND_RES_IDS = "friends";
    public  final static String[] FRIENDS_NAMES = { "taylorswift13",
            "msrebeccablack", "ladygaga"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
