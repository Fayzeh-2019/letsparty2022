package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Signup extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    Spinner spinnerSignup;
    TextInputEditText email, name, pass, city;
    Button signup;
    User u = new User();
    static String as = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("User");
        myRef2 = database.getReference("Designer");

        String mt = getResources().getString(R.string.app_name);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        ArrayList<String> lang = new ArrayList<>();
        lang.add(0,"Register as");
        lang.add("Designer");
        lang.add("User");
        email = findViewById(R.id.user);
        name= findViewById(R.id.emails);
        pass = findViewById(R.id.pass);
        city = findViewById(R.id.city);
        signup = findViewById(R.id.button);
        spinnerSignup = findViewById(R.id.spinnersignup);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSignup.setAdapter(adapter);

        spinnerSignup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Designer") ) {
                    as = "Designer";
                }
                if(adapterView.getItemAtPosition(i).toString().equals("User") ) {
                    as = "User";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                u.setEmail(name.getText().toString());
                u.setName(email.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setCity(city.getText().toString());

                if(as.equals("User")){
                    myRef.push().setValue(u);
                }else{
                    if(as.equals("Designer") ){
                        myRef2.push().setValue(u);
                    }

                }

            }
        });



    }
}