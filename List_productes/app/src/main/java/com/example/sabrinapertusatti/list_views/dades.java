package com.example.sabrinapertusatti.list_views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by usuario on 17/01/2017.
 */

public class dades extends AppCompatActivity implements View.OnClickListener{

    private  long Taskid;
    private  ArticleDataSource bdd;
    Toast toast;
    EditText editarCodigo, editarDescripcion, editarPVP, editarStock;
    private double PVP;
    private String CodiArticle;
    private String Descripcio;
    private double Estoc=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        bdd = new ArticleDataSource(this);

        editarCodigo = (EditText) findViewById(R.id.eCodi);
        editarDescripcion = (EditText) findViewById(R.id.eDesc);
        editarPVP = (EditText) findViewById(R.id.ePVP);
        editarStock = (EditText) findViewById(R.id.eStock);

        // Botons Aceptar,Cancelar,Eliminar
        Button btnAgregar = (Button) findViewById(R.id.btnadd);
       // Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        //Button btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnAgregar.setOnClickListener(this);

        Taskid = this.getIntent().getExtras().getLong("id");
        if(Taskid !=-1){
            //Carregam les dades d'aquell producte, a través d'un métode
            carregarDades();

            //Bloquejar el camp del Codi perque no el pugui modificar-lo
            editarCodigo.setEnabled(false);
            //btnEliminar.setVisibility(View.VISIBLE);

        }
        //Significar que el producte existeix i mostrem el boto de eliminar.
        if(Taskid==-1){
            editarCodigo.setEnabled(true);
           // btnEliminar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardar:
                agregarProductos();
                break;
          /*  case R.id.btnCancelar:
                cancelar();
                break;
            case R.id.btnEliminar:
                eliminar();
                break;*/
        }
    }

    private void carregarDades() {

        Cursor dades = bdd.Task(Taskid);
        dades.moveToFirst();

        editarCodigo.setText(dades.getString(dades.getColumnIndex(MySqLiteHelper.column_CodiArticle)));
        editarDescripcion.setText(dades.getString(dades.getColumnIndex(MySqLiteHelper.column_Descripcio)));
        editarPVP.setText(dades.getString(dades.getColumnIndex(MySqLiteHelper.column_pvp)));
        editarStock.setText(dades.getString(dades.getColumnIndex(MySqLiteHelper.column_Estoc)));
    }

    private void agregarProductos() {
//comprobar casillas
        Toast toast ;
        TextView text;

        text = (TextView) findViewById(R.id.eCodi);
        String codi = text.getText().toString();
        if (codi.trim().equals("")) {

            toast = Toast.makeText(dades.this,"Camp obligatori.", Toast.LENGTH_LONG);
            toast.show();

            return;
        }
        text = (TextView) findViewById(R.id.eDesc);
        String descripcio = text.getText().toString();
        if (descripcio.trim().equals("")) {

            toast = Toast.makeText(dades.this,"Campobligatori", Toast.LENGTH_LONG);
            toast.show();

            return;
        }
        text = (TextView) findViewById(R.id.ePVP);
        float  PVP;
        try {
            PVP = Float.valueOf(text.getText().toString());
        }
        catch (Exception e) {
            toast = Toast.makeText(dades.this,"El camp PVP ha de ser numeric.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (PVP < 0)
        {
            toast = Toast.makeText(dades.this,"El camp PVP ha de ser positiu.", Toast.LENGTH_SHORT);
            toast.show();;
            return;
        }

        text = (TextView) findViewById(R.id.eStock);
        Integer stock ;

        try {
            stock = Integer.valueOf(text.getText().toString());
        }
        catch (Exception e) {
            toast =Toast.makeText(dades.this,"Elvalor ha de ser numeric", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        // Mirem si estem creant o estem guardant
        if (Taskid == -1) {
            Taskid = bdd.AgregarProductos(codi, descripcio,PVP,stock);
        }
        else {
           // bdd.update(Taskid,codi,descripcio,PVP,stock);

        }

        Intent i = new Intent();
        i.putExtra("id", Taskid);
        setResult(RESULT_OK, i);

        finish();
    }
     /*public dades(double PVP, String codiArticle, String descripcio, double estoc) {
        this.PVP = PVP;
        CodiArticle = codiArticle;
        Descripcio = descripcio;
        Estoc = estoc;
    }

    public void setCodiArticle (String ca){
        CodiArticle = ca;
    }

    public String getCodiArticle(){
        return CodiArticle;
    }

    //Descrp
    public void setDescripcio(String descripcio) {
        Descripcio = descripcio;
    }
    public String getDescripcio() {
        return Descripcio;
    }

    //PVP
    public void setPVP(double PVP) {

        this.PVP = PVP;
    }
    public double getPVP() {

        return PVP;
    }

    //estoc
    public void setEstoc(double estoc)
    {
        Estoc = estoc;
    }
    public double getEstoc()
    {
        return Estoc;
    }*/
}

