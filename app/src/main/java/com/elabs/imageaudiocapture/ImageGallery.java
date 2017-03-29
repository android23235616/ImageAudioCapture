package com.elabs.imageaudiocapture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class ImageGallery extends AppCompatActivity {
    Intent i;
    ImageView Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        i = getIntent();
        Image = (ImageView)findViewById(R.id.mainImage);
        getPhoto(i.getStringExtra("name"));
    }

    private void getPhoto(String name) {
        File f = new File(Environment.getExternalStorageDirectory()+"/ElabsImage/"+name);
        if(f.exists()){
            Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
            Image.setImageBitmap(bmp);
        }else{
            Display("File Not exists");
        }
    }

    private void Display(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
