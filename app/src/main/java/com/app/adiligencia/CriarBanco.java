package com.app.adiligencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CriarBanco extends SQLiteOpenHelper {
    private static final String NOME = "nome";
    private static final String TABELA = "usuarios";
    private static final String ID = "_id";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";
    private static final String LOGADO = "logado";
    private static final int VERSAO = 1;

    public CriarBanco(Context context){
        super(context, "bd.db",null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //criar tabela
        String sql = "CREATE TABLE "+TABELA+"("+ID+" integer primary key autoincrement,"
                +NOME+" text,"
                +EMAIL+" text,"
                +SENHA+" text,"
                +LOGADO+" integer"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getNOME() {
        return NOME;
    }

    public String getTABELA() {
        return TABELA;
    }

    public String getID() {
        return ID;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public String getLogado(){
        return this.LOGADO;
    }

    public String getSENHA() {
        return SENHA;
    }

    public int getVERSAO() {
        return VERSAO;
    }
}
