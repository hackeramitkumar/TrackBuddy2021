package com.example.trackbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trackbuddy.data.NotesContract.NoteEntry;
import com.example.trackbuddy.data.NotesDBHelper;

public class notesAddEventActivity extends AppCompatActivity {

    private EditText heading_field;

    private EditText description_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_notes);

        heading_field = (EditText) findViewById(R.id.textbox_notes_heading);
        description_field = (EditText) findViewById(R.id.textbox_notes_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            case R.id.action_save:
                insertNotes();
                finish();
                return true;
            case R.id.action_delete:
                // ENTER CODE LATER
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void insertNotes() {
        // Read from input fields
        String headingString = heading_field.getText().toString().trim();
        String descriptionString = description_field.getText().toString().trim();

        // Create database helper
        NotesDBHelper mDbHelper = new NotesDBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(NoteEntry.COLUMN_HEADING, headingString);
        values.put(NoteEntry.COLUMN_DESCRIPTION, descriptionString);


        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(NoteEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        }
        else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

}
