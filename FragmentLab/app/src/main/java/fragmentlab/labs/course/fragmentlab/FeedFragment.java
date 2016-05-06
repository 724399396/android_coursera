package fragmentlab.labs.course.fragmentlab;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by weili on 16-5-6.
 */
public class FeedFragment extends Fragment {
    private static final String TAG = "Lab-Fragments";

    private TextView mTextView;
    private static FeedFragmentData feedFragmentData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (null == feedFragmentData) {
            feedFragmentData = new FeedFragmentData(getActivity());
        }
    }

    void updateFeedDisplay(int position) {
        Log.i(TAG, "Entered updateFeedDisplay()");

        mTextView = (TextView) getView().findViewById(R.id.feed_view);
        mTextView.setText(feedFragmentData.getFeed(position));
    }
}
