package com.example.subrata.firstapplication.GetOsRetrofit;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.subrata.firstapplication.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Model> os;
    private Context context;

    public RecyclerAdapter(List<Model> os, Context context)
    {
        this.os = os;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raw_os, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.textName.setText(os.get(i).getName());
            myViewHolder.txtReleaseyear.setText("Release: "+os.get(i).getReleaseYear());
             Glide.with(context).load(os.get(i).getImagePath()).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return os.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        TextView textName, txtReleaseyear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            textName = itemView.findViewById(R.id.name);
            txtReleaseyear = itemView.findViewById(R.id.date);
        }
    }
}
