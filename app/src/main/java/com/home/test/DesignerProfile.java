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

public class DesignerProfile extends AppCompatActivity {

    FirebaseDatabase database ;
    DatabaseReference myRef;
    EditText name, email, password, city;
    static String id;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_profile);
        String mt = getResources().getString(R.string.profile);
        ActionBar ab =  this.getSupportActionBar();
        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Designer");

        name = findViewById(R.id.editTextTextPersonNameDesigner);
        email = findViewById(R.id.editTextTextPersonEmailDesigner);
        password = findViewById(R.id.editTextTextPasswordDesigner);
        city = findViewById(R.id.editTextTextCityDesigner);
        update = findViewById(R.id.DesignerEdit);

        if(MainActivity.designer.email != null){
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Iterable<DataSnapshot> users = snapshot.getChildren();
                    for (DataSnapshot child : users) {
                        if(child.child("email").getValue().toString().equals(MainActivity.designer.email) ){
                            name.setText(child.child("name").getValue().toString());
                            password.setText(child.child("password").getValue().toString());
                            email.setText(child.child("email").getValue().toString());
                            city.setText(child.child("city").getValue().toString());
                        }

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

                    Toast.makeText(DesignerProfile.this, "Information updated successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}