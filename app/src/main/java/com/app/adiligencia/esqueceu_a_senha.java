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

        if(strEmail.length() > 0 && strSenha.length() > 0 && strConfSenha.length() > 0){
            if(strSenha.equals(strConfSenha)) {
                BancoController crud = new BancoController(getBaseContext());
                crud.atualizarSenha(strEmail, strSenha);
                Toast.makeText(getApplicationContext(), "Se existir conta associada a esse e-mail, a senha foi atualizada.", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(), "As senhas não conferem.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "O e-mail não existe.", Toast.LENGTH_LONG).show();
        }
    }
}
