package coursera.labs.uilabs;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by weili on 16-5-10.
 */
public class ToDoManagerActivity extends ListActivity {

    private static final int ADD_TODO_ITEM_REQUEST = 0;
    private static final String FILE_NAME = "ToDoManagerActivityData.txt";
    private static final String TAG = "Lab-UserInterface";

    // IDs for menu items
    private static final int MENU_DELETE = Menu.FIRST;
    private static final int MENU_DUMP = Menu.FIRST + 1;

    ToDoListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a new TodoListAdapter for this ListActivity's ListView
        mAdapter = new ToDoListAdapter(getApplicationContext());

        // Put divider between ToDoItems and FooterView
        getListView().setFooterDividersEnabled(true);

        // TODO - Inflate footerView for footer_view.xml file
        TextView footerView = (TextView) getLayoutInflater().inflate(R.layout.footer_view, null);

        // TODO - Add footerView to ListView
        getListView().addFooterView(footerView);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), AddToDoActivity.class);
                startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);
            }
        });

        getListView().setAdapter(mAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == ADD_TODO_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                ToDoItem toDoItem = new ToDoItem(intent);
                mAdapter.add(toDoItem);
            }
        }
    }
}
