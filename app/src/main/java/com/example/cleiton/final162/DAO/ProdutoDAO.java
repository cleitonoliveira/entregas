package com.example.cleiton.final162.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private SQLiteDatabase db;
    private Banco banco;
    private static final String TABELA = "produtos";


    public ProdutoDAO(Context context) {
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

    public void salvar(Produto produto){
        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("tipo", produto.getTipo());
        values.put("preco", produto.getPreco());

        writable();

        if (produto.id != 0){
            String where = "produto = " + produto.id;
            db.update(TABELA, values, where, null);
        }
        else {
            db.insert(TABELA, null, values);
        }
        close();
    }

    public Produto buscar(int id){
        String sql = "select * from "+ TABELA + " where produto = " + id;
        readable();
        Cursor cursor = db.rawQuery(sql,null);

        Produto produto = new Produto();

        try{
            while(cursor.moveToNext()){
                produto.setId(cursor.getInt(0));
                produto.setNome(cursor.getString(1));
                produto.setTipo(cursor.getString(2));
                produto.setPreco(cursor.getDouble(3));
            }
            close();

        }catch(SQLiteException e){
            System.out.println(e.getMessage());
        }
        return produto;
    }

    public List<Produto> listar(){

        List<Produto> produtos = new ArrayList<>();

        String sql = "Select * from " + TABELA;

        readable();
        Cursor cursor = db.rawQuery(sql,null);

        try{
            while(cursor.moveToNext()){
                Produto produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setNome(cursor.getString(1));
                produto.setTipo(cursor.getString(2));
                produto.setPreco(cursor.getDouble(3));

                produtos.add(produto);
            }
            close();

        }catch(SQLiteException e){
            System.out.println(e.getMessage());
        }

            return produtos;
        }

        public  void deletar(Produto produto){
            String args[]={produto.getId()+""};
            writable();
            db.delete(TABELA,"produto = ?",args);
            close();
        }
    }

