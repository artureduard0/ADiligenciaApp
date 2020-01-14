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

    public BancoController(Context context){
        banco = new CriarBanco(context);
    }

    public String inserirDados(String nome, String email, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();

        valores.put(NOME,nome);
        valores.put(EMAIL,email);
        valores.put(SENHA,senha);

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro";
        }else{
            return "Sucesso";
        }
    }


}
