package com.example.eventinsti;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<UploadHelperClass> dataList;
    public EventAdapter(Context context, List<UploadHelperClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getEventImg()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getEventTitle());
        holder.recDesc.setText(dataList.get(position).getEventDsc());
        //FOr instisus name and status
        holder.recInstiUsername.setText(dataList.get(position).getUsername());
        holder.recInstiStatus.setText(dataList.get(position).getVerifyStatus());


        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getEventImg());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getEventDsc());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getEventTitle());
                intent.putExtra("Link", dataList.get(holder.getAdapterPosition()).getEventLink());
                //For two ape
                intent.putExtra("Username", dataList.get(holder.getAdapterPosition()).getUsername());
                intent.putExtra("Status", dataList.get(holder.getAdapterPosition()).getVerifyStatus());

                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<UploadHelperClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recInstiUsername, recInstiStatus;
    CardView recCard;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDesc = itemView.findViewById(R.id.recDesc);
        recTitle = itemView.findViewById(R.id.recTitle);
        recInstiUsername = itemView.findViewById(R.id.recInstiUsername);
        recInstiStatus = itemView.findViewById(R.id.recInstiStatus);
    }
}
