package com.elabs.imageaudiocapture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camera_capture extends AppCompatActivity {
    ImageView imageView;
    Button capture,files;
    final int REQUEST_CAMERA_CAPTURE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_capture);
        Initialise();

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,REQUEST_CAMERA_CAPTURE);
            }
        });
        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Camera_capture.this,SeeImages.class));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==REQUEST_CAMERA_CAPTURE&&resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            try {
                saveToFile(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Display(e.toString());
            }
        }
    }

    private void saveToFile(Bitmap bitmap) throws IOException{
        File f = new File(Environment.getExternalStorageDirectory()+"/ElabsImage");
        if(!f.isDirectory()){
            f.mkdir();
        }
        File f1 = new File(f.getAbsolutePath(),System.currentTimeMillis()+".jpeg");
        FileOutputStream fos = new FileOutputStream(f1);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        Display("File Saved");
    }

    private void Display(String s){
        Toast.makeText(Camera_capture.this,s,Toast.LENGTH_SHORT).show();
    }

    private void Initialise(){
        imageView =(ImageView)findViewById(R.id.image);
        capture = (Button)findViewById(R.id.capture);
        files = (Button)findViewById(R.id.files);
    }
}
