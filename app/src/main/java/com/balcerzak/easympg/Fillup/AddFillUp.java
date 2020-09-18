package com.balcerzak.easympg.Fillup;

import android.content.res.Resources;

import com.balcerzak.easympg.Converter.FuelUnitConverter;
import com.balcerzak.easympg.Units.FuelUnits;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

public class AddFillUp extends FillUpBaseActivity {

    public void addFillUpClicked(){
        final VehicleInfoStruct currentlySelectedVehicle = getCurrentlySelectedVehicle();

    }

    private double getFuelUnitsInVehicleDefault(VehicleInfoStruct currentlySelectedVehicle){
        FuelUnits selectedFuelUnit = getSelectedFuelUnit();
        double units = Double.parseDouble(_units.getText().toString());
        if(selectedFuelUnit != currentlySelectedVehicle.getDefaultFuelUnit()){
            convertFuelUnitToDefault(selectedFuelUnit, currentlySelectedVehicle.getDefaultFuelUnit(), units);
        }
        return units;
    }

    private FuelUnits getSelectedFuelUnit(){
        if(_liters.isChecked()){
            return FuelUnits.LITERS;
        } else if(_usGallons.isChecked()){
            return FuelUnits.US_GALLON;
        } else if(_imperialGallons.isChecked()){
            return FuelUnits.IMPERIAL_GALLON;
        } else{
            throw new Resources.NotFoundException("NO FUEL UNIT WAS SELECTED ON FILL UP");
        }
    }

    private double convertFuelUnitToDefault(FuelUnits inputUnit, FuelUnits returnUnit, double units){
        if(returnUnit == FuelUnits.US_GALLON){
            return FuelUnitConverter.convertToGallon(inputUnit, units);
        } else if(returnUnit == FuelUnits.LITERS){
            return FuelUnitConverter.convertToLiters(inputUnit, units);
        } else if(returnUnit == FuelUnits.IMPERIAL_GALLON){
            return FuelUnitConverter.convertToImperialGallons(inputUnit, units);
        } else{
            throw new Resources.NotFoundException("NO KNOWN FUEL UNIT CONVERSION");
        }
    }

}
