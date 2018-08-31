package com.university.soa.bus.RadioButton;

/**
 * Created by pkumar on 5/6/18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.university.soa.bus.R;
import com.university.soa.bus.SeatClass.SeatSelection4;
import com.university.soa.bus.SeatClass.SeatSelection5;

public class checkbox extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        final RadioButton O1 = (RadioButton) findViewById(R.id.Opt1);
        final RadioButton O2 = (RadioButton) findViewById(R.id.Opt2);
        final RadioButton O3 = (RadioButton) findViewById(R.id.Opt3);
        final RadioButton O4 = (RadioButton) findViewById(R.id.Opt4);


        final Button go = (Button) findViewById(R.id.OK);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (O1.isChecked()) {
                    Intent Intents= new Intent(checkbox.this, SeatSelection4.class); // <----- START "BEACHES" ACTIVITY
                    startActivity(Intents);
                }
                else if (O2.isChecked()) {
                    Intent Intentm = new Intent(getApplicationContext(), SeatSelection5.class); // <----- START "HIll STATIONS" ACTIVITY
                    startActivityForResult(Intentm, 0);
                }
                else if (O3.isChecked()) {
                    Intent Intentw = new Intent(getApplicationContext(), SeatSelection4.class); // <----- START "WILDLIFE" ACTIVITY
                    startActivityForResult(Intentw, 0);
                }
                else if (O4.isChecked()) {
                    Intent Intenth = new Intent(getApplicationContext(), SeatSelection5.class); // <----- START "HISTORICAL MONUMENTS" ACTIVITY
                    startActivityForResult(Intenth, 0);
                }
            }
        });

    }
    }
