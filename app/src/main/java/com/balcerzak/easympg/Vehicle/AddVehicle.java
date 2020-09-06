package com.balcerzak.easympg.Vehicle;

import android.os.Bundle;
import android.view.View;
import com.balcerzak.easympg.Database.VehicleAdmin;

public class AddVehicle extends VehicleBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _km.setChecked(true);
        _liters.setChecked(true);
    }

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
               getDistanceUnit(),
               getFuelUnit());
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        vehicleAdmin.addVehicle(vehicle);
            finish();
        }
}