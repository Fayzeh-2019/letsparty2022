package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplicantsAdmin extends AppCompatActivity {

    RecyclerView recycle;
    ApplicantsAdapter adapter;
    static DatabaseReference myRef;

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

        myRef = FirebaseDatabase.getInstance().getReference("Applicant");
        FirebaseRecyclerOptions<Applicant> list = new FirebaseRecyclerOptions.Builder<Applicant>()
                .setQuery(myRef, Applicant.class)
                .build();
        adapter = new ApplicantsAdapter( list);
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