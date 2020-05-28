package sg.edu.rp.c346.taskmanager_p06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvTasks;
    ArrayAdapter aa;
    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = (ListView) this.findViewById(R.id.lvTasks);

        // Create a few tasks objects in Tasks array
        tasks = new ArrayList<Task>();
        tasks.add(new Task(1, "Buy milk", "Low fat"));
        tasks.add(new Task(2, "Post Letters", "Get stamps from car"));

        /*aa = new TaskAdapter(this, R.layout.row, tasks);
        lvTasks.setAdapter(aa);*/

    }
}
