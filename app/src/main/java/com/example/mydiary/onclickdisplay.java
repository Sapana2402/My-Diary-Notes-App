package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class onclickdisplay extends AppCompatActivity {
TextView text_setsubject,text_setdetails;
private String name,details;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickdisplay);

        text_setsubject=findViewById(R.id.text_setsubject);
        text_setdetails=findViewById(R.id.text_setdetails);


        Intent intentl=getIntent();
        String h =intentl.getStringExtra("subjectt");
        text_setsubject.setText(h);
        String hh =intentl.getStringExtra("detilst");
        text_setdetails.setText(hh);

    }


    }
