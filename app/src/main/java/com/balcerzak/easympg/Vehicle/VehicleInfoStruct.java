package com.balcerzak.easympg.Vehicle;

import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;

public class VehicleInfoStruct {

    private int _vehiclePK;
    private String _displayName;
    private int _odometer;
    private int _year;
    private String _make;
    private String _model;
    private DistanceUnits _distanceUnit;
    private FuelUnits _defaultFuelUnit;

/*    public VehicleInfoStruct(int vehiclePK,
                             String displayName,
                             int odometer,
                             int year,
                             String make,
                             String model,
                             DistanceUnits distanceUnits,
                             FuelUnits fuelUnits){
        _vehiclePK = vehiclePK;
        _displayName = displayName;
        _odometer = odometer;
        _year = year;
        _make = make;
        _model = model;
        _distanceUnit = distanceUnits;
        _defaultFuelUnit = fuelUnits;
    }*/

    public VehicleInfoStruct(String displayName,
                             int odometer,
                             int year,
                             String make,
                             String model,
                             DistanceUnits distanceUnits,
                             FuelUnits fuelUnits){
        _displayName = displayName;
        _odometer = odometer;
        _year = year;
        _make = make;
        _model = model;
        _distanceUnit = distanceUnits;
        _defaultFuelUnit = fuelUnits;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public int getOdometer() {
        return _odometer;
    }

    public String getMake() {
        return _make;
    }

    public String getModel() {
        return _model;
    }

    public int getYear() {
        return _year;
    }

    public DistanceUnits getDistanceUnit() {
        return _distanceUnit;
    }

    public FuelUnits getDefaultFuelUnit() {
        return _defaultFuelUnit;
    }

    public int getVehiclePK() { return _vehiclePK; }
}

