package com.example.trackbuddy;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;


    public MainAdapter(@NonNull FragmentManager fm,Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
        switch(position){
            case 0:
                calenderFragment calenderFragment = new calenderFragment();
                return calenderFragment;
            case 1:
                toDoFragment toDoFragment = new toDoFragment();
                return toDoFragment;


            case 2:
                notesFragment notesFragment = new notesFragment();
                return notesFragment;

            default:
                return null;
        }

    }
}
