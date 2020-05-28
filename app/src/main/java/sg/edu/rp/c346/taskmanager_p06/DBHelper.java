package sg.edu.rp.c346.taskmanager_p06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    // Start version with 1
    // increment by 1 whatever db schema changes.
    private static final int DATABASE_VERSION = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "Task.db";
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK_NAME = "task.name";
    private static final String COLUMN_TASK_DESC = "task.description";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK_NAME + " TEXT,"
                + COLUMN_TASK_DESC + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        // Create table(s) again
        onCreate(db);
    }

    public ArrayList<Task> getTasks() {

        // Create an ArrayList that holds String objects
        ArrayList<Task> tasks = new ArrayList<Task>();
        // Select all the tasks' description
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_TASK_NAME + ", "
                + COLUMN_TASK_DESC  + " FROM " + TABLE_TASK;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                // Add the task content to the ArrayList object
                //  getString(0) retrieves first column data
                //  getString(1) return second column data
                //  getInt(0) if data is an integer value
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                Task obj = new Task(id, name, description);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return tasks;
    }

    public long insertTask(Task task) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        // the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TASK_NAME, task.getName());
        // Store the column name as key and the date as value
        values.put(COLUMN_TASK_DESC, task.getDescription());
        // Insert the row into the TABLE_TASK
        long id = db.insert(TABLE_TASK, null, values);
        // Close the database connection
        db.close();
        return id;
    }
}
