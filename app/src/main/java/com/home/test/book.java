package com.home.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.home.test.ui.MainActivity;
import com.home.test.ui.MyAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class book extends AppCompatActivity {

    Button con;
    Spinner h, m, i;
    String time = "";
    TextInputEditText atendees;
    CalendarView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);



        ActionBar ab = getSupportActionBar();
        ab.hide();
        con = findViewById(R.id.button4);
        h = findViewById(R.id.hours);
        m = findViewById(R.id.minutes);
        i = findViewById(R.id.interval);
        atendees = findViewById(R.id.editTextTextPersonName2);
        date = findViewById(R.id.calendarView);

        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i2, int i3) {

                String datee = (i + "/" + (i2 +1)+ "/" + i3);
                MyAdapter.a.setDate(datee);

            }
        });

        ArrayList<String> hour = new ArrayList<>();
        hour.add(0,"00");
        hour.add("1");
        hour.add("2");
        hour.add("3");
        hour.add("4");
        hour.add("5");
        hour.add("6");
        hour.add("7");
        hour.add("8");
        hour.add("9");
        hour.add("10");
        hour.add("11");
        hour.add("12");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, hour);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        h.setAdapter(adapter);

        ArrayList<String> minute = new ArrayList<>();
        minute.add(0,"00");
        minute.add("30");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, minute);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m.setAdapter(adapter2);

        ArrayList<String> interval = new ArrayList<>();
        interval.add(0,"AM");
        interval.add("PM");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, interval);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        i.setAdapter(adapter3);

        h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                time = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time = time +" : "+ adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        i.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                time = time +" "+ adapterView.getItemAtPosition(i).toString();
               MyAdapter.a.setTime(time);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        con.setOnClickListener(view -> {
            MyAdapter.a.atendees = atendees.getText().toString();
            Intent ii = new Intent(book.this, payment.class);
            startActivity(ii);
        });
    }
}