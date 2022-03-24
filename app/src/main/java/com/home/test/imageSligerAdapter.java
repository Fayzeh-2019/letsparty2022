package com.home.test;

import android.content.Context;
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

 public class imageSligerAdapter extends SliderViewAdapter<imageSligerAdapter.Holder>{

  int[] images;

  imageSligerAdapter(int[] images){this.images = images;}


  @Override
  public Holder onCreateViewHolder(ViewGroup parent) {
   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imageviewslide,parent,false);
   return new Holder(view);
  }

  @Override
  public void onBindViewHolder(Holder viewHolder, int position) {

   viewHolder.image.setImageResource(images[position]);
  }

  @Override
  public int getCount() {
   return images.length;
  }

  public class Holder extends SliderViewAdapter.ViewHolder{

   ImageView image ;
   public Holder(View itemView) {
    super(itemView);

    image = itemView.findViewById(R.id.iv_auto_image_slider);
   }
  }
 }