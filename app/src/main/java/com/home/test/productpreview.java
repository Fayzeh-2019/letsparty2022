package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.home.test.ui.MainActivity;
import com.home.test.ui.NavigationPage;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class productpreview extends AppCompatActivity {

    SliderView slider;
    int[] images = {R.drawable.one,
    R.drawable.two, R.drawable.three, R.drawable.four};
    TextView ti, de;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpreview);

        String mt = getResources().getString(R.string.app_name);
        ActionBar ab = getSupportActionBar();

        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        book = findViewById(R.id.button3);
        ti = findViewById(R.id.textView8);
        de = findViewById(R.id.textView9);

        ti.setText(getIntent().getStringExtra("title"));
        de.setText(getIntent().getStringExtra("desc"));

        slider = findViewById(R.id.imageSlider);
       imageSligerAdapter ad = new imageSligerAdapter(images);
       slider.setSliderAdapter(ad);
       slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
       slider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
       slider.startAutoCycle();

        book.setOnClickListener(view -> {
            Intent ii = new Intent(productpreview.this, book.class);
            startActivity(ii);
        });
    }
}