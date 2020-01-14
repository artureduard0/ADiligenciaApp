package com.app.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {


        /*
        APENAS QUANDO O USUÁRIO FOR VERIFICADO E EXISTIR
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
        */

    }

    public void novoCadastro(View view) {
        Intent intent = new Intent(this, cadastro.class);
        startActivity(intent);
    }
}
