package com.home.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.home.test.ui.MainActivity;

public class AdminProfile extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference myRef;
    EditText name, email, password, city;
    static String id;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        String mt = getResources().getString(R.string.profile);
        ActionBar ab = getSupportActionBar();

        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admin");

        name = findViewById(R.id.editTextTextPersonNameAdmin);
        email = findViewById(R.id.editTextTextPersonEmailAdmin);
        password = findViewById(R.id.editTextTextPasswordAdmin);
        city = findViewById(R.id.editTextTextCityAdmin);
        update = findViewById(R.id.AdminEdit);

        if(MainActivity.designer.email == null && MainActivity.user.email == null){
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Iterable<DataSnapshot> users = snapshot.getChildren();
                    for (DataSnapshot child : users) {
                            name.setText(child.child("name").getValue().toString());
                            password.setText(child.child("password").getValue().toString());
                            email.setText(child.child("email").getValue().toString());
                            city.setText(child.child("city").getValue().toString());

                        id = child.getKey();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myRef.child(id).child("name").setValue(name.getText().toString());
                    myRef.child(id).child("email").setValue(email.getText().toString());
                    myRef.child(id).child("password").setValue(password.getText().toString());
                    myRef.child(id).child("city").setValue(city.getText().toString());

                    Toast.makeText(AdminProfile.this, "Information updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}