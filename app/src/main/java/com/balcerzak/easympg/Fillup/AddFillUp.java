package com.balcerzak.easympg.Fillup;

import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

public class AddFillUp extends FillUpBaseActivity {

    public void addFillUpClicked(){
        int vehiclePK = getVehiclePK();
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        VehicleInfoStruct vehicle = vehicleAdmin.getVehicleById(vehiclePK);
    }

}
