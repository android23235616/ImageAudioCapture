package com.elabs.imageaudiocapture;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SeeImages extends AppCompatActivity {
    Intent i;
    Intent i1;
    ListView list;
    int flag=0;
    List<String> ll = new ArrayList<>();
    String DirectoryName="ElabsImage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_images);
        list = (ListView)findViewById(R.id.list);
        i1 = getIntent();
        if(i1!=null){
            if(i1.getStringExtra("name2")!=null) {
                DirectoryName = i1.getStringExtra("name2");
                flag=1;
            }
        }
        getContentsOfFile();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ll.get(position);
                if(flag==0)
                    i = new Intent(SeeImages.this,ImageGallery.class);
                else
                    i= new Intent(SeeImages.this,PlayMusic.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }

    private void Display(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void getContentsOfFile() {
        Display(DirectoryName);
       File f = new File(Environment.getExternalStorageDirectory()+"/"+DirectoryName);
        if(f.isDirectory()){
            File[] fArr = f.listFiles();
            for(int index=0; index<fArr.length; index++){
                File temp = fArr[index];
                ll.add(temp.getName());
            }
            ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this,R.layout.listviewitem,R.id.text,ll);
            list.setAdapter(arrayAdapter);
        }else{
            Display("You havent saved Any Files");
        }
    }
}
