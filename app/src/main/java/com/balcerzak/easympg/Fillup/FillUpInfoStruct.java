package com.balcerzak.easympg.Fillup;

import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;

public class FillUpInfoStruct {
    private int _vehicleId;
    private int _odometer;
    private double _totalCost;
    private boolean _missedPreviousFillUp;
    private boolean _partialFillUp;
    private double _unitsLiters;

    public FillUpInfoStruct(int vehicleId,
                            int odometer,
                            int year,
                            String make,
                            String model,
                            DistanceUnits distanceUnits,
                            FuelUnits fuelUnits,
                            boolean missedPreviousFillUp,
                            boolean isPartialFillUp){
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

    public double getUnitsLiters() {
        return _unitsLiters;
    }

}
