package coursera.labs.graphicslab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by weili on 16-5-17.
 */
public class BubbleActivity extends Activity {
    // These variables are for testing purposes, do not modify
    private final static int RANDOM = 0;
    private final static int SINGLE = 1;
    private final static int STILL = 2;
    private static int speedMode = RANDOM;

    private static final String TAG = "Lab-Graphics";

    // The Main view
    private RelativeLayout mFrame;

    // Bubble image's bitmap
    private Bitmap mBitmap;

    // Display dimensions
    private int mDisplayWidth, mDisplayHeight;

    // Sound variables

    // AudioManager
    private AudioManager mAudioManager;
    // SoundPool
    private SoundPool mSoundPool;
    // ID for the bubble popping sound
    private int mSoundID;
    // Audio volumne
    private float mStreamVolume;

    // Gesture Detector
    private GestureDetector mGestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // Set up user interface
        mFrame = (RelativeLayout) findViewById(R.id.frame);

        // Load basic bubble Bitmap
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b64);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Manage bubble popping sound
        // Use AudioManger.STREAM_MUSIC as tream type

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        mStreamVolume = (float) mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        // TODO - make a new SoundPool, allowing up to 10 stream
        mSoundPool = null;

        // TODO - set a SoundPool OnLoadCompletedListener that calls setupGestureDetector()


        // TODO - load the sound from res/raw/bubble_pop.wav
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Get the size of the display so this View knows where borders are
            mDisplayWidth = mFrame.getWidth();
            mDisplayHeight = mFrame.getHeight();
        }
    }

    // Set up GestureDetector
    private void setupGestureDetector() {
        mGestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    // If a fling gesture starts on a BubbleView then change the
                    // BubbleView's velocity
                    @Override
                    public boolean onFling(MotionEvent event1, MotionEvent event2,
                                           float velocityX, float velocityY) {
                        // TODO - Implement onFling actions.
                        // You cant get all Views in mFrame using the
                        // ViewGroup.getChildCount() method

                        return false;
                    }

                    // If a single tap intersects a BubbleView, then pop the BubbleView
                    // Otherwise, create a new BubbleView at the tap's location and add
                    // it to mFrame. You can get all views from mFrame with ViewGroup.getChildAt()
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent event) {
                        // TODO - Implement onSingleTapConfirmed actions.
                        // You can get all Views in mFrame using the
                        // ViewGroup.getChildCount() method


                        return false;
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO - Delegate the touch to the gestureDetector

        return false;
    }

    @Override
    protected void onPause() {
        // TODO - Release all SoundPool resources

        super.onPause();
    }

    // BubbleView is a View that displays a bubble.
    // This class handles animating, drawing, and popping amongst other actions.
    // A new BubbleView is created for each bubble on the display.
    public class BubbleView extends View {

        private static final int BITMAP_SIZE = 64;
        private static final int REFRESH_RATE = 40;
        private final Paint mPainter = new Paint();
        private ScheduledFuture<?> mMoveFuture;
        private int mScaledBitmapWidth;
        private Bitmap mSclaedBitmap;

        // location, speed and direction of the bubble
        private float mXPos, mYPos, mDx, mDy, mRadius, mRadiusSquared;
        private long mRotate, mDRotate;

        BubbleView(Context context, float x, float y) {
            super(context);

            // Created a new random number generator to
            // randomize size, rotation, speed and direction
            Random r = new Random();

            // Creates the bubble bitmap for this BubbleView
            createScaledBitmap(r);

            // Radius of the Bitmap
            mRadius = mScaledBitmapWidth / 2;
            mRadiusSquared = mRadius * mRadius;

            // Adjust position to center the bubble under user's finger
            mYPos = x - mRadius;
            mYPos = y - mRadius;

            // Set the BubbleView's speed and direction
            setSpeedAndDirection(r);

            // Set the BubbleView's rotation
            setRotation(r);

            mPainter.setAntiAlias(true);
        }

        private void setRotation(Random r) {
            if (speedMode == RANDOM) {
                // TODO - set rotation in range [1..3]
                mDRotate = 0;

            } else {
                mDRotate = 0;
            }
        }

        private void setSpeedAndDirection(Random r) {
            // Used by test cases
            switch (speedMode) {
                case SINGLE:
                    mDx = 20;
                    mDy = 20;
                    break;
                case STILL:
                    // No speed
                    mDx = 0;
                    mDy = 0;
                    break;
                default:
                    // TODO - Set movement direction and speed
                    // Limit movement speed in the x and y
                    // direction to [-3..3] pixels per movement.
            }
        }

        private void createScaledBitmap(Random r) {
            if (speedMode != RANDOM) {
                mScaledBitmapWidth = BITMAP_SIZE * 3;
            } else {
                // TODO - set scaled bitmap size in range [1..3] * BITMAP_SIZE
                mScaledBitmapWidth = 0;
            }

            // TODO - create the scaled bitmap using size set above
            mSclaedBitmap = null;
        }

        // Start moving the BubbleView & updating display
        private void start() {
            // Creates a WorkerThread
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

            // Execute the run() in Worker Thread every REFRESH_RATE
            // milliseconds
            // Save reference to this job in mMoveFuture
            mMoveFuture = executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    // TODO - implement movement logic.
                    // Each time this method is run the BubbleView should
                    // move one step. If the BubbleView exits the display,
                    // stop the BubbleView's Worker Thread.
                    // Otherwise, request that the BubbleView be radrawn.
                }
            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
        }

        // Returns true if the BubbleView intersects position (x,y)
        private synchronized boolean intersects(float x, float y) {
            // TODO - Return true if the BubbleView intersects position(x,y)

            return false;
        }
    }
}