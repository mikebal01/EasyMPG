package com.balcerzak.easympg.Vehicle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.balcerzak.easympg.R;
import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;

public class VehicleBaseActivity extends Activity {

    EditText _displayName, _make, _model, _year, _odometer;
    RadioButton _km, _mile, _liters, _usGallons, _imperialGallons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle);
        setupVariables();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    private void setupVariables(){
        _displayName = findViewById(R.id.editTextDisplayName);
        _make = findViewById(R.id.editTextmake);
        _model = findViewById(R.id.editTextModel);
        _year = findViewById(R.id.editTextYear);
        _odometer = findViewById(R.id.editTextOdometer);
        _km = findViewById(R.id.radioButtonDistanceKM);
        _mile = findViewById(R.id.radioButtonDistanceMiles);
        _liters = findViewById(R.id.radioButtonFillupLiters);
        _usGallons = findViewById(R.id.radioButtonFillUpUSGallon);
        _imperialGallons = findViewById(R.id.radioButtonFillUpImperialGallon);
    }

    FuelUnits getFuelUnit(){
        if(_liters.isChecked()){
            return FuelUnits.LITERS;
        } else if (_usGallons.isChecked()){
            return FuelUnits.US_GALLON;
        } else {
            return FuelUnits.IMPERIAL_GALLON;
        }
    }

    DistanceUnits getDistanceUnit(){
        if(_km.isChecked()){
            return DistanceUnits.KM;
        } else {
            return DistanceUnits.MILES;
        }
    }

    public void saveVehicleClicked(View view) {
    }
}