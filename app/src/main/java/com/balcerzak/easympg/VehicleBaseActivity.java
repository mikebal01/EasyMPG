package com.balcerzak.easympg;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class VehicleBaseActivity extends Activity {

    EditText _displayName, _make, _model, _year, _odometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle);
        setupVariables();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    private void setupVariables(){
        _displayName = (EditText) findViewById(R.id.editTextDisplayName);
        _make = (EditText) findViewById(R.id.editTextmake);
        _model = (EditText) findViewById(R.id.editTextModel);
        _year = (EditText) findViewById(R.id.editTextYear);
        _odometer = (EditText) findViewById(R.id.editTextOdometer);
    }

}