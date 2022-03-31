package com.home.test;

import android.os.Bundle;
import android.text.Html;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class offersAdmin extends AppCompatActivity {

    RecyclerView recycle;
    offersAdapter adapter;
    static DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offersadmin);

        String mt = getIntent().getStringExtra("title");
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        recycle = findViewById(R.id.offersadmin);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setHasFixedSize(true);
        myRef = FirebaseDatabase.getInstance().getReference("Design");
        FirebaseRecyclerOptions<Design> list = new FirebaseRecyclerOptions.Builder<Design>()
                .setQuery(myRef, Design.class)
                .build();

        adapter = new offersAdapter(list);
        recycle.setAdapter(adapter);
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}