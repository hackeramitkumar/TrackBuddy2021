package com.example.trackbuddy.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.trackbuddy.R;
import com.example.trackbuddy.data.NotesContract.NoteEntry;


public class NotesCursorAdapter extends CursorAdapter {


    public NotesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.notes_list_items, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView headingTextView = (TextView) view.findViewById(R.id.list_item_heading);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.list_item_description);

        // Find the columns of pet attributes that we're interested in
        int headingColumnIndex = cursor.getColumnIndex(NoteEntry.COLUMN_HEADING);
        int descriptionColumnIndex = cursor.getColumnIndex(NoteEntry.COLUMN_DESCRIPTION);

        // Read the pet attributes from the Cursor for the current pet
        String headingString = cursor.getString(headingColumnIndex);
        String descriptionString = cursor.getString(descriptionColumnIndex);

        // Update the TextViews with the attributes for the current pet
        headingTextView.setText(headingString);
        descriptionTextView.setText(descriptionString);
    }
}
