package sg.edu.rp.c346.taskmanager_p06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasks;
    private Context context;
    private TextView tvName, tvDescription;
    /*private EditText etName, etDescription;
    private Button btnAddTask, btnCancel;*/

    public TaskAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        tasks = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvName = (TextView) rowView.findViewById(R.id.textViewName);
        tvDescription = (TextView) rowView.findViewById(R.id.textViewDescription);

        // Get the EditText object
        /*etName = (EditText) rowView.findViewById(R.id.editTextName);
        etDescription = (EditText) rowView.findViewById(R.id.editTextDescription); */

        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Task currentTasks = tasks.get(position);

        // Set the TextView to show the food
        tvName.setText(currentTasks.getName());
        tvDescription.setText(currentTasks.getDescription());

        // Return the nicely done up View to the ListView
        return rowView;
    }
}
