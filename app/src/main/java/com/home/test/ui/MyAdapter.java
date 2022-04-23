package com.home.test.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.home.test.Applicant;
import com.home.test.Design;
import com.home.test.R;
import com.home.test.chatroom;
import com.home.test.productpreview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends FirebaseRecyclerAdapter<
        Design , MyAdapter.MyViewHolder> {
    private FirebaseDatabase database;
    public DatabaseReference myRef;
    public static Applicant a = new Applicant();
    public static Map<Integer, Bitmap> imgs = new HashMap<>();
    public MyAdapter(@NonNull FirebaseRecyclerOptions<Design> options){
        super(options);
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position, @NonNull final Design model) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


                if (model.approved.equals("yes")) {
                    holder.name.setText(model.getTitle());
                    holder.price.setText(model.getPrice());
                    holder.desc.setText(model.getDescription());
                    if(MainActivity.bitmapList.containsKey(model.title)) {
                        holder.img.setImageBitmap(MainActivity.bitmapList.get(model.title));

                    }
                    else{
                        holder.img.setImageResource(R.drawable.one);
                    }
                } else {
                    holder.card.removeAllViews();
                    holder.rel.removeView(holder.card);
                }

                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(MainActivity.bitmapList.containsKey(model.title)) {
                            imgs.put(0, MainActivity.bitmapList.get(model.title));
;                            for (int i = 1; i < 4; i++) {
                                if (MainActivity.bitmapList.containsKey(((String) (model.title + (i - 1))))) {
                                    String s = ((String) (model.title + (i - 1)));
                                    imgs.put(i, MainActivity.bitmapList.get(s));
                                }
                            }
                        }
                        a.setTitle(model.title);
                        a.setDesigner(model.designer);
                        a.setDescription(model.description);
                        a.setUser(MainActivity.user.email);
                        a.setPrice(model.price);
                        Intent i = new Intent(view.getContext(), productpreview.class);
                        i.putExtra("title",model.title);
                        i.putExtra("desc",model.description);
                        i.putExtra("designer",model.designer);
                        i.putExtra("price",model.price);
                        view.getContext().startActivity(i);
                    }
                });

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, desc;
        RelativeLayout rel;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            img =itemView.findViewById(R.id.img);
            name =itemView.findViewById(R.id.fullname);
            desc =itemView.findViewById(R.id.desc);
            price =itemView.findViewById(R.id.price);
            rel =itemView.findViewById(R.id.rell);
            card =itemView.findViewById(R.id.cardi2);

        }
    }
}
