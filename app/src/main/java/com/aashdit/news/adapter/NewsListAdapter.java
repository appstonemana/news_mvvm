package com.aashdit.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aashdit.news.R;
import com.aashdit.news.model.News;
import com.androidnetworking.widget.ANImageView;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsHolder>{

    private static final String TAG = "NewsListAdapter";
    private Context context;
    private List<News> newsList;

    public NewsListAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_news,parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        News news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.image.setImageUrl(news.getUrl());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {

        ANImageView image;
        TextView title;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
