package com.balcerzak.easympg.Fillup;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.balcerzak.easympg.Converter.FuelUnitConverter;
import com.balcerzak.easympg.Database.FillUpAdmin;
import com.balcerzak.easympg.Database.VehicleAdmin;
import com.balcerzak.easympg.R;
import com.balcerzak.easympg.Units.FuelUnits;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

public class AddFillUp extends FillUpBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView defaultUnitHeader = findViewById(R.id.addFuelDefaultHeader);
        String defaultResource = getStringByIdName(getApplicationContext(), getCurrentlySelectedVehicle().getDefaultFuelUnit().getStringResource());
        defaultUnitHeader.setText(String.format(getString(R.string.default_fill_up_units), defaultResource));
    }

    public static String getStringByIdName(Context context, String idName) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()));
    }

    public void addFillUpClicked(View v){
        final VehicleInfoStruct currentlySelectedVehicle = getCurrentlySelectedVehicle();
        final double fuelUnitsInVehicleDefault = getFuelUnitsInVehicleDefault(currentlySelectedVehicle);
        final FuelUnits selectedFuelUnit = getSelectedFuelUnit();
        final boolean isMissedPreviousFillUpChecked = _missedPreviousFillUp.isChecked();
        final boolean isPartialFillUp = _partialFillUp.isChecked();
        final double totalCost = Double.parseDouble(_totalCost.getText().toString());
        final int odometer = Integer.parseInt(_odometer.getText().toString());
        final String date = _day + "-" + _month + "-" + _year;

        if(isOdometerValueLowerThenPrevious(currentlySelectedVehicle.getVehiclePK(), odometer))
        {
            TextView odometerWarning = findViewById(R.id.textViewAddFillUpOdometerWarning);
            odometerWarning.setVisibility(View.VISIBLE);
        } else {
            FillUpInfoStruct fillUpInfoStruct = new FillUpInfoStruct(currentlySelectedVehicle.getVehiclePK(),
                    odometer,
                    totalCost,
                    fuelUnitsInVehicleDefault,
                    isMissedPreviousFillUpChecked,
                    isPartialFillUp,
                    selectedFuelUnit,
                    date);

            FillUpAdmin fillUpAdmin = new FillUpAdmin(getApplicationContext());
            fillUpAdmin.addFillUp(fillUpInfoStruct);
            finish();
        }
    }

    public void fillUpInDifferentUnitClicked(View v){
        RadioGroup radioButtons = findViewById(R.id.radioGroupFillUpFuelUnits);
        CheckBox checkBox = findViewById(v.getId());
        if(checkBox.isChecked()){
            radioButtons.setVisibility(View.VISIBLE);
        } else{
            radioButtons.setVisibility(View.GONE);
            radioButtons.clearCheck();
        }
    }

    private double getFuelUnitsInVehicleDefault(VehicleInfoStruct currentlySelectedVehicle){
        FuelUnits selectedFuelUnit = getSelectedFuelUnit();
        double units = Double.parseDouble(_units.getText().toString());
        if(selectedFuelUnit != currentlySelectedVehicle.getDefaultFuelUnit()){
            units = convertFuelUnitToDefault(selectedFuelUnit, currentlySelectedVehicle.getDefaultFuelUnit(), units);
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

    private boolean isOdometerValueLowerThenPrevious(int vehiclePk, int currentOdometer){
        FillUpAdmin fillUpAdmin = new FillUpAdmin(getApplicationContext());
        VehicleAdmin vehicleAdmin = new VehicleAdmin(getApplicationContext());
        return (currentOdometer < ((fillUpAdmin.getMaxOdometer(vehiclePk) > 0) ? fillUpAdmin.getMaxOdometer(vehiclePk)
                : vehicleAdmin.getVehicleById(vehiclePk).getOdometer()));
    }
}
