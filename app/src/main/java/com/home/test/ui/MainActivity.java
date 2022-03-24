package com.home.test.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.home.test.AdminDashboard;
import com.home.test.R;
import com.home.test.Signup;
import com.home.test.designerNavigation;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView register, title, email, password, forgot, mainTitle;
    Spinner sp;
    TextInputEditText emaillogin;
    Toolbar tb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ActionBar ab = getSupportActionBar();
        login = findViewById(R.id.login);
        register = findViewById(R.id.textView);
        title = findViewById(R.id.text1);
        email = findViewById(R.id.usernamee);
        password = findViewById(R.id.passwordd);
        forgot = findViewById(R.id.textView4);
        mainTitle = findViewById(R.id.mainTitle);
        tb = (Toolbar) findViewById(R.id.toolbar);
        sp = (Spinner) findViewById(R.id.spinner);

        emaillogin = findViewById(R.id.usernamee);
        ArrayList<String> lang = new ArrayList<>();
        lang.add("English");
        lang.add("Arabic");
        getSupportActionBar().hide();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#ffffff"));
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Arabic") ){
                    seLocale("ar");
                    register.setText(R.string.have_dont_account);
                    title.setText(R.string.login_account);
                    email.setHint(R.string.email);
                    password.setText("*******");
                    forgot.setText(R.string.forgot);
                    login.setText(R.string.login);
                    mainTitle.setText(R.string.app_name);

                }
                if(adapterView.getItemAtPosition(i).toString().equals("English") ){
                    seLocale("en");
                    register.setText(R.string.have_dont_account);
                    title.setText(R.string.login_account);
                    email.setHint(R.string.email);
                    password.setHint(R.string.passwrd);
                    forgot.setText(R.string.forgot);
                    login.setText(R.string.login);
                    mainTitle.setText(R.string.app_name);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        login.setOnClickListener(view -> {
            Intent ii = new Intent(MainActivity.this, NavigationPage.class);
            Intent i = new Intent(MainActivity.this, AdminDashboard.class);
            Intent iii = new Intent(MainActivity.this, designerNavigation.class);
                startActivity(ii);

            if(emaillogin.getText().toString().equals("d")){
                startActivity(iii);
            }
            if(emaillogin.getText().toString().equals("a")){
                startActivity(i);
            }
        });
        register.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, NavigationPage.class);
            startActivity(i);
        });


    }
    public void seLocale(String lang){
        Resources rr = getResources();
        DisplayMetrics metrics = rr.getDisplayMetrics();
        Configuration config = rr.getConfiguration();
        config.setLocale(new Locale(lang));
        rr.updateConfiguration(config,metrics);
        onConfigurationChanged(config);
    }

}