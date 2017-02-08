package com.example.sabrinapertusatti.list_views;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by usuario on 05/02/2017.
 */

public class ArticleDataSource {
    private SQLiteDatabase databaseR, databaseW;
    private MySqLiteHelper helper;

    public static final String taula_productes = "Dades";
    public static final String _id = "_id";
    public static final String CodiArticle = "CodiArticle";
    public static final String Desc = "Desc";
    public static final String PVP = "PVP";
    public static final String stock = "stock";

    public ArticleDataSource (Context context){

        helper = new MySqLiteHelper(context);
        open();
    }

    public void open() throws SQLException {
        databaseW = helper.getWritableDatabase();
        databaseR = helper.getReadableDatabase();
    }

    public void close() {
        helper.close();
        databaseR.close();
        databaseW.close();
    }

    //Recojer datos de la bdd para luego pasarlo devolviendo una array
    public Cursor takeData(){
        return databaseR.query(taula_productes, new String[]{
                _id, CodiArticle, Desc, PVP, stock
        },
                null, null,
                null, null,_id);
    }
    public Cursor Task(long id){
        return databaseR.query(taula_productes, new String[]{
                        _id, CodiArticle, Desc, PVP, stock
                },
                //Devuelve una id
                _id + "=?", new String[]{String.valueOf(_id)},
                null, null, null );
    }
    public long AgregarProductos(String codi, String descrip, double pvp, int stk){
        //Agregar datos a bdd
        ContentValues datos = new ContentValues();

        datos.put(CodiArticle, codi);
        datos.put(Desc,descrip);
        datos.put(PVP, pvp );
        datos.put(stock, stk);

        return databaseW.insert(taula_productes,null,datos);
    }

    /*
    * public void update(long id,String codi, String descrip, double pvp, int stk){
        ContentValues datos = new ContentValues();

        datos.put(CodiArticle, codi);
        datos.put(Descripcio,desc);
        datos.put(PVP, pvp );
        datos.put(Stock, stock);

        databaseW.update(taula_productes,datos, _id +"=?",new String[]{
                String.valueOf(id)});
    }

    public void delete(long id){

        databaseW.delete(taula_productes, _id + "=?", new String[]{String.valueOf(id)});
    }
    * */
    //web 3.3CommentsDataSource
}
