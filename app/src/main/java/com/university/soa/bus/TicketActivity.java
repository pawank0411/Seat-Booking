package com.university.soa.bus.SeatClass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.university.soa.bus.BookingInfo;
import com.university.soa.bus.MainActivity;
import com.university.soa.bus.R;
import com.university.soa.bus.book;

import org.parceler.Parcels;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class TicketActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, tour, dateTime, seatNo, phoneNo, passNo, empName, empCode;
    private Button bookAgain, close,save;
    private ImageView imageView;
    private BookingInfo info = new BookingInfo();
     int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        title = findViewById(R.id.title);
        tour = findViewById(R.id.tour_name);
        dateTime = findViewById(R.id.date_time);
        seatNo = findViewById(R.id.seat_no);
        phoneNo = findViewById(R.id.phone_no);
        passNo = findViewById(R.id.pass_no);
        empName = findViewById(R.id.emp_name);
        empCode = findViewById(R.id.emp_code);
        bookAgain = findViewById(R.id.book_again);
        close = findViewById(R.id.close_logout);
        imageView = findViewById(R.id.imageView4);

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final ConstraintLayout constraintLayout = findViewById(R.id.ticket);

                constraintLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap pic = takeScreenshot(constraintLayout);
                        try{
                            if(pic!=null){
                                saveScreenshot(pic);
                                Toast.makeText(TicketActivity.this, "Ticket has been saved", Toast.LENGTH_SHORT).show();

                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            }
        });


        info = new BookingInfo();
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("info")) {
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));
        } else if (savedInstanceState != null && savedInstanceState.getParcelable("info") != null) {
            info = Parcels.unwrap(savedInstanceState.getParcelable("info"));
        }

        bookAgain.setOnClickListener(this);
        close.setOnClickListener(this);
        assert info != null;
        setViews(info);
    }

    private Bitmap takeScreenshot(View v){
        Bitmap screenShot = null;

        try{
            int width = v.getMeasuredWidth();
            int height = v.getMeasuredHeight();

            screenShot = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

            Canvas c = new Canvas(screenShot);
            v.draw(c);



        }catch (Exception e){
            e.printStackTrace();
        }
        return screenShot;
    }

    private  void saveScreenshot(Bitmap bm){

        ByteArrayOutputStream b;
        File file;
        try {
            // Creates a file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.

            b = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,40,b);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
            Date now = new Date();
            String fileName = formatter.format(now)+" UTCL Bus Ticket.png";
            File testFile = new File(this.getExternalFilesDir("/Tickets/"),fileName);
            if (!testFile.exists())
                testFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(testFile);
            fos.write(b.toByteArray());
            fos.close();


            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{testFile.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String path, Uri uri) {

                    Log.i("MediaScanner", "Scanned " + path + ":");
                    Log.i("MediaScanner", "-> uri=" + uri);
                }
                });

        } catch (IOException e) {
                    e.printStackTrace();
           }
    }



    private void setViews(BookingInfo info) {
        if (info.seats.size() == 1) {
            title.setText(String.format(getString(R.string.booked_string), info.seats.size(), ""));
        } else {
            title.setText(String.format(getString(R.string.booked_string), info.seats.size(), "s"));
        }
        tour.setText(info.tour_name);
        dateTime.setText(String.valueOf(info.date + "    " + info.timing));
        empName.setText(String.format(getString(R.string.employee_name_string), info.emp_name));
        empCode.setText(String.format(getString(R.string.employee_code_string), info.emp_code));
        phoneNo.setText(String.format(getString(R.string.phone_number_string), info.phoneNo));
        if (TextUtils.isEmpty(info.passNo))
            passNo.setVisibility(View.GONE);
        else {
            passNo.setText(String.format(getString(R.string.pass_number_string), info.passNo));
            passNo.setVisibility(View.VISIBLE);
        }
        seatNo.setText(String.format(getString(R.string.seat_number_string),
                printSeats(info.seats)));
    }

    private String printSeats(List<Integer> seats) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < seats.size(); i++) {
            if (i == seats.size() - 1) {
                result.append(seats.get(i));
            } else {
                result.append(seats.get(i));
                result.append(", ");
            }
        }

        return result.toString();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.book_again:
                Intent intent=new Intent(TicketActivity.this,book.class);
                startActivity(intent);
                break;
            case R.id.close_logout:
                Intent intent1=new Intent(TicketActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("info", Parcels.wrap(info));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        info = Parcels.unwrap(savedInstanceState.getParcelable("info"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
