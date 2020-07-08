package com.balcerzak.easympg;

import android.view.View;
import android.widget.RadioButton;

import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;

public class AddVehicle extends VehicleBaseActivity{

    public void saveVehicleClicked(View v) {
        int vehicleYear = -1;
        int odometer = -1;

        if(!_odometer.getText().toString().equals("")){
            odometer = Integer.parseInt(_odometer.getText().toString());
        }
        if(!_year.getText().toString().equals("")){
            vehicleYear = Integer.parseInt(_year.getText().toString());
        }

        VehicleInfoStruct vehicle = new VehicleInfoStruct(_displayName.getText().toString(),
               odometer,
               vehicleYear,
               _make.getText().toString(),
               _model.getText().toString(),
               _distanceUnit,
               _fuelUnit);
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        vehicleAdmin.addVehicle(vehicle);
            finish();
        }

        public void distanceRadioClick(View view){
            RadioButton toHide;
            if(view.getId() == R.id.radioButtonDistanceKM) {
                _distanceUnit = DistanceUnits.KM;
                toHide = (RadioButton) findViewById(R.id.radioButtonDistanceMiles);
            } else {
                _distanceUnit = DistanceUnits.MILES;
                toHide = (RadioButton) findViewById(R.id.radioButtonDistanceKM);
            }
            toHide.setSelected(false);
        }

    public void fuelMeasurementRadioClick(View view){
        RadioButton toHide;
        RadioButton toHide2;
        if(view.getId() == R.id.radioButtonLiters) {
            _fuelUnit = FuelUnits.LITERS;
            toHide = (RadioButton) findViewById(R.id.radioButtonImperialGallon);
            toHide2 = (RadioButton) findViewById(R.id.radioButtonUSGallon);
        } else if(view.getId() == R.id.radioButtonUSGallon) {
            _fuelUnit = FuelUnits.US_GALLON;
            toHide = (RadioButton) findViewById(R.id.radioButtonImperialGallon);
            toHide2 = (RadioButton) findViewById(R.id.radioButtonLiters);
        } else {
            _fuelUnit = FuelUnits.IMPERIAL_GALLON;
            toHide = (RadioButton) findViewById(R.id.radioButtonLiters);
            toHide2 = (RadioButton) findViewById(R.id.radioButtonUSGallon);
        }
        toHide.setSelected(false);
        toHide2.setSelected(false);
    }
}

