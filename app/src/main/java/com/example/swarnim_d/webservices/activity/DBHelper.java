package com.example.swarnim_d.webservices.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by swarnim_d on 02-12-2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "moviedb.db";
    public static final String MOVIE_TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "date";
    public static final String LANGUAGE = "language";
    public static final String POPULARITY = "popularity";
    public static final String VOTEAVERAGE = "vote";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table moviedb " + "(id integer primary key, title text,overview text,date text, language text,popularity text,vote text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
