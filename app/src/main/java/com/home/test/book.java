package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class book extends AppCompatActivity {

    Button con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ActionBar ab = getSupportActionBar();
        ab.hide();
        con = findViewById(R.id.button4);

        con.setOnClickListener(view -> {
            Intent ii = new Intent(book.this, payment.class);
            startActivity(ii);
        });
    }
}