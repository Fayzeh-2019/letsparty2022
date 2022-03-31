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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.home.test.ui.MainActivity;
import com.home.test.ui.NavigationPage;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


public class designsAdminAdapter extends FirebaseRecyclerAdapter<
       Design, designsAdminAdapter.MyViewHolder> {


        private FirebaseDatabase database;
        public DatabaseReference myRef;

        public designsAdminAdapter(@NonNull FirebaseRecyclerOptions<Design> options){
            super(options);
        }
@NonNull
@Override
public designsAdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.designscard, parent, false);

        return new MyViewHolder(v);
        }

@Override
public void onBindViewHolder(@NonNull final MyViewHolder holder,
                             int position, @NonNull final Design model) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Iterable<DataSnapshot> designers = snapshot.child("Designer").getChildren();
            Iterable<DataSnapshot> designs = snapshot.child("Design").getChildren();

                if (model.approved.equals("no") && model.offer.equals("no")) {
                   holder.name.setText(model.getTitle());
                    holder.price.setText(model.getPrice());
                    holder.desc.setText(model.getDescription());
                    holder.img.setImageResource(R.drawable.one);
                    for (DataSnapshot childd : designers) {
                        if (childd.child("email").getValue().toString().equals(model.getDesigner())) {
                            holder.dname.setText(childd.child("name").getValue().toString());
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
                   Iterable<DataSnapshot> designs = snapshot.child("Design").getChildren();
                   for (DataSnapshot child : designs) {
                       if (model.getDesigner().equals(child.child("designer").getValue().toString())
                       && model.getTitle().equals(child.child("title").getValue().toString())) {
                          myRef.child("Design").child(child.getKey()).child("approved").setValue("yes");
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
    TextView name, dname, price, desc;
    View accept, reject, chat;
    CardView card;
    RelativeLayout rel;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        img =itemView.findViewById(R.id.desimageSlider);
        name =itemView.findViewById(R.id.desName);
        dname =itemView.findViewById(R.id.designername);
        price =itemView.findViewById(R.id.desprice);
        desc =itemView.findViewById(R.id.desdesc);
        accept = itemView.findViewById(R.id.designaccept);
        reject = itemView.findViewById(R.id.designreject);
        chat = itemView.findViewById(R.id.designchat);
        card =itemView.findViewById(R.id.designscard);
        rel =itemView.findViewById(R.id.rel);
    }
}

}
