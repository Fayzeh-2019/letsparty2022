package com.home.test;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class ApplicantsAdapter extends FirebaseRecyclerAdapter<
        Applicant, ApplicantsAdapter.MyViewHolder> {



    private FirebaseDatabase database;
    public DatabaseReference myRef;

    public ApplicantsAdapter(@NonNull FirebaseRecyclerOptions<Applicant> options){
        super(options);
    }
    @NonNull
    @Override
    public ApplicantsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicantscard, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantsAdapter.MyViewHolder holder, int position, @NonNull final Applicant model) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> designers = snapshot.child("Designer").getChildren();
                Iterable<DataSnapshot> users = snapshot.child("User").getChildren();

                if (model.approved.equals("no")) {
                    holder.name.setText(model.getTitle());
                    holder.price.setText(model.getPrice());
                    holder.img.setImageResource(R.drawable.one);
                    holder.date.setText(model.date +" "+ model.time);
                    holder.atendees.setText(model.atendees);
                    for (DataSnapshot childd : designers) {
                        if (childd.child("email").getValue().toString().equals(model.getDesigner())) {
                            holder.dname.setText(childd.child("name").getValue().toString());
                        }
                    }
                    for (DataSnapshot child : users) {
                        if (child.child("email").getValue().toString().equals(model.getUser())) {
                            holder.uname.setText(child.child("name").getValue().toString());
                        }
                    }
                }else{
                    holder.card.removeAllViews();
                    holder.rel.removeView(holder.card);
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterable<DataSnapshot> applicant = snapshot.child("Applicant").getChildren();
                        for (DataSnapshot child : applicant) {
                            if (model.designer.equals(child.child("designer").getValue().toString())
                                    && model.title.equals(child.child("title").getValue().toString())) {
                                myRef.child("Applicant").child(child.getKey()).child("approved").setValue("yes");
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, dname, uname, date, atendees;
        Button accept, reject, chat;
        RelativeLayout rel;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.applicantImage);
            name =itemView.findViewById(R.id.applicanttitle);
            dname =itemView.findViewById(R.id.applicantdesignerName);
            uname =itemView.findViewById(R.id.applicantUserName);
            price =itemView.findViewById(R.id.applicantprice);
            date = itemView.findViewById(R.id.date);
            atendees = itemView.findViewById(R.id.atendees);
            accept = itemView.findViewById(R.id.applicantaccept);
            reject = itemView.findViewById(R.id.applicantreject);
            chat = itemView.findViewById(R.id.applicantchat);

            rel = itemView.findViewById(R.id.relapplicant);
            card = itemView.findViewById(R.id.applicantscard);

        }
    }
}
