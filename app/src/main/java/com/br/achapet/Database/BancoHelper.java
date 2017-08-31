package com.br.achapet.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rafael on 30/08/2017.
 */

public class BancoHelper extends SQLiteOpenHelper {
    public static final String BANCO = "bd_adocao";
    public static final String TABELA_USUARIO = "usuario";
    public static final String TABELA_ANIMAL = "animal";
    public static final int VERSAO = 1;

    public BancoHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsuario = "CREATE TABLE IF NOT EXISTS usuario (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome VARCHAR NOT NULL, " +
                "login VARCHAR NOT NULL, " +
                "senha VARCHAR NOT NULL, " +
                "endereco VARCHAR NOT NULL, " +
                "email VARCHAR NOT NULL, " +
                "telefone VARCHAR NOT NULL" +
                ");";
        String createTableAnimal = "CREATE TABLE IF NOT EXISTS animal (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome VARCHAR NOT NULL, " +
                "raca VARCHAR NOT NULL, " +
                "tipo VARCHAR NOT NULL, " +
                "descricao VARCHAR NOT NULL, " +
                "idade INTEGER NOT NULL, " +
                "porte VARCHAR NOT NULL, " +
                "foto VARCHAR NOT NULL, " +
                "adotado INTEGER(1) NOT NULL, " +
                "codigo_usuario INTEGER," +
                "FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo)" +
                ");";
        db.execSQL(createTableUsuario);
        Log.i("TESTE", "CRIOU BD USUARIO");
        db.execSQL(createTableAnimal);
        Log.i("TESTE", "CRIOU BD ANIMAL");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS animal");
        this.onCreate(db);
    }
}
