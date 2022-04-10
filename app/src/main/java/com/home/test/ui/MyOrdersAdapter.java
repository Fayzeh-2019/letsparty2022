package com.home.test.ui;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.test.R;
import com.home.test.chatroom;

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
                ((ViewGroup) holder.delete.getParent()).removeView(holder.delete);
                holder.name.setText(model.title);
                holder.desc.setText(model.description);
                holder.price.setText(model.price);
                holder.date2.setText(model.date+ " " +
                        model.time);
                holder.atendees2.setText(model.atendees);
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
            }else {
                holder.card.removeAllViews();
                holder.rr.removeView(holder.card);
            }


        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getSnapshots().remove(holder.getAdapterPosition());
            }
        });

        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), chatroom.class);
                view.getContext().startActivity(i);
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
