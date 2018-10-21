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

import com.university.soa.bus.BookingInfo;
import com.university.soa.bus.R;
import com.university.soa.bus.SeatClass.SeatSelection;

import org.parceler.Parcels;

public class checkbox1 extends Activity {
    RadioButton R1,R2,R3,R4;
    Button B1;
    String str_empcode;
    BookingInfo info = new BookingInfo();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            str_empcode = getIntent().getStringExtra("employee");
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));

        }
        R1 = findViewById(R.id.Opt1);
        R2 = findViewById(R.id.Opt2);
        R3 = findViewById(R.id.Opt3);
        R4= findViewById(R.id.Opt4);
        B1= findViewById(R.id.OK);
        B1.setText(R.string.OK);
        R1.setText(R.string.Opt5);
        R2.setText(R.string.Opt6);
        R3.setText(R.string.Opt7);
        R4.setText(R.string.Opt8);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R1.isChecked()) {
                    Intent Intents= new Intent(checkbox1.this, SeatSelection.class); // <----- START "BEACHES" ACTIVITY
                    Intents.putExtra("employee", str_empcode + "R1");
                    info.timing = R1.getText().toString();
                    Intents.putExtra("info", Parcels.wrap(info));
                    startActivity(Intents);
                }
                else if (R2.isChecked()) {
                    Intent Intentm = new Intent(getApplicationContext(), SeatSelection.class); // <----- START "HIll STATIONS" ACTIVITY
                    Intentm.putExtra("employee", str_empcode + "R2");
                    info.timing = R2.getText().toString();
                    Intentm.putExtra("info", Parcels.wrap(info));
                    startActivityForResult(Intentm, 0);
                }
                else if (R3.isChecked()) {
                    Intent Intentw = new Intent(getApplicationContext(), SeatSelection.class); // <----- START "WILDLIFE" ACTIVITY
                    Intentw.putExtra("employee", str_empcode + "R3");
                    info.timing = R3.getText().toString();
                    Intentw.putExtra("info", Parcels.wrap(info));
                    startActivityForResult(Intentw, 0);
                }
                else if (R4.isChecked()) {
                    Intent Intenth = new Intent(getApplicationContext(), SeatSelection.class); // <----- START "HISTORICAL MONUMENTS" ACTIVITY
                    Intenth.putExtra("employee", str_empcode + "R4");
                    info.timing = R4.getText().toString();
                    Intenth.putExtra("info", Parcels.wrap(info));
                    startActivityForResult(Intenth, 0);
                }
            }
        });

    }
}
