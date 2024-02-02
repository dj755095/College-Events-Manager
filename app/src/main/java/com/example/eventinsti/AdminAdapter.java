package com.example.eventinsti;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.myViewHolder> {

    Context context;

    public AdminAdapter(Context context, ArrayList<HelperClass2> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<HelperClass2> list;

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        HelperClass2 user = list.get(position);
        holder.InstituteName.setText(user.getUsername());
        holder.VerificationStatus.setText(user.getVerifyStats());

        holder.recCardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(context, InstiDetail.class);
                inte.putExtra("username",holder.InstituteName.getText().toString());
                context.startActivity(inte);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView InstituteName, VerificationStatus;
        CardView recCardAdmin;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            InstituteName = itemView.findViewById(R.id.instiNameItem);
            VerificationStatus = itemView.findViewById(R.id.verifyStatsItem);
            recCardAdmin = itemView.findViewById(R.id.recCardAdmin);
        }
    }
}
