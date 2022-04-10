package com.home.test;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class Designer {
    public String email;
    String name;
    String password;
    String city;
    FirebaseDatabase database ;
    DatabaseReference myRef;

    public Designer(){
        email = null;
        name= null;
        password = null;
        city= null;
    }

    public Designer(String email, String name, String password, String city){
        this.email = email; this.name = name; this.password = password;
        this.city = city;
    }

    public void setDesigner(String mail){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> designers = snapshot.child("Designer").getChildren();

                for (DataSnapshot child : designers) {
                    if(child.child("email").getValue().toString().equals(mail) ){
                        email = child.child("email").getValue().toString();
                        name = child.child("name").getValue().toString();
                        password = child.child("password").getValue().toString();
                        city = child.child("city").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
