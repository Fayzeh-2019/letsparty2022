package com.home.test.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.home.test.Applicant;
import com.home.test.ChatMessage;
import com.home.test.Message;
import com.home.test.R;
import com.home.test.chatroom;
import com.home.test.ui.dashboard.DashboardFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    private com.google.firebase.database.FirebaseDatabase database;
    public com.google.firebase.database.DatabaseReference myRef;
    public com.google.firebase.database.DatabaseReference myRef2;
    public com.google.firebase.database.DatabaseReference myRef3;

     ArrayList<String> list;
     String sender = "", mess = "";
     Date dt;
     Context context;

    public ChatAdapter(Context context , ArrayList<String> listy) {
        this.list = listy;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordercard, parent, false);
        MyViewHolder mv = new MyViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        database = com.google.firebase.database.FirebaseDatabase.getInstance();
        myRef = database.getReference("Messages");
        myRef2 = database.getReference("User");
        myRef3 = database.getReference("Designer");

        myRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {

            @Override
            public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot snapshot) {

                if(com.home.test.ui.MainActivity.designer.email != null){
                    Iterable<com.google.firebase.database.DataSnapshot> messages = snapshot.child(list.get(holder.getAdapterPosition())).getChildren();
                    for (com.google.firebase.database.DataSnapshot child : messages) {
                            holder.message.setText( child.child("message").getValue().toString());
                            Date dtt= child.child("dateTime").getValue(Date.class);
                            SimpleDateFormat sf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String d = sf.format(dtt);
                            holder.date.setText(d);
                            myRef2.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {

                                @Override
                                public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot snapshot) {


                                        Iterable<com.google.firebase.database.DataSnapshot> users = snapshot.getChildren();
                                        for (com.google.firebase.database.DataSnapshot childd : users) {
                                            if(childd.child("email").getValue().toString().equals(child.child("name").getValue().toString())) {
                                               holder.name.setText(childd.child("name").getValue().toString());
                                                holder.card.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent i = new Intent(context,chatroom.class);
                                                        i.putExtra("user",childd.child("email").getValue().toString());
                                                        context.startActivity(i);
                                                    }
                                                });
                                            }


                                        }




                                }


                                @Override
                                public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError error) {

                                }
                            });


                        }

                    }


                if(com.home.test.ui.MainActivity.user.email != null){
                    Iterable<com.google.firebase.database.DataSnapshot> messages = snapshot.child(list.get(holder.getAdapterPosition())).getChildren();
                    for (com.google.firebase.database.DataSnapshot child : messages) {
                            mess = child.getKey();
                            holder.message.setText(snapshot.child(list.get(holder.getAdapterPosition())).child(mess).child("message").getValue().toString());
                            Date dtt= child.child("dateTime").getValue(Date.class);
                            SimpleDateFormat sf= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String d = sf.format(dtt);
                            holder.date.setText(d);
                           // dt = ((Date) child.child("dateTime").getValue());
                            myRef3.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {

                                @Override
                                public void onDataChange(@androidx.annotation.NonNull com.google.firebase.database.DataSnapshot snapshot) {


                                    Iterable<com.google.firebase.database.DataSnapshot> designers = snapshot.getChildren();
                                    for (com.google.firebase.database.DataSnapshot childd : designers) {
                                        if(childd.child("email").getValue().toString().equals(child.child("name").getValue().toString())) {
                                            holder.name.setText(childd.child("name").getValue().toString());
                                            holder.card.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent i = new Intent(context,chatroom.class);
                                                    i.putExtra("user", childd.child("email").getValue().toString());
                                                    context.startActivity(i);
                                                }
                                            });
                                        }

                                    }




                                }


                                @Override
                                public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError error) {

                                }
                            });


                        }
                    if(mess.equals("")){ holder.rr.removeView(holder.card);}

                    }

                }


            @Override
            public void onCancelled(@androidx.annotation.NonNull com.google.firebase.database.DatabaseError error) {

            }

        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, message, date;
        CardView card;
        android.widget.RelativeLayout rr;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.chatName);
            message =itemView.findViewById(R.id.chatmessage);
            date =itemView.findViewById(R.id.chatdate);
            card =itemView.findViewById(R.id.cardi3);
            rr = itemView.findViewById(R.id.rela);

        }
    }

    public void onAttatchedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}