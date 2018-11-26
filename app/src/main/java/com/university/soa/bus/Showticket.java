package com.university.soa.bus;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Showticket extends AppCompatActivity{


    String data;
    private static final int PICKFILE_RESULT_CODE = 1;
   ImageView tour;
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);


       text = findViewById(R.id.textView);
//

        openFolder();
       /* Title = findViewById(R.id.title);
        tour = findViewById(R.id.tour_name);
        dateTime = findViewById(R.id.date_time);
        seatNo = findViewById(R.id.seat_no);
        phoneNo = findViewById(R.id.phone_no);
        passNo = findViewById(R.id.pass_no);
        empName = findViewById(R.id.emp_name);
        empCode = findViewById(R.id.emp_code);
        bookAgain = findViewById(R.id.book_again);
        close = findViewById(R.id.close_logout);

        ticket = getSharedPreferences("ticket",MODE_PRIVATE);
        //Use empcode to retrive the data form firebase accordingly
        Intent intent = getIntent();
        String empcode = intent.getStringExtra("empcode");


        ref = FirebaseDatabase.getInstance().getReference().child("booked details");


        mAuth = FirebaseAuth.getInstance();

            ref.child(empcode).child("-LS0DmmbJJhIh9FTzbxq").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Journey = dataSnapshot.child("Journey Date").getValue().toString();
                    String employee_name = dataSnapshot.child("Employee name").getValue().toString();
                    String Passnum = dataSnapshot.child("Pass Number").getValue().toString();
                    String phn = dataSnapshot.child("Passenger's Phone Number").getValue().toString();
                    String route = dataSnapshot.child("Route").getValue().toString();
                    String seats = dataSnapshot.child("Seats").getValue().toString();
                    String timmings = dataSnapshot.child("Timmings").getValue().toString();

                    empName.setText(employee_name);
                    tour.setText(Journey);

                    passNo.setText(Passnum);
                    phoneNo.setText(phn);
                    dateTime.setText(timmings);
                    seatNo.setText(seats);
                    title.setText(route);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            */




    /*    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "/Android/data/com.university.soa.bus/files/Tickets/");
        intent.setDataAndType(uri, "image/png");

        startActivity(Intent.createChooser(intent, "open folder"));

      */
       /* try {
            File file = new File(Environment.getExternalStorageDirectory().getPath()
                    + "/Android/data/com.university.soa.bus/files/Tickets/");
            Uri uri1 = Uri.fromFile(file);
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            if (file.toString().contains(".png")) {
                // JPG file
                intent1.setDataAndType(uri1, "image/jpeg");
            }

            startActivity(intent1);
        } catch (Exception e) {

      }
*/

    }
    public void openFolder()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "no path");
        intent.setDataAndType(uri, "*/*");
        startActivityForResult(intent,PICKFILE_RESULT_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){

                    String FilePath = data.getData().getPath();
                    File file = new File(FilePath);
                    String FileName = data.getData().getLastPathSegment();
                    int lastPos = FilePath.length() - FileName.length();
                    String Folder = FilePath.substring(0, lastPos);

                    text.setText(new File(FilePath).getAbsolutePath());


                    /** Show the selected image in Image view */


                    if(file.exists()){

                       Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                       ImageView myImage = (ImageView) findViewById(R.id.imageView);

                       myImage.setImageBitmap(myBitmap);


                   }
                    else {
                        Toast.makeText(this, "can't be converted", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    private class filename{

        String filename_Without_Ext = "";
        String ext = "";

        filename(String file){
            int dotposition= file.lastIndexOf(".");
            filename_Without_Ext = file.substring(0,dotposition);
            ext = file.substring(dotposition + 1, file.length());
        }

        String getFilename_Without_Ext(){
            return filename_Without_Ext;
        }

        String getExt(){
            return ext;
        }
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

 /*  @Override
   protected void onActivityResult(int reqCode, int resCode, Intent data) {
       if(resCode == Activity.RESULT_OK && data != null){
           String realPath;
           // SDK < API11
           if (Build.VERSION.SDK_INT < 11)
               realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());

               // SDK >= 11 && SDK < 19
           else if (Build.VERSION.SDK_INT < 19)
               realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());

               // SDK > 19 (Android 4.4)
           else
               realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());


           setTextViews(Build.VERSION.SDK_INT, data.getData().getPath(),realPath);
       }
   }

    private void setTextViews(int sdk, String uriPath,String realPath){

       // this.text.setText("Build.VERSION.SDK_INT: "+sdk);
        //this.text.setText("URI Path: "+uriPath);
       this.text.setText("Real Path: "+realPath);

        Uri uriFromPath = Uri.fromFile(new File(realPath));

        // you have two ways to display selected image

        // ( 1 ) imageView.setImageURI(uriFromPath);

        // ( 2 ) imageView.setImageBitmap(bitmap);
      /*  Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriFromPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tour.setImageBitmap(bitmap);

        Log.d("HMKCODE", "Build.VERSION.SDK_INT:"+sdk);
        Log.d("HMKCODE", "URI Path:"+uriPath);
        Log.d("HMKCODE", "Real Path: "+realPath);
    }
*/
}
