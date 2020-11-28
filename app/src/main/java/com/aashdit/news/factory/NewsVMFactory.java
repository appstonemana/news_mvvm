package com.aashdit.news.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aashdit.news.model.News;
import com.aashdit.news.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class NewsVMFactory extends ViewModelProvider.NewInstanceFactory {

private Context context;
private ArrayList<News> newsList;

    public NewsVMFactory(Context context, ArrayList<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new NewsViewModel(context);
    }
}
