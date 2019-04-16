package com.example.subrata.firstapplication.volleyExample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.subrata.firstapplication.R;
import com.example.subrata.firstapplication.recyclerViewExample.RecyclerViewActivity;

public class GithubAdapter extends  RecyclerView.Adapter<GithubAdapter.GithubViewHolder>{

    private Context context;
    private User[] data;

    public GithubAdapter(Context context, User[] data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GithubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_user_layout, viewGroup, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubViewHolder githubViewHolder, int position) {

        final User user = data[position];
        githubViewHolder.txtUser.setText(user.getLogin());
        Glide.with(githubViewHolder.imgUser.getContext()).load(user.getAvatarUrl()).into(githubViewHolder.imgUser);
        githubViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, user.getUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("url", user.getUrl());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imgUser;
        TextView txtUser;

        public GithubViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.imgUser);
            txtUser = itemView.findViewById(R.id.txtUser);
        }
    }
}
