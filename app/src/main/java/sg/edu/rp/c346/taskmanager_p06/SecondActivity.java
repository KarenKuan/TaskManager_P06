package sg.edu.rp.c346.taskmanager_p06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnAddTask = findViewById(R.id.btnAddTask);
    }
}
