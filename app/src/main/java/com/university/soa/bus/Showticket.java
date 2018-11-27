package com.university.soa.bus;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.university.soa.bus.seatclass.TicketActivity;

import java.io.File;

public class Showticket extends AppCompatActivity {


    String data;
    private static final int PICKFILE_RESULT_CODE = 1;
    ImageView tour;
    TextView text, error;
    Button bookagain, close;
    Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);


        text = findViewById(R.id.textView1);
        error = findViewById(R.id.textView2);
        tour = findViewById(R.id.imageView);
        CardView cardView = findViewById(R.id.screenshot);

        SharedPreferences sharedPreferences = this.getSharedPreferences("pictures", MODE_PRIVATE);
        String image = sharedPreferences.getString("picture", "");

        if (!image.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(image, Base64.DEFAULT);
            cardView.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            tour.setImageBitmap(bitmap);
        } else {
            cardView.setVisibility(View.INVISIBLE);
            text.setVisibility(View.INVISIBLE);
            error.setVisibility(View.VISIBLE);
        }



    /*public void openFolder()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "no path");
        intent.setDataAndType(uri, "");
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


                    /** Show the selected image in Image view


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
}
