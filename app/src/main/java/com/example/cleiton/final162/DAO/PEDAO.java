package com.example.cleiton.final162.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

public class PEDAO {
    private SQLiteDatabase db;
    private Banco banco;
    private static final String TABELA = "produto_entrega";


    public PEDAO(Context context) {
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

    public void salvar(PE pe){
        ContentValues values = new ContentValues();

        values.put("cliente", pe.getCliente());
        values.put("produto", pe.getProduto());
        values.put("qtde", pe.getQtde());
        values.put("preco", pe.getPreco());

        writable();

        if (pe.id != 0){
            String where = "pe = " + pe.id;
            db.update(TABELA, values, where, null);
        }
        else {
            db.insert(TABELA, null, values);
        }
        close();
    }

    public PE buscar(int id){
        String sql = "select * from "+ TABELA + " where id = " + id;
        readable();
        Cursor cursor = db.rawQuery(sql,null);
        close();

        PE pe = new PE();

        try{
            while(cursor.moveToNext()){
                pe.setId(cursor.getInt(0));
                pe.setCliente(cursor.getInt(1));
                pe.setProduto(cursor.getInt(2));
                pe.setQtde(cursor.getInt(3));
                pe.setPreco(cursor.getDouble(4));
            }
        }catch(SQLiteException e){
            System.out.println(e.getMessage());
        }
        return pe;
    }


    /**
     * Busca a lista de produtos registrados para a entrega
     * @param id
     * @return List<PE>
     */
    public List<PE> listar(int id){
        String sql = "select * from "+ TABELA + " where produto = " + id;
        readable();
        Cursor cursor = db.rawQuery(sql,null);
        List<PE> pes = new ArrayList<>();

        try{
            while(cursor.moveToNext()){
                PE pe = new PE();
                pe.setId(cursor.getInt(0));
                pe.setCliente(cursor.getInt(1));
                pe.setProduto(cursor.getInt(2));
                pe.setQtde(cursor.getInt(3));
                pe.setPreco(cursor.getDouble(4));

                pes.add(pe);
            }
            close();
        }catch(SQLiteException e){
            System.out.println(e.getMessage());
        }
            return pes;
        }

        public  void deletar(PE pe){
            String args[]={pe.getId()+""};
            writable();
            db.delete(TABELA,"pe = ?",args);
            close();
        }
    }