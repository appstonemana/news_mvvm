package com.aashdit.news.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aashdit.news.model.News;
import com.aashdit.news.repo.Repository;

import java.util.ArrayList;

public class NewsViewModel extends ViewModel {

    private static final String TAG = "NewsViewModel";
    private MutableLiveData<ArrayList<News>> newsList;
    private Repository repo;
//    private  ArrayList<News> newsarticles = new ArrayList<>();
//    private Context context;

    public NewsViewModel() {
//        this.newsList = new MutableLiveData<>();
//        this.context = context;
        repo = new Repository();
    }
    public NewsViewModel(Context context) {
//        this.newsList = new MutableLiveData<>();
//        this.context = context;
        repo = new Repository();
    }


    public LiveData<ArrayList<News>> getNewsList() {
        if (newsList == null) {
            newsList = repo.getNews();
        }
        return newsList;
    }

    private void callApi() {

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(1, TimeUnit.MINUTES)
//                .readTimeout(1, TimeUnit.MINUTES)
//                .writeTimeout(1, TimeUnit.MINUTES)
//                .build();
//        AndroidNetworking.initialize(context, okHttpClient);

//        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        if (response.optString("status").equals("ok")){
//                            JSONArray ary = response.optJSONArray("articles");
//                            if (ary != null && ary.length() > 0) {
//                                for (int i = 0; i <ary.length() ; i++) {
//                                    News news = new News();
//                                    news.setTitle(ary.optJSONObject(i).optString("title"));
//                                    news.setUrl(ary.optJSONObject(i).optString("urlToImage"));
//                                    newsarticles.add(news);
//                                }
//                                newsList.setValue(newsarticles);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.i(TAG, "onError: "+anError.getErrorDetail());
//                    }
//                });
    }
}
