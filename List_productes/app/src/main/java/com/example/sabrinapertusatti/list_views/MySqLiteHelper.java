package com.example.sabrinapertusatti.list_views;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by usuario on 05/02/2017.
 */

public class MySqLiteHelper extends SQLiteOpenHelper {
    public static final String _id = "_id";
    public static final String tabla = "Tabla";
    public static final String column_CodiArticle = "CodiArticle";
    public static final String column_Descripcio = "Descripcio";
    public static final String column_pvp = "pvp";
    public static final String column_Estoc = "Estoc";

    private static final String DATABASE_NAME = "tabla.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement


    public MySqLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
         String TABLE_CREATE = "create table "
                + tabla + "( " + _id + " integer primary key autoincrement, "
                + column_CodiArticle + " text not null, "
                + column_Descripcio + " text not null, "
                + column_pvp + " real not null, "
                + column_Estoc + " real default 0);";

        database.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Log.w(MySqLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("ALTER TABLE IF EXISTS " + tabla);
        onCreate(db);*/
    }
}

