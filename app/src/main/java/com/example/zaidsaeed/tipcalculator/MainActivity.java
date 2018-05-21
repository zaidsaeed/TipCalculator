package com.example.zaidsaeed.tipcalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText mEditAmount;
    private EditText mEditPercentage;
    private EditText mNumberOfPeople;
    private Button mDone;
    private TextView mSuggestTip;
    private CoordinatorLayout mSnackBarLayout;

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
                mEditAmount.setText("");
                mEditPercentage.setText("");
            }
        });
        snackbar.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditAmount = (EditText) findViewById(R.id.total_amount);
        mEditPercentage = (EditText) findViewById(R.id.tip_percentage);
        mNumberOfPeople = (EditText) findViewById(R.id.number_of_people);
        mDone = (Button) findViewById(R.id.done);
        mSnackBarLayout = findViewById(R.id.snackbar_container);
        mSuggestTip = (TextView) findViewById(R.id.tip_suggestion);
        mSuggestTip.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Intent toSuggestTipIntent = new Intent(V.getContext(), SuggestTip.class);
                V.getContext().startActivity(toSuggestTipIntent);
            }
        });

        mDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mEditAmount.getText().toString().length() == 0){
                    createSnackBar("Please Enter a bill amount.");
                }else if(mEditPercentage.getText().toString().length() == 0){
                    createSnackBar("Please Enter a tip percentage");
                }else{
                    double amount = Double.parseDouble(mEditAmount.getText().toString());
                    double tipPercentage = Double.parseDouble(mEditPercentage.getText().toString());
                    Integer numberOfPeople = Integer.parseInt(mNumberOfPeople.getText().toString());
                    double tip_result = (amount)*((tipPercentage)/100);
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
