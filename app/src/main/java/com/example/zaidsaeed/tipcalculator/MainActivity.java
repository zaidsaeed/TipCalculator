package com.example.zaidsaeed.tipcalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private PrefixEditText mEditAmount;
    private EditText mEditPercentage;
    private EditText mNumberOfPeople;
    private Button mDone;
    private TextView mSuggestTip;
    private CoordinatorLayout mSnackBarLayout;
    private ImageButton mSettingsButton;
    private PrefManager mPrefManager;
    public String currencySign;

    public void createSnackBar(String snackBarMessage){
        final Snackbar snackbar = Snackbar.make(mSnackBarLayout, snackBarMessage, Snackbar.LENGTH_INDEFINITE);

        TextView tv = (TextView)(snackbar.getView().findViewById(android.support.design.R.id.snackbar_text));
        tv.setTextColor(Color.WHITE);

        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        snackbar.setActionTextColor(Color.WHITE);
        snackbar.setAction("OK", new View.OnClickListener(){
            @Override
            public void onClick(View v){
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefManager = new PrefManager(this);
        mSnackBarLayout = findViewById(R.id.snackbar_container);

        mSettingsButton = (ImageButton) findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View newView) {
                Intent goToSettingsPageIntent = new Intent(newView.getContext(), Settings.class);
                newView.getContext().startActivity(goToSettingsPageIntent);
            }
        });


        String defaultCurreny = mPrefManager.getDefaultCurrency();
        if(defaultCurreny.equals("Dollar")){
            currencySign = "$";
        }else if(defaultCurreny.equals("Pound")){
            currencySign = "£";
        }else if(defaultCurreny.equals("Euro")){
            currencySign = "€";
        }else{
            currencySign = "   ";
        }

        mEditAmount = (PrefixEditText) findViewById(R.id.total_amount);
        mEditAmount.setTag(currencySign);
        if(mEditAmount.getText().toString().length() == 0){
            mEditAmount.setError("Please Enter an amount");
            mEditAmount.setTag("   ");
        }
        mEditAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(mEditAmount.getText().toString().length() == 0){
                    mEditAmount.setError("Please Enter an amount");
                }
                if(mEditAmount.getText().toString().length() == 0){
                    mEditAmount.setTag("   ");
                }
                if(mEditAmount.getText().toString().length() != 0){
                    mEditAmount.setTag(currencySign);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mEditAmount.getText().toString().length() == 0){
                    mEditAmount.setError("Please Enter an amount");
                }
                if(mEditAmount.getText().toString().length() == 0){
                    mEditAmount.setTag("   ");
                }
                if(mEditAmount.getText().toString().length() != 0){
                    mEditAmount.setTag(currencySign);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mEditAmount.getText().toString().length() == 0){
                    mEditAmount.setError("Please Enter an amount");
                }
                if(mEditAmount.getText().toString().length() == 0){
                    mEditAmount.setTag("   ");
                }
                if(mEditAmount.getText().toString().length() != 0){
                    mEditAmount.setTag(currencySign);
                }
            }
        });

        mEditPercentage = (EditText) findViewById(R.id.tip_percentage);
        Intent suggestTipIntent = getIntent();
        Float suggestedTipRating = suggestTipIntent.getFloatExtra("tip_percentage", (float) 0.0);
        String defaultTip = mPrefManager.getDefaultTip() == null ? "" : String.valueOf(mPrefManager.getDefaultTip());
        mEditPercentage.setText(defaultTip);
        mEditPercentage.setText(suggestedTipRating != (float) 0.0 ? String.valueOf(suggestedTipRating) : defaultTip);
        mEditPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(mEditPercentage.getText().toString().length() == 0){
                    mEditPercentage.setError("Please enter a tip percentage");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mEditPercentage.getText().toString().length() == 0){
                    mEditPercentage.setError("Please enter a tip percentage");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mEditPercentage.getText().toString().length() == 0){
                    mEditPercentage.setError("Please enter a tip percentage");
                }
            }
        });

        mNumberOfPeople = (EditText) findViewById(R.id.number_of_people);
        mNumberOfPeople.setText("1");

        mSuggestTip = (TextView) findViewById(R.id.tip_suggestion);
        mSuggestTip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent toSuggestTipIntent = new Intent(V.getContext(), SuggestTip.class);
                V.getContext().startActivity(toSuggestTipIntent);
            }
        });

        mDone = (Button) findViewById(R.id.done);
        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mEditAmount.getText().toString().length() == 0){
                    createSnackBar("Please Enter a bill amount.");
                }else if(mEditPercentage.getText().toString().length() == 0){
                    createSnackBar("Please Enter a tip percentage");
                } else if (mNumberOfPeople.getText().toString().length() == 0) {
                    createSnackBar("Please Enter the number of people:");
                } else {
                    double amount = Double.parseDouble(mEditAmount.getText().toString());
                    double tipPercentage = Double.parseDouble(mEditPercentage.getText().toString());
                    Integer numberOfPeople = Integer.parseInt(mNumberOfPeople.getText().toString());
                    double tip_result = (amount) * ((tipPercentage) / 100);
                    Intent viewTipIntent = new Intent(v.getContext(), DisplayTip.class);
                    viewTipIntent.putExtra("tip_result", tip_result);
                    viewTipIntent.putExtra("amount", amount);
                    viewTipIntent.putExtra("numberOfPeople", numberOfPeople);
                    v.getContext().startActivity(viewTipIntent);
                }
            }
        });
    }
}
