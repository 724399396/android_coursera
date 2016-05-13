package course.labs.notifications;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity implements
        SelectionListener, DownloadFinishedListener {

    private static final String TAG_NAME = "name";
    private static final String TAG_USER = "user";
    private static final String TAG_TEXT = "text";
    private static final String TAG_FRIENDS_FRAGMENT = "friends_fragment";
    private static final String TAG_FEED_FRAGMENT = "feed_fragment";
    private static final String TAG_DOWNLOADER_FRAGMENT = "downloader_fragment";
    private static final String TAG_IS_DATA_AVAILABLE = "is_data_available";
    private static final String TAG_PROCESSED_FEEDS = "processed_feeds";
    static final String TAG_TWEET_DATA = "data";
    static final String TAG_FRIENDS_RES_IDS = "friends";

    public static final String TWEET_FILENAME = "tweets.txt";
    public static final String[] FRIENDS_NAMES = { "taylorswift13",
            "msrebeccablack", "ladygaga" };
    public static final int IS_AVIVE = Activity.RESULT_FIRST_USER;
    public static final String DATA_REFRESHED_ACTION = "course.lab.notificationslabnew.DATA_REFRESHED";
    private static final String TAG = "Lab-Notifications";

    // Raw feed file IDS used to reference stored tweet data
    public static final ArrayList<Integer> sRawTextFeedIds = new ArrayList<>(
            Arrays.asList(R.raw.tswift, R.raw.rblack, R.raw.lgaga));

    private FragmentManager mFragmentManager;
    private FriendsFragment mFriendsFragment;
    private FeedFragment mFeedFragment;
    private DownlaoderTaskFragment mDownloaderFragment;
    private boolean mIsInteractionEnabled;
    private String[] mFormattedFeeds = new String[sRawTextFeedIds.size()];
    private boolean mIsFresh;
    private BroadcastReceiver mRefreshReceiver;
    private static final long TWO_MIN = 2 * 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getFragmentManager();

        // Reset instance state on reconfiguration
        if (null != savedInstanceState) {
            restoreState(savedInstanceState);
        } else {
            setUpFragments();
        }
    }

    // One time setup of UI and retained (headless) Fragment
    private void setUpFragments() {
        installFriendsFragment();

        // The feed is fresh if it was downloaded less than 2 minutes ago
        mIsFresh = (System.currentTimeMillis()) - getFilesStreamPath(
                TWEET_FILENAME).lastMofifies()) < TWO_MIN;

        if (!mIsFresh) {
            installDownloaderTaskFragment();
        }
    }
}
