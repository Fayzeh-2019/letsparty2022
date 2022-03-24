package com.home.test;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class designsAdminAdapter extends RecyclerView.Adapter<designsAdminAdapter.MyViewHolder> {

        Context context;
        ArrayList<Integer> list;

public designsAdminAdapter(Context context, ArrayList<Integer> list){
        this.context = context; this.list = list;
        }
@NonNull
@Override
public designsAdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.designscard, parent, false);

        return new MyViewHolder(v);
        }

@Override
public void onBindViewHolder(@NonNull designsAdminAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Wedding in backyard");
        holder.dname.setText("Fayzeh Alnabulsi");
        holder.price.setText("500$");
        holder.desc.setText("this is the description area.");
        holder.img.setImageResource(R.drawable.one);


        }

@Override
public int getItemCount() {
        return list.size();
        }

public static class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name, dname, price, desc;
    Button accept, reject, chat;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        img =itemView.findViewById(R.id.desimageSlider);
        name =itemView.findViewById(R.id.desName);
        dname =itemView.findViewById(R.id.designername);
        price =itemView.findViewById(R.id.desprice);
        desc =itemView.findViewById(R.id.desdesc);
        accept = itemView.findViewById(R.id.accept);
        reject = itemView.findViewById(R.id.reject);
        chat = itemView.findViewById(R.id.deschat);

    }
}
}
