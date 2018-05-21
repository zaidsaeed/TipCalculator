package com.example.zaidsaeed.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SuggestTip extends AppCompatActivity {
    RatingBar mRatingBar;
    Button mButton;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_tip);

        mRatingBar = (RatingBar) findViewById(R.id.star_rating_bar);
        mTextView = (TextView) findViewById(R.id.text_view);
        mButton = (Button) findViewById(R.id.use_tip);
        mRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mTextView.setText(String.valueOf(rating));
            }
        });

    }

}
