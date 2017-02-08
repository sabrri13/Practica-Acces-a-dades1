package com.example.sabrinapertusatti.list_views;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPrincipal extends AppCompatActivity implements View.OnClickListener{

    private ArticleDataSource bdd;
    private long id;
    private int diferenciador = 1; //add
    private simpleCursorAdap cursorAdapter;
    private static MySqLiteHelper helper;
    private ArticleDataSource dataSource;

    private static String[] from = new String[]{
            helper.column_CodiArticle,
            helper.column_Descripcio,
            helper.column_pvp,
            helper.column_Estoc
    };

    private static  int[] to = new int[]{
            R.id.lblCodi,
            R.id.lblDescripcio,
            R.id.lblPVP,
            R.id.lblStock
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_items);

        final Button btnCanvi = (Button) findViewById(R.id.btnadd);
        btnCanvi.setOnClickListener(this);
        //no pude más
        dataSource = new ArticleDataSource(this);
        cargarDatos();
        btnCanvi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();

                Intent intencio = new Intent(ActivityPrincipal.this, dades.class);
                startActivity(intencio);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnadd:

                Bundle bundle = new Bundle();
                bundle.putLong("id",-1);

                Intent intencio = new Intent(ActivityPrincipal.this, dades.class);
                intencio.putExtras(bundle);
                startActivityForResult(intencio,diferenciador);
                break;
        }
    }

    private void cargarDatos(){
        Cursor cursorTasks = dataSource.takeData();
        ListView lista = (ListView)findViewById(R.id.lista_op);

        cursorAdapter = new simpleCursorAdap(this, R.layout.activity_principal, cursorTasks, from, to, 1);
        lista.setAdapter(cursorAdapter);
    }
}
