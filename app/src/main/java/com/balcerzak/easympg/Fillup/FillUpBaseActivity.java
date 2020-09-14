package com.balcerzak.easympg.Fillup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.R;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

import java.util.ArrayList;

public class FillUpBaseActivity extends Activity {

    EditText _displayName, _make, _model, _year, _odometer;
    RadioButton _km, _mile, _liters, _usGallons, _imperialGallons;
    private ArrayList<VehicleInfoStruct> _vehicles = null;
    private TextView _vehicleHeader;
    private int _vehicleIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfillup);
        int vehiclePK = -1;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                vehiclePK = extras.getInt("vehiclePK");
            }
        }
        setupVariables(vehiclePK);
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    private void setupVariables(int vehiclePk) {
        Button dateSelector = findViewById(R.id.buttonEditDate);
        _vehicleHeader = findViewById(R.id.textViewAddFillupVehicleHeader);

        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        _vehicles = vehicleAdmin.getVehicles();
        //TODO: FIND WHICH VEHICLE TO DISPLAY SO OTHER PROPERTIES CAN BE TRACKED

    }
}