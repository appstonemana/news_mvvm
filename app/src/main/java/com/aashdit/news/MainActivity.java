package com.aashdit.news;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aashdit.news.adapter.NewsListAdapter;
import com.aashdit.news.factory.NewsVMFactory;
import com.aashdit.news.model.News;
import com.aashdit.news.viewmodel.NewsViewModel;
import com.androidnetworking.AndroidNetworking;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    NewsViewModel viewModel;
    NewsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidNetworking.initialize(getApplicationContext());

//        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
//        LiveData<ArrayList<News>> newsLiveData = viewModel.newsList;//java.lang.RuntimeException: Cannot create an instance of class com.aashdit.news.viewmodel.NewsViewModel
        viewModel = ViewModelProviders.of(this, new NewsVMFactory(this, new ArrayList<News>())).get(NewsViewModel.class);
        mRecyclerView = findViewById(R.id.rv_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.newsList.observe(this, new Observer<ArrayList<News>>() {
            @Override
            public void onChanged(ArrayList<News> news) {
                adapter = new NewsListAdapter(MainActivity.this, news);
                mRecyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "" + news.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}