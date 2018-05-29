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
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DisplayTip extends AppCompatActivity {
    private TextView billAmount;
    private TextView tipAmount;
    private TextView totalAmount;
    private TextView perPersonPay;
    private Button mReturnToMainMenu;
    /*Used to round off all double values to two decimal places */
    private static DecimalFormat df2 = new DecimalFormat(".##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tip);
        Intent mainActivityIntent = getIntent();
        Double tip_result = mainActivityIntent.getDoubleExtra("tip_result", 0.0);
        Double amount = mainActivityIntent.getDoubleExtra("amount", 0.0);
        Integer numberOfPeople = mainActivityIntent.getIntExtra("numberOfPeople", 1);
        String total_amount = df2.format(tip_result + amount);
        String perPersonPayAmount = df2.format(Double.parseDouble(total_amount)/numberOfPeople);

        billAmount = (TextView) findViewById(R.id.bill_amount);
        billAmount.setText("The Bill Amount is: " + amount);
        tipAmount = (TextView) findViewById(R.id.tip_amount);
        tipAmount.setText("The Chosen Tip Amount is: " + tip_result);
        totalAmount = (TextView) findViewById(R.id.total_amount);
        totalAmount.setText("The Total Bill is: " + total_amount);
        perPersonPay = (TextView) findViewById(R.id.per_person_pay);
        perPersonPay.setText("The Pay Per Person is: "+ perPersonPayAmount);
        if(numberOfPeople == 1){
            perPersonPay.setVisibility(View.GONE);
        }

        mReturnToMainMenu = (Button) findViewById(R.id.return_to_main_menu);
        mReturnToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToMainMenuIntent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(returnToMainMenuIntent);
            }
        });

    }
}
