package com.bzyness.bzyness.Services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bzyness.bzyness.activity.LoginActivity;
import com.bzyness.bzyness.activity.MainActivity;
import com.bzyness.bzyness.models.LoginResponseModel;
import com.bzyness.bzyness.other.SessionManager;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginService extends AsyncTask <String,LoginResponseModel,LoginResponseModel> {
    private OkHttpClient client = new OkHttpClient();
    private Gson gson=new Gson();
    private SessionManager sessionManager;
    private Context context;
    public  LoginService(SessionManager sessionManager, Context context)
    {
        this.context=context;
        this.sessionManager=sessionManager;
    }


    @Override
    protected LoginResponseModel doInBackground(String... params) {
        LoginResponseModel response = null;

        try {
            String url=params[0];
            String userName=params[1];
            String password=params[2];
            String Json= getLogin(url,userName,password);
            response=gson.fromJson(Json,LoginResponseModel.class);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(LoginResponseModel loginResponseModel) {
        sessionManager.setAccessToken(loginResponseModel.getAccessToken());
        sessionManager.setExpiresIn(loginResponseModel.getExpiresIn());
        if(sessionManager.getIsLogin()){
            Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show();
            Intent i=new Intent(context, MainActivity.class);
            context.startActivity(i);
        }
    }


    private String getLogin(String Url,String UserName,String Password) throws IOException
    {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username",UserName)
                .addFormDataPart("password",Password)
                .build();
        Request request = new Request.Builder()
                .url(Url).method("POST",RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
