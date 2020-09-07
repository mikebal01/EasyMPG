package com.balcerzak.easympg.Fillup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.balcerzak.easympg.R;
import com.balcerzak.easympg.Units.DistanceUnits;
import com.balcerzak.easympg.Units.FuelUnits;

import java.util.Calendar;
import java.util.Date;

public class FillUpBaseActivity extends Activity {

    EditText _displayName, _make, _model, _year, _odometer;
    RadioButton _km, _mile, _liters, _usGallons, _imperialGallons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfillup);
        setupVariables();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    private void setupVariables() {
        Button dateSelector = findViewById(R.id.buttonEditDate);
    }
}