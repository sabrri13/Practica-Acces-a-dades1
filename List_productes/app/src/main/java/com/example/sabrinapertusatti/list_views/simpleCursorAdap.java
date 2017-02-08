package com.example.sabrinapertusatti.list_views;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by usuario on 03/02/2017.
 */

public class simpleCursorAdap extends android.widget.SimpleCursorAdapter {
    private static final String stockNegatiu = "#FFFF0000";
    private static final String stockPositiu = "#FFFFFFFF";

    public simpleCursorAdap(Context context,
        int layout, Cursor c, String[] from, int[] to, int flags) {
         super(context, layout, c, from, to, flags);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position, convertView, parent);

        Cursor linia = (Cursor) getItem(position);
        int producteestock = linia.getInt(linia.getColumnIndexOrThrow(ArticleDataSource.stock));

        //Cambiar elcolor delstock
        if (producteestock >= 0) {
            view.setBackgroundColor(Color.parseColor(stockPositiu));
        }
        else {
            view.setBackgroundColor(Color.parseColor(stockNegatiu));
        }

        return view;
    }
}