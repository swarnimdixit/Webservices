package com.example.swarnim_d.webservices.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.swarnim_d.webservices.model.Movie;

import static android.R.attr.name;

/**
 * Created by swarnim_d on 02-12-2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "moviedbver2.db";
    public static final String TABLE_NAME = "movietbl";
    public static final String MOVIE_TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "date";
    public static final String LANGUAGE = "language";
    public static final String POPULARITY = "popularity";
    public static final String VOTEAVERAGE = "vote";

    Context mcontext;
    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
            mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+ "(title text PRIMARY KEY,overview text,date text, language text,popularity text,vote text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS MOVIETBL");
        onCreate(db);

    }
    public boolean insertMovie (String mtitle,String moverview,String mdate,String mlanguage,String mpopularity, String mvote) {

        SQLiteDatabase db = this.getWritableDatabase();
       try {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",mtitle);
        contentValues.put("overview",moverview);
        contentValues.put("date", mdate);
        contentValues.put("language", mlanguage);
        contentValues.put("popularity", mpopularity);
        contentValues.put("vote", mvote);

        db.insert(TABLE_NAME, null, contentValues);



       }
       catch (Exception e)
       {
           e.printStackTrace();
           Toast.makeText(mcontext, "exceptionn"+e, Toast.LENGTH_SHORT).show();
                  }
        return true;
    }


    public Cursor getData(String mtitle) {
        Cursor res = null;
        SQLiteDatabase db = this.getReadableDatabase();
        //res =  db.rawQuery( "select * from movietbl where title = \""+mtitle+"\"", null );
        res = db.rawQuery("SELECT * FROM movietbl WHERE title=?", new String[] {mtitle + ""});
        //Cursor res = db.query(TABLE_NAME, new String[] { OVERVIEW }, MOVIE_TITLE + "=?", new String[] { String.valueOf(MOVIE_TITLE) }, null, null, null, null);

        return res;
    }

}
