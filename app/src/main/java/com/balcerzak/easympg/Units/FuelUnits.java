package com.balcerzak.easympg.Units;

public enum FuelUnits {
    LITERS ("liters"),
    US_GALLON("us_gallon"),
    IMPERIAL_GALLON("imperial_gallon");

    private String stringResource;
    FuelUnits(String toString) {
        stringResource = toString;
    }

    public String getStringResource(){
        return this.stringResource;
    }
}

