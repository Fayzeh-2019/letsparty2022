package com.home.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.home.test.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.Map;

public class imageSligerAdapter extends SliderViewAdapter<imageSligerAdapter.Holder>{

 Map<Integer, Bitmap> images;

  imageSligerAdapter( Map<Integer, Bitmap> images){this.images = images;}


  @Override
  public Holder onCreateViewHolder(ViewGroup parent) {
   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageviewslide,parent,false);
   return new Holder(view);
  }

  @Override
  public void onBindViewHolder(Holder viewHolder, int position) {

   viewHolder.image.setImageBitmap(images.get(position));
  }

  @Override
  public int getCount() {
   return images.size();
  }

  public class Holder extends SliderViewAdapter.ViewHolder{

   ImageView image ;
   public Holder(View itemView) {
    super(itemView);

    image = itemView.findViewById(R.id.iv_auto_image_slider);
   }
  }
 }