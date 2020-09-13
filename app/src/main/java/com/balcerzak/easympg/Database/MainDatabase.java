package com.balcerzak.easympg.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MainDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "records.db";

    public MainDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createFueTypeTable = "CREATE TABLE fuel_type (fuel_type_code TEXT UNIQUE)";
        db.execSQL(createFueTypeTable);
        db.execSQL("insert into fuel_type (fuel_type_code) values ('LITERS');");
        db.execSQL("insert into fuel_type (fuel_type_code) values ('US_GALLON');");
        db.execSQL("insert into fuel_type (fuel_type_code) values ('IMPERIAL_GALLON');");

        final String createDistanceUnitTypeTable = "CREATE TABLE distance_type (distance_type_code TEXT UNIQUE)";
        db.execSQL(createDistanceUnitTypeTable);
        db.execSQL("insert into distance_type (distance_type_code) values ('KM');");
        db.execSQL("insert into distance_type (distance_type_code) values ('MILES');");

        final String queryTableVehicle = "CREATE TABLE vehicle (vehicle_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "display_name TEXT, " +
                "cost_per_unit TEXT, " +
                "production_year INTEGER," +
                "model TEXT, " +
                "make TEXT, " +
                "odometer INTEGER," +
                "distance_unit_type TEXT," +
                "default_fuel_unit_type TEXT," +
                "FOREIGN KEY (distance_unit_type) REFERENCES distance_type (distance_type_code)," +
                "FOREIGN KEY (default_fuel_unit_type) REFERENCES fuel_type (fuel_type_code));";
        db.execSQL(queryTableVehicle);

        final String queryTableFillUp = "CREATE TABLE fillup (fill_up_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "odometer TEXT, " +
                "total_cost INTEGER," +
                "missed_previous_fillup INTEGER," +
                "partial_fillup INTEGER," +
                "units_liters REAL," +
                "vehicle_id INTEGER," +
                "FOREIGN KEY (vehicle_id) REFERENCES vehicle (vehicle_id));";
        db.execSQL(queryTableFillUp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
//            db.execSQL("ALTER TABLE records ADD COLUMN notes TEXT");
        }

    }

}