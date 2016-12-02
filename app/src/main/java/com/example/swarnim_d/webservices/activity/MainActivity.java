package com.example.swarnim_d.webservices.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.swarnim_d.webservices.AsyncT.AsyncTsk;
import com.example.swarnim_d.webservices.R;
import com.example.swarnim_d.webservices.adapter.MoviesAdapter;
import com.example.swarnim_d.webservices.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public final static String API_KEY = "ef03354bd4d950212e2699719d24814b";
    final String BASE_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
    List<Movie> movie = new ArrayList<>();// data should come here in the movie that will be set on list view
    JSONArray resultArray;
    public void parse(JSONObject jsonResponse) throws JSONException//------------------receiving data from asyncTask
    {
        resultArray = jsonResponse.getJSONArray("results");

        for(int i=0;i<resultArray.length();i++){
            try {
                JSONObject movieObject = (JSONObject) resultArray.get(i);
                Movie movies = new Movie();
                String title = movieObject.getString("title");
                movies.setTitle(title);
                String overview = movieObject.getString("overview");
                movies.setOverview(overview);
                movies.setReleaseDate(movieObject.getString("release_date"));
                movies.setOriginalLanguage(movieObject.getString("original_language"));
                movies.setPopularity(movieObject.getDouble("popularity"));
                movies.setVoteAverage(movieObject.getDouble("vote_average"));
                movie.add(movies);

                Log.d("asdf-------------", movie.get(i).getTitle());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MoviesAdapter(movie, R.layout.list_item_movie, getApplicationContext()));
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Api Key unavailable", Toast.LENGTH_LONG).show();
            return;
        }



        try {

            JSONObject toSend = new JSONObject();
            toSend.put("apikey", API_KEY);
            toSend.put("baseurl", BASE_URL);

            AsyncTsk asyncTsk = new AsyncTsk(MainActivity.this);
            asyncTsk.execute(new JSONObject[]{toSend});

        } catch (JSONException e) {
            e.printStackTrace();
        }


        //will set data into list


    }



}



