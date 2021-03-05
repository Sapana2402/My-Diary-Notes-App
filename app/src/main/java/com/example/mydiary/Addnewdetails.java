package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addnewdetails extends AppCompatActivity {
EditText msubject,mdetails;
Button mdone;
DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewdetails);
        dbref=FirebaseDatabase.getInstance().getReference().child("hii");
        msubject = findViewById(R.id.subject);
        mdetails = findViewById(R.id.details);
        mdone = findViewById(R.id.done);
        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }

            private void validate() {
                String subjectt =msubject.getText().toString();
                String detilst=mdetails.getText().toString();

                User user=new  User(subjectt,detilst);

                dbref.push().setValue(user);
                Intent pushintent = new Intent(Addnewdetails.this,MainActivity.class);
                startActivity(pushintent);

            }
        });
    }}
