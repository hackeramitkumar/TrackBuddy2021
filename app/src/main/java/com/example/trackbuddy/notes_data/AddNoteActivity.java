package com.example.trackbuddy.notes_data;


        import android.content.ContentValues;
        import android.database.sqlite.SQLiteDatabase;
        import android.net.Uri;
        import android.os.Bundle;

        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.trackbuddy.R;
        import com.google.android.material.textfield.TextInputEditText;


/**
 * Allows user to create a new pet or edit an existing one.
 */
public class AddNoteActivity extends AppCompatActivity {

    /** EditText field to enter the pet's name */
    private EditText mTitleEditText;

    /** EditText field to enter the pet's breed */
    private EditText mDescriptionEditText;

    /** EditText field to enter the pet's weight */
    private TextInputEditText mContentEditText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Find all relevant views that we will need to read user input from
        mTitleEditText = (EditText) findViewById(R.id.edit_note_title);
        mDescriptionEditText = (EditText) findViewById(R.id.edit_note_description);
        mContentEditText = (TextInputEditText) findViewById(R.id.edit_note_content);

    }





    /**
     * Get user input from editor and save new pet into database.
     */
    private void insertPet() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String titleString = mTitleEditText.getText().toString().trim();
        String descriptionString = mDescriptionEditText.getText().toString().trim();
        String contentString = mContentEditText.getText().toString().trim();

        // Create database helper
        NoteDbHelper mDbHelper = new NoteDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COLUMN_NOTE_CONTENT, titleString);
        values.put(NoteContract.NoteEntry.COLUMN_NOTE_DESCRIPTION, descriptionString);
        values.put(NoteContract.NoteEntry.COLUMN_NOTE_CONTENT, contentString);
/*
        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);
        */

        Uri newUri = getContentResolver().insert(NoteContract.NoteEntry.CONTENT_URI,values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newUri == null) {
            // If the row ID is -1, then there was an error with insertion.

            // Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();

            // If the new content URI is null, then there was an error with insertion.
            Toast.makeText(this, "not inserted",
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            // Toast.makeText(this, "Pet saved with row id: " + newUri, Toast.LENGTH_SHORT).show();


            // Otherwise, the insertion was successful and we can display a toast.
            Toast.makeText(this, "inserted",
                    Toast.LENGTH_SHORT).show();

        }
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_editor.xml file.
//        // This adds menu items to the app bar.
//        getMenuInflater().inflate(R.menu.menu_editor, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Save" menu option
//            case R.id.action_save:
//                // Save pet to database
//                insertPet();
//                // Exit activity
//                finish();
//                return true;
//            // Respond to a click on the "Delete" menu option
//            case R.id.action_delete:
//                // Do nothing for now
//                return true;
//            // Respond to a click on the "Up" arrow button in the app bar
//            case android.R.id.home:
//                // Navigate back to parent activity (CatalogActivity)
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
