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
import com.balcerzak.easympg.Units.FuelUnits;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

import java.util.ArrayList;

public class FillUpBaseActivity extends Activity {

    EditText _odometer, _totalCost, _costPerUnit, _units;
    RadioButton _liters, _usGallons, _imperialGallons;
    Switch _partialFillUp, _missedPreviousFillUp;
    private ArrayList<VehicleInfoStruct> _vehicles = null;
    private TextView _vehicleHeader;
    private int _vehicleIndex = 0;
    Button _dateSelector;

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
        setupUIVariables();
        setupVehicleData(vehiclePK);
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    public VehicleInfoStruct getCurrentlySelectedVehicle(){
        return _vehicles.get(_vehicleIndex);
    }

    private void setupUIVariables() {
        _dateSelector = findViewById(R.id.buttonAddFillupDate);
        _vehicleHeader = findViewById(R.id.textViewAddFillupVehicleHeader);
        _odometer = findViewById(R.id.editTextAddFillUpOdometer);
        _units = findViewById(R.id.editTextAddFillUpUnit);
        _totalCost = findViewById(R.id.editTextTotalCost);
        _costPerUnit = findViewById(R.id.editTextAddFillupCostPerUnit);
        _missedPreviousFillUp = findViewById(R.id.switchMissedPreviousFillup);
        _partialFillUp = findViewById(R.id.switchPartialFillup);

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
                    setDefaultFuelUnit(_vehicles.get(_vehicleIndex).getDefaultFuelUnit());
                }
            }
        });
    }

    private void setupVehicleData(int vehiclePk){
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        _vehicles = vehicleAdmin.getVehicles();
        while (_vehicles.get(_vehicleIndex).getVehiclePK() != vehiclePk){
            _vehicleIndex++;
        }
        _vehicleHeader.setText(_vehicles.get(_vehicleIndex).getDisplayName());
        setDefaultFuelUnit(_vehicles.get(_vehicleIndex).getDefaultFuelUnit());
    }

    public void setDefaultFuelUnit(FuelUnits defaultFuelUnit){
        _imperialGallons = findViewById(R.id.radioButtonFillUpImperialGallon);
        _liters = findViewById(R.id.radioButtonFillupLiters);
        _usGallons = findViewById(R.id.radioButtonFillUpUSGallon);
        if(defaultFuelUnit == FuelUnits.IMPERIAL_GALLON){
            _imperialGallons.setChecked(true);
            _usGallons.setChecked(false);
            _liters.setChecked(false);
        } else if (defaultFuelUnit == FuelUnits.US_GALLON){
            _usGallons.setChecked(true);
            _imperialGallons.setChecked(false);
            _liters.setChecked(false);
        } else{
            _liters.setChecked(true);
            _imperialGallons.setChecked(false);
            _usGallons.setChecked(false);
        }
    }
}