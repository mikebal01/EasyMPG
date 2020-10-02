package com.balcerzak.easympg.MainView;

import android.content.Intent;
import android.os.Bundle;

import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.Fillup.AddFillUp;
import com.balcerzak.easympg.R;
import com.balcerzak.easympg.Vehicle.AddVehicle;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<VehicleInfoStruct> _vehicles = null;
    private TextView _vehicleHeader;
    private int _vehicleIndex = 0;

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
                Intent openAddVehicle = new Intent(MainActivity.this, AddVehicle.class);
                //openAddVehicle.putExtra("vehiclePK", _vehicles.get(_vehicleIndex).getVehiclePK());
                startActivity(openAddVehicle);
            }
        });

        Button addFillUpButton = findViewById(R.id.buttonAddFillUp);
        addFillUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getVehiclePk() != -1) {
                    Intent openAddFillUp = new Intent(MainActivity.this, AddFillUp.class);
                    openAddFillUp.putExtra("vehiclePK", _vehicles.get(_vehicleIndex).getVehiclePK());
                    startActivity(openAddFillUp);
                }
            }
        });

        setupVehicleHeader();
        Button vehicleHeaderNext = findViewById(R.id.buttonVehicleHeaderNext);
        vehicleHeaderNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!_vehicles.isEmpty()) {
                    _vehicleIndex++;
                    if (_vehicleIndex == _vehicles.size()) {
                        _vehicleIndex = 0;
                    }
                    _vehicleHeader.setText(_vehicles.get(_vehicleIndex).getDisplayName());
                }
            }
        });
    }
    private void setupVehicleHeader(){
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        Button _vehicleHeaderNext = findViewById(R.id.buttonVehicleHeaderNext);
        _vehicles = vehicleAdmin.getVehicles();
        _vehicleHeader = findViewById(R.id.textViewVehicleHeader);

        if(_vehicles.isEmpty()){
            _vehicleHeaderNext.setClickable(false);
            _vehicleHeader.setText("Add Vehicle to get Started");
        }else{
            _vehicleHeader.setText(_vehicles.get(_vehicleIndex).getDisplayName());
            }
    }

    @Override
    public void onResume() {
        super.onResume();
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        _vehicles = vehicleAdmin.getVehicles();
        setupVehicleHeader();
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

    int getVehiclePk(){
        if(_vehicles.size() == 0){
            return -1;
        }
        return _vehicles.get(_vehicleIndex).getVehiclePK();
    }
}