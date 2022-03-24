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
import com.home.test.productpreview;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> list;

    public MyAdapter(Context context, ArrayList<Integer> list){
        this.context = context; this.list = list;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Fayzeh");
        holder.price.setText("50$");
        holder.desc.setText("this is the description area.");
        holder.img.setImageResource(R.drawable.one);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, productpreview.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.img);
            name =itemView.findViewById(R.id.fullname);
            desc =itemView.findViewById(R.id.desc);
            price =itemView.findViewById(R.id.price);

        }
    }
}
