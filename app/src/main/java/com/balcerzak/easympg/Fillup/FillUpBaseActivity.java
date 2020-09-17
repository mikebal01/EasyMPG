package com.balcerzak.easympg.Fillup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.R;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

import java.util.ArrayList;

public class FillUpBaseActivity extends Activity {

    EditText _odometer, _totalCost, _costPerUnit, _units;
    RadioButton _liters, _usGallons, _imperialGallons;
    Switch _partialFillUp, _missedPreviousFillUp;
    private ArrayList<VehicleInfoStruct> _vehicles = null;
    private TextView _vehicleHeader;
    private int _vehicleIndex = 0;
    private int _vehiclePK;
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
        } else {
            _vehiclePK = -1;
        }
        setupVariables(vehiclePK);
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    public int getVehiclePK(){
        return _vehiclePK;
    }

    private void setupVariables(int vehiclePk) {
        Button dateSelector = findViewById(R.id.buttonAddFillupDate);
        _vehicleHeader = findViewById(R.id.textViewAddFillupVehicleHeader);
        _odometer = findViewById(R.id.editTextOdometer);
        _totalCost = findViewById(R.id.editTextTotalCost);
        _costPerUnit = findViewById(R.id.editTextAddFillupCostPerUnit);
        _units = findViewById(R.id.editTextAddFillUpUnit);
        _liters = findViewById(R.id.radioButtonFillupLiters);
        _usGallons = findViewById(R.id.radioButtonFillUpUSGallon);
        _imperialGallons = findViewById(R.id.radioButtonFillUpImperialGallon);
        _missedPreviousFillUp = findViewById(R.id.switchMissedPreviousFillup);
        _partialFillUp = findViewById(R.id.switchPartialFillup);

        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        _vehicles = vehicleAdmin.getVehicles();
        while (_vehicles.get(_vehicleIndex).getVehiclePK() != vehiclePk){
            _vehicleIndex++;
        }
        _vehicleHeader.setText(_vehicles.get(_vehicleIndex).getDisplayName());

        Button vehicleHeaderNext = findViewById(R.id.buttonAddFillUpNextVehicle);
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
}