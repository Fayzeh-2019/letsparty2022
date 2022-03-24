package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;

import com.home.test.ui.MyAdapter;

import java.util.ArrayList;

public class designsAdmin extends AppCompatActivity {

    RecyclerView recycle;
    ArrayList<Integer> list;
    designsAdminAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designs_admin);

        String mt = getIntent().getStringExtra("title");
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        recycle = findViewById(R.id.designsadmin);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        list= new ArrayList<>();
        list.add(8);
        list.add(8);
        list.add(8);

        adapter = new designsAdminAdapter(this, list);
        recycle.setAdapter(adapter);
    }
}