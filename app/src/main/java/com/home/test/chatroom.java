package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

public class chatroom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        String mt = getResources().getString(R.string.title_chatroom);
        ActionBar ab = ((AppCompatActivity)this).getSupportActionBar();

        ab.hide();
    }
}