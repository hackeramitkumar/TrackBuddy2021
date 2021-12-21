package com.example.trackbuddy.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.trackbuddy.R;
import com.example.trackbuddy.notes_data.NoteContract;


public class NoteCursorAdapter extends CursorAdapter {

       public NoteCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.description);

        // Find the columns of pet attributes that we're interested in
        int titleColumnIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);
        int descriptionColumnIndex = cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_NOTE_DESCRIPTION);

        // Read the pet attributes from the Cursor for the current pet
        String notetitle = cursor.getString(titleColumnIndex);
        String notedescription = cursor.getString(descriptionColumnIndex);

        // Update the TextViews with the attributes for the current pet
        titleTextView.setText(notetitle);
        descriptionTextView.setText(notedescription);
    }
}