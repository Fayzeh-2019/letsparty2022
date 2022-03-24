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


public class ApplicantsAdapter extends RecyclerView.Adapter<ApplicantsAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> list;

    public ApplicantsAdapter(Context context, ArrayList<Integer> list){
        this.context = context; this.list = list;
    }
    @NonNull
    @Override
    public ApplicantsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.applicantscard, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantsAdapter.MyViewHolder holder, int position) {
        holder.name.setText("Wedding in backyard");
        holder.price.setText("50$");
        holder.dname.setText("Fayzeh Alnabulsi");
        holder.dname.setText("Danah Ibrahim");
        holder.img.setImageResource(R.drawable.one);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, dname, uname;
        Button accept, reject, chat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.applicantImage);
            name =itemView.findViewById(R.id.applicanttitle);
            dname =itemView.findViewById(R.id.applicantdesignerName);
            uname =itemView.findViewById(R.id.applicantUserName);
            price =itemView.findViewById(R.id.applicantprice);

            accept = itemView.findViewById(R.id.applicantaccept);
            reject = itemView.findViewById(R.id.applicantreject);
            chat = itemView.findViewById(R.id.applicantchat);

        }
    }
}
