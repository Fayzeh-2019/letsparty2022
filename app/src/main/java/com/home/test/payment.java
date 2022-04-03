package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.home.test.ui.MainActivity;
import com.home.test.ui.MyAdapter;

public class payment extends AppCompatActivity {

    Button confirm;
    private FirebaseDatabase database;
    public DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Applicant");
        confirm = findViewById(R.id.confirm);

        MyAdapter.a.setApproved("no");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.push().setValue(MyAdapter.a);
            }
        });
    }
}