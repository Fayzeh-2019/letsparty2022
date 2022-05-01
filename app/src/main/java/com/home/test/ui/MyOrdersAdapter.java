package com.home.test.ui;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.home.test.R;
import com.home.test.chatroom;
import com.home.test.ui.dashboard.DashboardFragment;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrdersAdapter extends com.firebase.ui.database.FirebaseRecyclerAdapter<
        com.home.test.Applicant, MyOrdersAdapter.MyViewHolder> {

  private com.google.firebase.database.FirebaseDatabase database;
  public com.google.firebase.database.DatabaseReference myRef;


    public MyOrdersAdapter(@NonNull com.firebase.ui.database.FirebaseRecyclerOptions<com.home.test.Applicant> options){
        super(options);
    }
    @NonNull
    @Override
    public MyOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatcard, parent, false);

      return new MyOrdersAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.MyViewHolder holder, int position, @NonNull final com.home.test.Applicant model) {
        database = com.google.firebase.database.FirebaseDatabase.getInstance();
        myRef = database.getReference();


        if(MainActivity.user.email != null){
            if(model.approved.equals("yes") && model.user.equals(MainActivity.user.email)){
                holder.delete.setColorFilter(0x00000000);
                holder.name.setText(model.title);
                holder.desc.setText(model.description);
                holder.price.setText(model.price);
                holder.date2.setText(model.date+ " " +
                        model.time);
                holder.atendees2.setText(model.atendees);
                if(MainActivity.bitmapList.containsKey(model.title))
                    holder.img.setImageBitmap(MainActivity.bitmapList.get(model.title));
                else{
                    holder.img.setImageResource(R.drawable.one);
                }
            }else {
                holder.card.removeAllViews();
                holder.rr.removeView(holder.card);
            }


        }

        if(MainActivity.designer.email != null){
            if(model.approved.equals("yes") && model.designer.equals(MainActivity.designer.email)){
                holder.name.setText(model.title);
                holder.desc.setText(model.description);
                holder.price.setText(model.price);
                holder.date2.setText(model.date+ " " +
                        model.time);
                holder.atendees2.setText(model.atendees);
                if(MainActivity.bitmapList.containsKey(model.title))
                    holder.img.setImageBitmap(MainActivity.bitmapList.get(model.title));
                else{
                    holder.img.setImageResource(R.drawable.one);
                }
            }else {
                holder.card.removeAllViews();
                holder.rr.removeView(holder.card);
            }

        }

        if(MainActivity.designer.email != null) {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DashboardFragment.adapter.getRef(holder.getAdapterPosition()).removeValue();
                }
            });
        }
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.designer.email != null){
                    Intent i = new Intent(view.getContext(), chatroom.class);
                    i.putExtra("user",model.user);
                    view.getContext().startActivity(i);
                }
                if(MainActivity.user.email != null){
                    Intent i = new Intent(view.getContext(), chatroom.class);
                    i.putExtra("user",model.designer);
                    view.getContext().startActivity(i);
                }
            }
        });


    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, desc, price, date2 ,atendees2;
        ImageView chat, delete;
        CardView card;
        android.widget.RelativeLayout rr;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.img2);
            name =itemView.findViewById(R.id.textView5);
            desc =itemView.findViewById(R.id.textView7);
            price =itemView.findViewById(R.id.textView6);
            date2 =itemView.findViewById(R.id.userdate);
            atendees2 = itemView.findViewById(R.id.useratendees);
            chat = itemView.findViewById(R.id.imageView3);
            delete = itemView.findViewById(R.id.imageView2);
            card =itemView.findViewById(R.id.cardi4);
            rr = itemView.findViewById(R.id.relative);

        }
    }
}
