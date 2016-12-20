package com.example.cleiton.final162.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
    private SQLiteDatabase db;
    private Banco banco;
    private static final String TABELA = "entregas";


    public EntregaDAO(Context context) {
        banco = new Banco(context);
    }
    private void writable(){
        db = banco.getWritableDatabase();
    }
    private void readable(){
        db = banco.getReadableDatabase();
    }
    private void close(){
        db.close();
    }

    /**
     * @param entrega
     * @return o id.
     */
    public int salvar(Entrega entrega){
        int retorno;
        ContentValues values = new ContentValues();

        values.put("endereco", entrega.getEndereco());
        values.put("cliente", entrega.getCliente());
        values.put("obs", entrega.getObs());

        writable();

        if (entrega.id != 0){
            String where = "entrega = " + entrega.id;
            db.update(TABELA, values, where, null);
            retorno = entrega.id;
        }
        else {
            retorno = (int)db.insert(TABELA, null, values);
        }
        close();
        return retorno;
    }

    public Entrega buscar(int id){
        Entrega entrega;
        try{
            String sql = "select * from "+ TABELA + " where entrega = " + id;
            readable();
            Cursor cursor = db.rawQuery(sql,null);
            entrega = new Entrega();
            while(cursor.moveToNext()){
                entrega.setId(cursor.getInt(0));
                entrega.setEndereco(cursor.getString(1));
                entrega.setCliente(cursor.getString(2));
                entrega.setObs(cursor.getString(3));
                entrega.setEntregue(cursor.getInt(4) == 0 ? false : true);
            }
            close();

        }catch(SQLiteException e){
            System.out.println(e.getMessage());
            return null;
        }
        return (entrega.getId() == 0 || entrega == null ? null : entrega);
    }

    public List<Entrega> listar(){

        List<Entrega> entregas = new ArrayList<>();

        String sql = "Select * from " + TABELA;

        readable();

        try{
            Cursor cursor = db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                Entrega entrega = new Entrega();
                entrega.setId(cursor.getInt(0));
                entrega.setEndereco(cursor.getString(1));
                entrega.setCliente(cursor.getString(2));
                entrega.setObs(cursor.getString(3));
                entrega.setEntregue(cursor.getInt(4) == 0 ? false : true);

                entregas.add(entrega);
            }
            close();

        }catch(SQLiteException e){
            System.out.println(e.getMessage());
        }

            return entregas;
        }

        public  void deletar(Entrega entrega){
            String args[]={entrega.getId()+""};
            writable();
            db.delete(TABELA,"entrega = ?",args);
            close();
        }
    }

