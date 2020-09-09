package com.balcerzak.easympg.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;
import com.balcerzak.easympg.Vehicle.VehicleInfoStruct;

import java.util.ArrayList;

public class VehicleAdmin extends MainDatabase {

    String VEHICLE_ID = "vehicle_id";
    String DISPLAY_NAME = "display_name";
    String ODOMETER = "odometer";
    String MAKE = "make";
    String MODEL = "model";
    String YEAR = "production_year";
    String DISTANCE_UNIT = "distance_unit_type";
    String FUEL_MEASUREMENT_UNIT = "default_fuel_unit_type";

    public VehicleAdmin(Context context) {
        super(context);
    }

    public void addVehicle(VehicleInfoStruct vehicle) {
        ContentValues values = createContentValuesForVehicle(vehicle);
        SQLiteDatabase db = getWritableDatabase();
        String VEHICLE_TABLE = "vehicle";
        db.insert(VEHICLE_TABLE, null, values);
        db.close();
    }

    public ArrayList<VehicleInfoStruct> getVehicles(){
        SQLiteDatabase db = getReadableDatabase();
        final String query = "SELECT * FROM vehicle";
        ArrayList<VehicleInfoStruct> vehicleInfoStructs = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            vehicleInfoStructs.add(createVehicleFromCursor(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return vehicleInfoStructs;
    }

    public VehicleInfoStruct getVehicleById(int vehicleId){
        SQLiteDatabase db = getReadableDatabase();
        final String query = "SELECT * FROM vehicle WHERE " + VEHICLE_ID + " =" + vehicleId;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return createVehicleFromCursor(cursor);
    }
    public void updateVehicle(VehicleInfoStruct vehicle){
/*        ContentValues values = createContentValuesForVehicle(vehicle);
        SQLiteDatabase db = getWritableDatabase();
        db.update(RECORD_TABLE, values,RECORD_ID + "=?", new String[] {record.getRecordPk()});
        db.close();*/
    }

    public void deleteVehicle(String recordPK){
/*        SQLiteDatabase db = getWritableDatabase();
        db.delete(RECORD_TABLE, RECORD_ID + "=?", new String[] {recordPK});
        db.close();*/
    }

    private VehicleInfoStruct createVehicleFromCursor(Cursor cursor){
        VehicleInfoStruct vehicleInfoStruct = null;
        if (!cursor.isAfterLast()) {
            vehicleInfoStruct = new VehicleInfoStruct(cursor.getString(cursor.getColumnIndex(DISPLAY_NAME)),
                    cursor.getInt(cursor.getColumnIndex(ODOMETER)),
                    cursor.getInt(cursor.getColumnIndex(YEAR)),
                    cursor.getString(cursor.getColumnIndex(MAKE)),
                    cursor.getString(cursor.getColumnIndex(MODEL)),
                    DistanceUnits.valueOf(cursor.getString(cursor.getColumnIndex(DISTANCE_UNIT))),
                    FuelUnits.valueOf(cursor.getString(cursor.getColumnIndex(FUEL_MEASUREMENT_UNIT))));
        }
        return vehicleInfoStruct;
    }

    private ContentValues createContentValuesForVehicle(VehicleInfoStruct vehicle){
        ContentValues values = new ContentValues();
        values.put(DISPLAY_NAME, vehicle.getDisplayName());
        values.put(ODOMETER, vehicle.getOdometer());
        values.put(MAKE, vehicle.getMake());
        values.put(MODEL, vehicle.getModel());
        values.put(YEAR, vehicle.getYear());
        values.put(DISTANCE_UNIT, vehicle.getDistanceUnit().toString());
        values.put(FUEL_MEASUREMENT_UNIT, vehicle.getDefaultFuelUnit().toString());
        return values;
    }
}
