package com.balcerzak.easympg.Fillup;

import com.balcerzak.easympg.Units.FuelUnits;

public class FillUpInfoStruct {
    private int _vehicleId;
    private int _odometer;
    private double _totalCost;
    private boolean _missedPreviousFillUp;
    private boolean _partialFillUp;
    private double _unitsInDefault;
    private FuelUnits _fillUpFuelUnits;
    private String _fillUpDate;

    public FillUpInfoStruct(int vehicleId,
                            int odometer,
                            double totalCost,
                            double units,
                            boolean isMissedPreviousFillUpChecked,
                            boolean isPartialFillUpChecked,
                            FuelUnits fuelUnits,
                            String fillUpDate){
        _vehicleId = vehicleId;
        _odometer = odometer;
        _totalCost = totalCost;
        _unitsInDefault = units;
        _missedPreviousFillUp = isMissedPreviousFillUpChecked;
        _partialFillUp = isPartialFillUpChecked;
        _fillUpFuelUnits = fuelUnits;
        _fillUpDate = fillUpDate;
    }

    public int getVehicleId() {
        return _vehicleId;
    }

    public int getOdometer() {
        return _odometer;
    }

    public double getTotalCost() {
        return _totalCost;
    }

    public boolean isMissedPreviousFillUp() {
        return _missedPreviousFillUp;
    }

    public boolean isPartialFillUp() {
        return _partialFillUp;
    }

    public double getUnitsInDefault() {
        return _unitsInDefault;
    }

    public FuelUnits getFillUpFuelUnits() {
        return _fillUpFuelUnits;
    }

    public String getFillUpDate() {
        return _fillUpDate;
    }
}
