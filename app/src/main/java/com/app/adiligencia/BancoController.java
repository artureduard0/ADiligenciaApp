package com.app.adiligencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriarBanco banco;
    private static final String NOME = "nome";
    private static final String TABELA = "usuarios";
    private static final String ID = "_id";
    private static final String EMAIL = "email";
    private static final String SENHA = "senha";

    public BancoController(Context context) {
        banco = new CriarBanco(context);
    }

    public String inserirDados(String nome, String email, String senha) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(NOME, nome);
        valores.put(EMAIL, email);
        valores.put(SENHA, senha);

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro";
        } else {
            return "Sucesso";
        }
    }

    public boolean isCadastrado(String email){
        db = banco.getWritableDatabase();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+EMAIL+" = ?";
        String[] selectionArgs = new String[1];
        selectionArgs[0] = email;
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if(cursor == null || cursor.getCount() == 0){
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
            //"UPDATE "+TABELA+" SET "+SENHA+" = "+novaSenha+" WHERE "+EMAIL+" = "+email
            db.update(TABELA,args,EMAIL+" = ?",new String[]{ email });
        }
    }
}