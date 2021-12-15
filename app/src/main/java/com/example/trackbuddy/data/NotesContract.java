package com.example.trackbuddy.data;

import android.net.Uri;
import android.provider.BaseColumns;

// contract class which contains the name of the table as well as all the columns
public final class NotesContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.trackbuddy";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_NOTES = "notes";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private NotesContract() {}


    public static final class NoteEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        public final static String TABLE_NAME = "notes";


        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_HEADING = "heading";

        public final static String COLUMN_DESCRIPTION = "description";
    }

}
