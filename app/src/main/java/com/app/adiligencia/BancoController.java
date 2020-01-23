package com.app.adiligencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private final CriarBanco banco;
    private final String NOME;
    private final String TABELA;
    private final String EMAIL;
    private final String SENHA;
    private final String LOGADO;

    public BancoController(Context context) {
        banco = new CriarBanco(context);
        this.NOME = banco.getNOME();
        this.TABELA = banco.getTABELA();
        this.EMAIL = banco.getEMAIL();
        this.SENHA = banco.getSENHA();
        this.LOGADO = banco.getLogado();
    }

    public long inserirDados(String nome, String email, String senha) {
        if(isCadastrado(email))
           return -1;

        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(NOME, nome);
        valores.put(EMAIL, email);
        valores.put(SENHA, senha);
        valores.put(LOGADO, "1");

        resultado = db.insert(TABELA, null, valores);
        db.close();

        return resultado;
    }

    public boolean isLogado(){
        db = banco.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+LOGADO+" = 1";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor == null || cursor.getCount() == 0){
            db.close();
            return false;
        }else{
            return true;
        }
    }

    public void deslogar(){
        db = banco.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(LOGADO,0);
        db.update(TABELA,args,LOGADO+" = ?",null);
        db.close();
    }

    public boolean isCadastrado(String email){
        db = banco.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+EMAIL+" = ?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = email;
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if(cursor == null || cursor.getCount() == 0){
            db.close();
            return false;
        }else{
            return true;
        }
    }

    public Cursor fazerLogin(String email, String senha){
        db = banco.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+EMAIL+" = ? AND "+SENHA+" = ?";
        String[] selectionArgs = new String[]{ email, senha };
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if(cursor != null) {
            cursor.moveToFirst();
            db.close();
            return cursor;
        }else{
            return null;
        }
    }

    public void atualizarSenha(String email, String novaSenha){
        if(isCadastrado(email)){
            db = banco.getWritableDatabase();
            ContentValues args = new ContentValues();
            args.put(SENHA,novaSenha);
            db.update(TABELA,args,EMAIL+" = ?",new String[]{ email });
            db.close();
        }
    }
}