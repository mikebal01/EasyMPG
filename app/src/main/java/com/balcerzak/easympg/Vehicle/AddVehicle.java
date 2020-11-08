package com.balcerzak.easympg.Vehicle;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.R;

import java.util.ArrayList;

public class AddVehicle extends VehicleBaseActivity {

    private VehicleInfoStruct _vehicle;
    private VehicleAdmin _vehicleAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _km.setChecked(true);
        _liters.setChecked(true);
    }

    public void saveVehicleClicked(View v) {
        int vehicleYear = -1;
        int odometer = -1;

        if (!_odometer.getText().toString().equals("")) {
            odometer = Integer.parseInt(_odometer.getText().toString());
        }
        if (!_year.getText().toString().equals("")) {
            vehicleYear = Integer.parseInt(_year.getText().toString());
        }

        _vehicle = new VehicleInfoStruct(_displayName.getText().toString(),
                odometer,
                vehicleYear,
                _make.getText().toString(),
                _model.getText().toString(),
                getDistanceUnit(),
                getFuelUnit());
        _vehicleAdmin = new VehicleAdmin(getApplicationContext());

        if (validateNewVehicle()) {
            _vehicleAdmin.addVehicle(_vehicle);
            finish();
        }
    }

    private boolean validateNewVehicle() {
            if (!_vehicle.getDisplayName().isEmpty()) {
                    if (!isUniqueVehicle()) {
                        TextView displayName = findViewById(R.id.vehicleDisplayName);
                        displayName.setText(R.string.vehicleDisplayNameUsed);
                        displayName.setTextColor(Color.RED);
                        return false;
                    }
            } else {
                _vehicle.setDefaultDisplayName();
                    if (!isUniqueVehicle()) {
                        TextView displayName = findViewById(R.id.vehicleDisplayName);
                        displayName.setTextColor(Color.RED);
                        TextView make = findViewById(R.id.vehicleMake);
                        make.setTextColor(Color.RED);
                        TextView model = findViewById(R.id.vehicleModel);
                        model.setTextColor(Color.RED);
                        TextView year = findViewById(R.id.vehicleProductionYear);
                        year.setTextColor(Color.RED);
                        return false;
                    }
                _vehicle.setDefaultDisplayName();
            }
        return true;
    }

    private boolean isUniqueVehicle() {
        boolean isUnique = true;
        ArrayList<VehicleInfoStruct> savedVehicles = _vehicleAdmin.getVehicles();
        for (VehicleInfoStruct savedVehicle : savedVehicles) {
            if (savedVehicle.getDisplayName().replace(" ", "").equals(_vehicle.getDisplayName().replace(" ", ""))) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }
}