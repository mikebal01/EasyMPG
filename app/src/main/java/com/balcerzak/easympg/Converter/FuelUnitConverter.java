package com.balcerzak.easympg.Converter;

import com.balcerzak.easympg.Units.FuelUnits;

import java.util.MissingFormatArgumentException;

public class FuelUnitConverter {
    public static double convertToGallon(FuelUnits inputUnit, double inputValue){
        if(inputUnit == FuelUnits.LITERS){
            return inputValue*0.2641729;
        } else if(inputUnit == FuelUnits.IMPERIAL_GALLON){
            return inputValue*1.200954;
        } else{
            throw new MissingFormatArgumentException("convertToGallon: No conversion from " + inputUnit.name() + " to Gallons");
        }
    }

    public static double convertToLiters(FuelUnits inputUnit, double inputValue){
        if(inputUnit == FuelUnits.US_GALLON){
            return inputValue*3.7854;
        } else if(inputUnit == FuelUnits.IMPERIAL_GALLON){
            return inputValue*4.54609;
        } else{
            throw new MissingFormatArgumentException("convertToGallon: No conversion from " + inputUnit.name() + " to Liters");
        }
    }

    public static double convertToImperialGallons(FuelUnits inputUnit, double inputValue){
        if(inputUnit == FuelUnits.LITERS){
            return inputValue*0.2199692;
        } else if(inputUnit == FuelUnits.US_GALLON){
            return inputValue*0.8326716;
        } else{
            throw new MissingFormatArgumentException("convertToGallon: No conversion from " + inputUnit.name() + " to Imperial Gallons");
        }
    }
}
