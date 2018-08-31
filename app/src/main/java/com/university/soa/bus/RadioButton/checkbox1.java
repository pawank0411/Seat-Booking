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

public class checkbox1 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing2);
        final RadioButton O5 = (RadioButton) findViewById(R.id.Opt5);
        final RadioButton O6 = (RadioButton) findViewById(R.id.Opt6);
        final RadioButton O7 = (RadioButton) findViewById(R.id.Opt7);
        final RadioButton O8 = (RadioButton) findViewById(R.id.Opt8);


        final Button go = (Button) findViewById(R.id.OK1);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (O5.isChecked()) {
                    Intent Intents= new Intent(checkbox1.this, SeatSelection4.class); // <----- START "BEACHES" ACTIVITY
                    startActivity(Intents);
                }
                else if (O6.isChecked()) {
                    Intent Intentm = new Intent(getApplicationContext(), SeatSelection5.class); // <----- START "HIll STATIONS" ACTIVITY
                    startActivityForResult(Intentm, 0);
                }
                else if (O7.isChecked()) {
                    Intent Intentw = new Intent(getApplicationContext(), SeatSelection4.class); // <----- START "WILDLIFE" ACTIVITY
                    startActivityForResult(Intentw, 0);
                }
                else if (O8.isChecked()) {
                    Intent Intenth = new Intent(getApplicationContext(), SeatSelection5.class); // <----- START "HISTORICAL MONUMENTS" ACTIVITY
                    startActivityForResult(Intenth, 0);
                }
            }
        });

    }
}
