package com.home.test.ui;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.test.R;
import com.home.test.chatroom;
import com.home.test.ui.notifications.NotificationsFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> list;

    public ChatAdapter(Context context, ArrayList<Integer> list){
        this.context = context; this.list = list;
    }
    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ordercard, parent, false);

        return new ChatAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Fayzeh");
        holder.message.setText("Hi how are you?");
        holder.date.setText("2/25/2022");

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, chatroom.class);
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
        TextView name, message, date;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardi3);
            img =itemView.findViewById(R.id.img);
            name =itemView.findViewById(R.id.chatName);
            message =itemView.findViewById(R.id.chatmessage);
            date =itemView.findViewById(R.id.chatdate);

        }
    }
}