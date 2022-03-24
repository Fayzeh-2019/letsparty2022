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

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> list;

    public MyOrdersAdapter(Context context, ArrayList<Integer> list){
        this.context = context; this.list = list;
    }
    @NonNull
    @Override
    public MyOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chatcard, parent, false);

      return new MyOrdersAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Order Name");
        holder.desc.setText("This is the order description to describe the order.");
        holder.price.setText("price: xxx$");
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, chatroom.class);
                context.startActivity(i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, desc, price;
        ImageView chat, delete;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.img2);
            name =itemView.findViewById(R.id.textView5);
            desc =itemView.findViewById(R.id.textView7);
            price =itemView.findViewById(R.id.textView6);
            chat = itemView.findViewById(R.id.imageView3);
            delete = itemView.findViewById(R.id.imageView2);
            card =itemView.findViewById(R.id.cardi4);

        }
    }
}
