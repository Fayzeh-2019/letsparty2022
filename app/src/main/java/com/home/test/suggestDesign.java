package com.home.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.home.test.ui.MainActivity;

public class suggestDesign extends AppCompatActivity {

    TextInputEditText title, desc, price;
    String designer;
    static Design d = new Design();
    FirebaseDatabase database ;
    DatabaseReference myRef;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_design);
        ActionBar ab = ((AppCompatActivity) this).getSupportActionBar();
        ab.hide();


        title = findViewById(R.id.designTitlee);
        designer = MainActivity.designer.getEmail();
        desc = findViewById(R.id.designdescr);
        price = findViewById(R.id.designpricee);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Design");

        add= findViewById(R.id.button5);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setTitle(title.getText().toString());
                d.setDesigner(designer);
                d.setDescription(desc.getText().toString());
                d.setPrice(price.getText().toString());
                d.setApproved("no");
                d.setOffer("no");

                if(title.getText().toString() != null){
                    myRef.push().setValue(d);
                }
            }
        });

    }
}