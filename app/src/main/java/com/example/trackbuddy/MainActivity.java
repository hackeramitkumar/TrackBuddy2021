package com.example.trackbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calender, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.help:
                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.about:
                Toast.makeText(getApplicationContext(), "Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Logout: {
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}




