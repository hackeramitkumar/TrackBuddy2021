package com.example.trackbuddy.notes_data;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * {@link ContentProvider} for Pets app.
 */
public class NoteProvider extends ContentProvider {

    private NoteDbHelper mDbHelper;


    /** Tag for the log messages */
    public static final String LOG_TAG = NoteProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the pets table */
    private static final int NOTE = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int NOTE_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        sUriMatcher.addURI(NoteContract.CONTENT_AUTHORITY,NoteContract.PATH_NOTES,NOTE);
        sUriMatcher.addURI(NoteContract.CONTENT_AUTHORITY,NoteContract.PATH_NOTES+"/#",NOTE);

    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        // TODO: Create and initialize a PetDbHelper object to gain access to the pets database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.

        mDbHelper = new NoteDbHelper(getContext());
        // Create and/or open a database to read from it

        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Get readable database
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        // This cursor will hold the result of the query
        Cursor cursor;

        // Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTE:
                // For the PETS code, query the pets table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the pets table.
                // TODO: Perform database query on pets table
                cursor = database.query(NoteContract.NoteEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);


                break;
            case NOTE_ID:
                // For the PET_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.android.pets/pets/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.
                selection = NoteContract.NoteEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the pets table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = database.query(NoteContract.NoteEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {



        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTE:
                return insertNote(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }



    }



    private Uri insertNote(Uri uri, ContentValues values) {
        // Check that the name is not null
        String title = values.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);
        if (title == null) {
            throw new IllegalArgumentException("note requires a name");
        }


        String description = values.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_DESCRIPTION);
        if (description == null) {
            throw new IllegalArgumentException("note requires description");
        }

//        // Check that the gender is valid
//        Integer gender = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
//        if (gender == null || !PetContract.PetEntry.isValidGender(gender)) {
//            throw new IllegalArgumentException("Pet requires valid gender");
//        }
//
//        // If the weight is provided, check that it's greater than or equal to 0 kg
//        Integer weight = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_WEIGHT);
//        if (weight != null && weight < 0) {
//            throw new IllegalArgumentException("Pet requires valid weight");
//        }

        // No need to check the breed, any value is valid (including null).

        // Get writeable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);





    }



    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTE:
                return updateNote(uri, contentValues, selection, selectionArgs);
            case NOTE_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = NoteContract.NoteEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateNote(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }

    }

    /**
     * Update pets in the database with the given content values. Apply the changes to the rows
     * specified in the selection and selection arguments (which could be 0 or 1 or more pets).
     * Return the number of rows that were successfully updated.
     */
    private int updateNote(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // If the {@link PetEntry#COLUMN_PET_NAME} key is present,
        // check that the name value is not null.
       if (values.containsKey(NoteContract.NoteEntry.COLUMN_NOTE_TITLE)) {
            String title = values.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);
            if (title == null) {
                throw new IllegalArgumentException("Note requires a title");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_GENDER} key is present,
        // check that the gender value is valid.
        if (values.containsKey(NoteContract.NoteEntry.COLUMN_NOTE_DESCRIPTION)) {
            String description = values.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_DESCRIPTION);
            if (description == null ) {
                throw new IllegalArgumentException("Note requires valid description");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (values.containsKey(NoteContract.NoteEntry.COLUMN_NOTE_CONTENT)) {
            // Check that the weight is greater than or equal to 0 kg
            String content = values.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_CONTENT);
            if (content == null) {
                throw new IllegalArgumentException("Pet requires valid weight");
            }
        }

        // No need to check the breed, any value is valid (including null).

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Returns the number of database rows affected by the update statement
        return database.update(NoteContract.NoteEntry.TABLE_NAME, values, selection, selectionArgs);



    }
    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
       SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTE:
                // Delete all rows that match the selection and selection args
                return database.delete(NoteContract.NoteEntry.TABLE_NAME, selection, selectionArgs);
            case NOTE_ID:
                // Delete a single row given by the ID in the URI
                selection = NoteContract.NoteEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return database.delete(NoteContract.NoteEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

    }
    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
       final int match = sUriMatcher.match(uri);
        switch (match) {
            case NOTE:
                return NoteContract.NoteEntry.CONTENT_LIST_TYPE;
            case NOTE_ID:
                return NoteContract.NoteEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }

    }

}