package com.example.zaidsaeed.tipcalculator;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    private Spinner mCurrencyDropdown;
    private SeekBar mSetDefaultPercentage;
    private TextView mDisplayTipValue;
    private MyApplication mMyApplication;
    private Button mSetDefaults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mMyApplication = ((MyApplication)getApplicationContext());

        mCurrencyDropdown = (Spinner) findViewById(R.id.currency_dropdown);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_spinner_item);

        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mCurrencyDropdown.setAdapter(staticAdapter);

        mCurrencyDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] currencies = getResources().getStringArray(R.array.currencies);
                mMyApplication.setDefaultCurrency(currencies[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSetDefaultPercentage = (SeekBar) findViewById(R.id.select_default_tip_percentage);
        mDisplayTipValue = (TextView) findViewById(R.id.display_tip);
        mSetDefaultPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDisplayTipValue.setText("The default tip value is: " + String.valueOf(progress) + "%");
                mDisplayTipValue.setTextColor(getResources().getColor(R.color.textColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mMyApplication.setDefaultTip(seekBar.getProgress());
            }
        });

        mSetDefaults = (Button) findViewById(R.id.set_default_button);
        mSetDefaults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackToHomePage = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(goBackToHomePage);
            }
        });
    }
}
