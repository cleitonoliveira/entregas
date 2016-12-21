package com.example.cleiton.final162.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cleiton on 30/10/2016.
 */

public class Banco extends SQLiteOpenHelper{

    public static final String TABELA = "banco";
    private static final int version = 5;

    public Banco(Context context) {
        super(context, TABELA, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table entregas(" +
                " entrega integer primary key autoincrement, " +
                " endereco text not null, " +
                " cliente text not null, " +
                " obs text, " +
                " entregue integer default 0" +
                ");";
        db.execSQL(sql);

        sql = "create table produtos(" +
                "produto integer primary key autoincrement, " +
                " nome text not null, " +
                " tipo text not null, " +
                " preco real" +
                ");";
        db.execSQL(sql);

        sql = "create table produto_entrega(" +
                " pe integer primary key autoincrement, " +
                " entrega integer not null, " +
                " produto integer not null, " +
                " qtde integer not null, " +
                " preco real not null, " +
                " foreign key(entrega) references entregas(entrega), " +
                " foreign key(produto) references produtos(produto) " +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists entregas";
        db.execSQL(query);
        query = "drop table if exists produtos";
        db.execSQL(query);
        query = "drop table if exists produto_entrega";
        db.execSQL(query);
        onCreate(db);
    }
}