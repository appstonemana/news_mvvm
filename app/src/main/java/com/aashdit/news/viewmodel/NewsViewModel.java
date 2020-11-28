package com.aashdit.news.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aashdit.news.model.News;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class NewsViewModel extends ViewModel {

    private static final String TAG = "NewsViewModel";
    public MutableLiveData<ArrayList<News>> newsList;
    private  ArrayList<News> newsarticles = new ArrayList<>();
    private Context context;

    public NewsViewModel(Context context) {
        this.newsList = new MutableLiveData<>();
        this.context = context;

        callApi();
    }

    private void callApi() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        AndroidNetworking.initialize(context, okHttpClient);

        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.optString("status").equals("ok")){
                            JSONArray ary = response.optJSONArray("articles");
                            if (ary != null && ary.length() > 0) {
                                for (int i = 0; i <ary.length() ; i++) {
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
                        Log.i(TAG, "onError: "+anError.getErrorDetail());
                    }
                });
    }
}
