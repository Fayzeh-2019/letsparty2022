package com.home.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.home.test.ui.MainActivity;
import com.home.test.ui.MyAdapter;
import com.home.test.ui.NavigationPage;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;
import java.util.Map;

public class productpreview extends AppCompatActivity {

    SliderView slider;
    public static Map<Integer, Bitmap> images = new HashMap<>();

    TextView ti, de;
    Button book;
    static  imageSligerAdapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productpreview);


            images.put(0, MyAdapter.imgs.get(0));
            for(int i = 1; i<=4; i = i+1){
                if( MainActivity.bitmapList.containsKey(getIntent().getStringExtra("title")+i)) {
                    images.put(i, MyAdapter.imgs.get(i));
                }
            }

        //images.put(0,MainActivity.bitmap);

        String mt = getResources().getString(R.string.app_name);
        ActionBar ab = getSupportActionBar();

        ab.setTitle(Html.fromHtml("<font color='#ffffff'>"+ mt +"</font>", Html.FROM_HTML_MODE_LEGACY));

        book = findViewById(R.id.button3);
        ti = findViewById(R.id.textView8);
        de = findViewById(R.id.textView9);

        ti.setText(getIntent().getStringExtra("title").toString());
        de.setText(getIntent().getStringExtra("desc"));

        slider = findViewById(R.id.imageSlider);
        ad = new imageSligerAdapter(images);


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