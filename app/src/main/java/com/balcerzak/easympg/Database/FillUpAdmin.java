package com.balcerzak.easympg.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.balcerzak.easympg.Database.MainDatabase;
import com.balcerzak.easympg.Fillup.FillUpInfoStruct;
import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

import java.util.ArrayList;

public class FillUpAdmin extends MainDatabase {

    final String DATE = "date";
    final String ODOMETER = "odometer";
    final String TOTAL_COST = "total_cost";
    final String MISSED_FILL_UP = "missed_previous_fillup";
    final String PARTIAL_FILL_UP = "partial_fillup";
    final String UNITS_IN_VEHICLE_DEFAULT = "units";
    final String VEHICLE_ID = "vehicle_id";
    final String SELECTED_FUEL_UNIT = "selected_fuel_unit_type";

    public FillUpAdmin(Context context) {
        super(context);
    }

    public void addFillUp(FillUpInfoStruct fillUp){
        ContentValues values = createContentValuesForFillUp(fillUp);
        SQLiteDatabase db = getWritableDatabase();
        final String FILL_UP_TABLE = "fillup";
        db.insert(FILL_UP_TABLE, null, values);
        db.close();
    }

    private ContentValues createContentValuesForFillUp(FillUpInfoStruct fillUp){
        ContentValues values = new ContentValues();
        values.put(DATE, fillUp.getFillUpDate());
        values.put(ODOMETER, fillUp.getOdometer());
        values.put(TOTAL_COST, fillUp.getTotalCost());
        values.put(MISSED_FILL_UP, fillUp.isMissedPreviousFillUp());
        values.put(PARTIAL_FILL_UP, fillUp.isPartialFillUp());
        values.put(UNITS_IN_VEHICLE_DEFAULT, fillUp.getUnitsInDefault());
        values.put(VEHICLE_ID, fillUp.getVehicleId());
        values.put(SELECTED_FUEL_UNIT, fillUp.getFillUpFuelUnits().toString());
        return values;
    }

    public ArrayList<FillUpInfoStruct> getFillUpsForVehicle(final int vehicleId){
        ArrayList<FillUpInfoStruct> fillUps = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        final String query = "SELECT * FROM fillup WHERE " + VEHICLE_ID + " = " + vehicleId;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            fillUps.add(createFillUpFromCursor(cursor));
            cursor.moveToNext();
        }
        return fillUps;
    }

    private FillUpInfoStruct createFillUpFromCursor(Cursor cursor){
        FillUpInfoStruct fillUp = null;
        if (!cursor.isAfterLast()) {
            fillUp = new FillUpInfoStruct(
                    cursor.getInt(cursor.getColumnIndex(VEHICLE_ID)),
                    cursor.getInt(cursor.getColumnIndex(ODOMETER)),
                    cursor.getDouble(cursor.getColumnIndex(TOTAL_COST)),
                    cursor.getDouble(cursor.getColumnIndex(UNITS_IN_VEHICLE_DEFAULT)),
                    getBooleanFromInt(cursor.getInt(cursor.getColumnIndex(MISSED_FILL_UP))),
                    getBooleanFromInt(cursor.getInt(cursor.getColumnIndex(PARTIAL_FILL_UP))),
                    FuelUnits.valueOf(cursor.getString(cursor.getColumnIndex(SELECTED_FUEL_UNIT))),
                    cursor.getString(cursor.getColumnIndex(DATE)));
        }
        return fillUp;
    }

    private boolean getBooleanFromInt(final int value){
        if(value == 0){
            return false;
        } else if (value == 1 ){
            return true;
        } else {
            throw new SQLException("VALUE (" + value + ") COULD NOT BE CONVERTED TO A BOOLEAN");
        }
    }
}
