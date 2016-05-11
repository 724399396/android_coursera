package coursera.labs.asynctasklab;

/**
 * Created by weili on 16-5-11.
 */
interface SelectionListener {
    void onItemSelected(int position);
    boolean canAllowUserClicks();
}
