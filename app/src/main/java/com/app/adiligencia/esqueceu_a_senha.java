package com.app.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class esqueceu_a_senha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_a_senha);
    }

    public void recuperar(View view){
        EditText email = findViewById(R.id.tiRecEmail);
        EditText senha = findViewById(R.id.tiRecSenha);
        EditText confSenha = findViewById(R.id.tiRecConfSenha);

        String strEmail = email.getText().toString();
        String strSenha = senha.getText().toString();
        String strConfSenha = confSenha.getText().toString();

        if(strEmail.length() > 0 && strSenha.length() > 0 && strConfSenha.length() > 0 && strSenha.equals(strConfSenha)){

        }else{
            Toast.makeText(getApplicationContext(), "O e-mail não existe ou as senhas digitadas não conferem.", Toast.LENGTH_LONG).show();
        }
    }
}
