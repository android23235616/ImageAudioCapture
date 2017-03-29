package com.elabs.imageaudiocapture;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class AudioCapture extends AppCompatActivity {

    int checker=0;
    MediaRecorder mediaRecorder;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_capture);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button b = (Button)findViewById(R.id.button);

        //Creating A FILE to save the contents of mediaRecorder

         f = new File(Environment.getExternalStorageDirectory()+"/ElabsAudio");

        if(!f.isDirectory())
            f.mkdir();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker++;
                if(checker%2==1){
                    b.setText("Stop Audio Capture");
                    MediaRecorderReady();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Display(e.toString());
                    }

                }else{
                    b.setText("Start Audio Capture");
                    if(mediaRecorder!=null)
                        mediaRecorder.reset();
                }
            }
        });
        final Button getFiles = (Button)findViewById(R.id.files);
        getFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaRecorder!=null)
                    mediaRecorder.reset();
                b.setText("Start Audio Capture");
                checker++;
                final Intent intent  = new Intent(AudioCapture.this,SeeImages.class);
                intent.putExtra("name2","ElabsAudio");
                startActivity(intent);
            }
        });
    }

    private void MediaRecorderReady(){
        mediaRecorder = new MediaRecorder();
        //setting up media recorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        String absolutePath = f.getAbsolutePath()+"/"+System.currentTimeMillis()+".3gp";
        mediaRecorder.setOutputFile(absolutePath);
    }

    private void Display(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}
