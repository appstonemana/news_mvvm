package com.aashdit.news.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.aashdit.news.model.News;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Repository {

    private static final String TAG = "Repository";

    private ArrayList<News> newsarticles = new ArrayList<>();


    public MutableLiveData<ArrayList<News>> getNews() {
        final MutableLiveData<ArrayList<News>> newsList = new MutableLiveData<>();
        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.optString("status").equals("ok")) {
                            JSONArray ary = response.optJSONArray("articles");
                            if (ary != null && ary.length() > 0) {
                                for (int i = 0; i < ary.length(); i++) {
                                    News news = new News();
                                    news.setTitle(ary.optJSONObject(i).optString("title"));
                                    news.setUrl(ary.optJSONObject(i).optString("urlToImage"));
                                    newsarticles.add(news);
                                }
                                newsList.setValue(newsarticles);
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i(TAG, "onError: " + anError.getErrorDetail());
                    }
                });
        return newsList;
    }
}
