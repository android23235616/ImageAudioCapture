package com.elabs.imageaudiocapture;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

public class PlayMusic extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        final Intent i = getIntent();
        String nameFile = i.getStringExtra("name");
        String path = Environment.getExternalStorageDirectory()+"/ElabsAudio/"+nameFile;
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Display(e.toString());
        }
    }
    private void Display(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        mediaPlayer.reset();
        finish();
    }
}
