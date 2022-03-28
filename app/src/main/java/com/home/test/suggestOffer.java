package com.home.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.home.test.ui.MainActivity;

public class suggestOffer extends AppCompatActivity {

    TextInputEditText title, desc, price;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_offer);

        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        ab.hide();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Design");

        title = findViewById(R.id.offerTitlee);
        desc = findViewById(R.id.offerdescr);
        price = findViewById(R.id.offerpricee);
        add = findViewById(R.id.button6);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterable<DataSnapshot> designs = snapshot.getChildren();
                        for (DataSnapshot child : designs) {
                            if (child.child("title").getValue().toString().equals(title.getText().toString())
                            && title.getText().toString() != null) {

                                myRef.child(child.getKey()).child("description").setValue(desc.getText().toString());
                                myRef.child(child.getKey()).child("price").setValue(price.getText().toString());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });
    }
}