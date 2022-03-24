package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;

import java.util.ArrayList;

public class ApplicantsAdmin extends AppCompatActivity {

    RecyclerView recycle;
    ArrayList<Integer> list;
    ApplicantsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicants_admin);

        String mt =  getResources().getString(R.string.applicants);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        recycle = findViewById(R.id.applicantsAdmin);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        list= new ArrayList<>();
        list.add(8);
        list.add(8);
        list.add(8);

        adapter = new ApplicantsAdapter(this, list);
        recycle.setAdapter(adapter);
    }
}