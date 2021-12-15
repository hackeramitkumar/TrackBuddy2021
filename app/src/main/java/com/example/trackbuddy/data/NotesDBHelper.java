package com.example.trackbuddy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.trackbuddy.data.NotesContract.NoteEntry;

// this class is basically used to create a database called all_the_notes
public class NotesDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = NotesDBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "all_the_notes.db";


    private static final int DATABASE_VERSION = 1;

    public NotesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + NoteEntry.TABLE_NAME + " ("
                + NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NoteEntry.COLUMN_HEADING + " TEXT NOT NULL, "
                + NoteEntry.COLUMN_DESCRIPTION + " TEXT); ";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
