package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class AdminDashboard extends AppCompatActivity {

    Button des,off,appl,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        String mt = getResources().getString(R.string.app_name);
        ActionBar ab = getSupportActionBar();

        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        des = findViewById(R.id.designs);
        off = findViewById(R.id.offers);
        appl = findViewById(R.id.applicants);
        profile = findViewById(R.id.Adminprofile);

        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDashboard.this, designsAdmin.class);
                i.putExtra("title",getResources().getString(R.string.designs));
                startActivity(i);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDashboard.this, offersAdmin.class);
                i.putExtra("title",getResources().getString(R.string.offers));
                startActivity(i);
            }
        });

        appl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDashboard.this, ApplicantsAdmin.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminDashboard.this, AdminProfile.class);
                startActivity(i);
            }
        });
    }
}