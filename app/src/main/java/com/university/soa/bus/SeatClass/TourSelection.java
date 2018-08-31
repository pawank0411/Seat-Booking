package com.university.soa.bus.SeatClass;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.university.soa.bus.R;
import com.university.soa.bus.RadioButton.checkbox;
import com.university.soa.bus.RadioButton.checkbox1;

public class TourSelection extends AppCompatActivity {
    Button mtour1,mtour2,mtour3,mtour4,mtour5,mtour6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtour1 = (Button) findViewById(R.id.Tour1);
        mtour2 =(Button)findViewById(R.id.Tour2);
        mtour3 = (Button)findViewById(R.id.Tour3);
        mtour4 = (Button)findViewById(R.id.Tour4);
        mtour5 = (Button)findViewById(R.id.Tour5);
        mtour6 = (Button)findViewById(R.id.Tour6);
        mtour1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TourSelection.this,
                        checkbox.class);
                startActivity(myIntent);
            }
        });
        mtour2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TourSelection.this,
                        checkbox1.class);
                startActivity(myIntent);
            }
        });
        mtour3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection.class);
                startActivity(myIntent);
            }
        });
        mtour4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection1.class);
                startActivity(myIntent);
            }
        });
        mtour5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection2.class);
                startActivity(myIntent);
            }
        });
        mtour6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(TourSelection.this,
                        SeatSelection3.class);
                startActivity(myIntent);
            }
        });

    }


}