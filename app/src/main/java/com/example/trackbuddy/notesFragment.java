package com.example.trackbuddy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.trackbuddy.data.NotesContract.NoteEntry;

import androidx.fragment.app.Fragment;

import com.example.trackbuddy.data.NotesCursorAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class notesFragment extends Fragment {

    ListView notesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_notes, container, false);


        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.add_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), notesAddEventActivity.class);
                startActivity(intent);
            }
        });

        notesList = (ListView) root.findViewById(R.id.notes_list_view);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                NoteEntry._ID,
                NoteEntry.COLUMN_HEADING,
                NoteEntry.COLUMN_DESCRIPTION,
        };
        // Perform a query on the provider using the ContentResolver.
        // Use the {@link NoteEntry#CONTENT_URI} to access the pet data.
        Cursor cursor = getContext().getContentResolver().query(
                NoteEntry.CONTENT_URI,   // The content URI of the words table
                projection,             // The columns to return for each row
                null,                   // Selection criteria
                null,                   // Selection criteria
                null);                  // The sort order for the returned rows

        NotesCursorAdapter adapter = new NotesCursorAdapter(getContext(), cursor);
        notesList.setAdapter(adapter);
    }
}
