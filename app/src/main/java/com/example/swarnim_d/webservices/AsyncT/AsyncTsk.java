package com.example.swarnim_d.webservices.AsyncT;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.swarnim_d.webservices.activity.MainActivity;
import com.example.swarnim_d.webservices.model.Movie;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by swarnim_d on 02-12-2016.
 */

public class AsyncTsk extends android.os.AsyncTask<JSONObject,JSONObject,JSONObject > {
    MainActivity mainActivity;
    ProgressDialog pd;
    Context context = null;
    String BASE_URL;
    String API_KEY;
    JSONObject jsonResponse = null;




    public AsyncTsk(MainActivity mActivity) {
        mainActivity = mActivity;
        context =mActivity;
        pd = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.setMessage("Loading Please Wait");
        pd.show();
    }



    @Override
    protected JSONObject doInBackground(JSONObject... mData) {

        JSONObject json = mData[0];

        try {
            BASE_URL = json.getString("baseurl");
            API_KEY = json.getString("apikey");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 100000);

        HttpPost post = new HttpPost(BASE_URL+API_KEY);

        try {
            StringEntity se = new StringEntity("json="+json.toString());
            post.addHeader("content-type", "application/x-www-form-urlencoded");//or application/json ,,x-www-form-urlencoded
            post.setEntity(se);

            HttpResponse response = client.execute(post);
            String resFromServer = org.apache.http.util.EntityUtils.toString(response.getEntity());
            jsonResponse = new JSONObject(resFromServer);

            Log.d("responseAsync: ",jsonResponse.toString());
        } catch (Exception e) { e.printStackTrace();}





        return jsonResponse;
    }


    @Override
    protected void onPostExecute(JSONObject jsonObject) {

        try {
            mainActivity.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pd.dismiss();
    }


}
