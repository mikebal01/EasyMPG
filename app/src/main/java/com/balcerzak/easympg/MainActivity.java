package com.balcerzak.easympg;

import android.content.Intent;
import android.os.Bundle;

import com.balcerzak.easympg.Fillup.AddFillUp;
import com.balcerzak.easympg.Vehicle.AddVehicle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open_AddRecords = new Intent(MainActivity.this, AddVehicle.class);
               // open_AddRecords.putExtra("toAddToTable", String.valueOf(databaseTable));
                startActivity(open_AddRecords);
            }
        });

        Button addFillUpButton = findViewById(R.id.buttonAddFillUp);
        addFillUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAddFillUp = new Intent(MainActivity.this, AddFillUp.class);
                // open_AddRecords.putExtra("toAddToTable", String.valueOf(databaseTable));
                startActivity(openAddFillUp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}