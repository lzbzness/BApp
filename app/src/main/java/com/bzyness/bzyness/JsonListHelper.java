package com.bzyness.bzyness;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonListHelper{
    public static final <T> List<T> getList(String json) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type typeOfList = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json, typeOfList);
    }
}
