package com.bzyness.bzyness;

import android.os.AsyncTask;
import android.util.Log;

import com.bzyness.bzyness.models.Category;
import com.bzyness.bzyness.models.CategoryList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by munshama on 1/29/2017.
 */

public class CategoryService extends AsyncTask<Void, List<Category>, List<Category>> {
    OkHttpClient client = new OkHttpClient();
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//            .create();

    @Override
    protected List<Category> doInBackground(Void... params) {
        List<Category> categories=null;
        try {
           String json= getCategories("http://coreenginex.azurewebsites.net/api/Category");
             categories = JsonListHelper.getList(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        if(categories!=null)
        {
            //Log.d("categories got", Arrays.toString(categories.toArray()));
        }
    }

    String getCategories(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
