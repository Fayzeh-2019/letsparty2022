package com.home.test.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.home.test.AdminDashboard;
import com.home.test.Designer;
import com.home.test.R;
import com.home.test.Signup;
import com.home.test.User;
import com.home.test.designerNavigation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView register, title, email, password, forgot, mainTitle;
    Spinner sp;
    TextInputEditText emaillogin;
    Toolbar tb ;
    FirebaseDatabase database ;
    DatabaseReference myRef;
    static public Bitmap bitmap, bitmap2;
    public static Map<String, Bitmap> bitmapList = new HashMap<>();
    static public User user = new User();
    static public Designer designer = new Designer();
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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

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

            Intent i = new Intent(MainActivity.this, AdminDashboard.class);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Iterable<DataSnapshot> users = snapshot.child("User").getChildren();
                    Iterable<DataSnapshot> designers = snapshot.child("Designer").getChildren();

                    Intent iii = new Intent(MainActivity.this, designerNavigation.class);
                    Intent ii = new Intent(MainActivity.this, NavigationPage.class);

                    for (DataSnapshot child : users) {
                        if(child.child("email").getValue().toString().equals(email.getText().toString()) ){
                            user.setUser(email.getText().toString());
                            startActivity(ii);
                        }
                    }

                    for (DataSnapshot child : designers) {
                        if(child.child("email").getValue().toString().equals(email.getText().toString()) ){
                            designer.setDesigner(email.getText().toString());
                            startActivity(iii);
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            if(emaillogin.getText().toString().equals("a")){
                startActivity(i);
            }
        });
        register.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Signup.class);
            startActivity(i);
        });
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Iterable<DataSnapshot> designs = snapshot.child("Design").getChildren();
                    for (DataSnapshot child : designs) {
                        try{
                        StorageReference pathReference = storageRef.child(child.child("title").getValue().toString() + ".jpg");
                        File localfile = File.createTempFile(child.child("title").getValue().toString(), "jpg");
                        pathReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                bitmapList.put(child.child("title").getValue().toString(), bitmap);

                                if(child.child("title").getValue().toString() == "beach wedding partyy"){
                                    for(int i =0; i< 4; i++){
                                        StorageReference pathReference2 = storageRef.child(child.child("title").getValue().toString()+i + ".jpg");
                                        try {
                                            File localfile2 = File.createTempFile(child.child("title").getValue().toString()+i, "jpg");
                                            int finalI = i;
                                            pathReference2.getFile(localfile2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                                @Override
                                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                    bitmap = BitmapFactory.decodeFile(localfile2.getAbsolutePath());
                                                    bitmapList.put(child.child("title").getValue().toString()+ finalI, bitmap);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }

                        });
                        }catch(Exception e){}
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
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