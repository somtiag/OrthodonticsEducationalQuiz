package com.example.android.orthodonticseducationalquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Intent intent = getIntent();

    }

    public void closeQuiz(View view) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
