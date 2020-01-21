package com.app.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText email = findViewById(R.id.tiEmailLogin);
        EditText senha = findViewById(R.id.tiSenhaLogin);
        String strEmail = email.getText().toString();
        String strSenha = senha.getText().toString();

        //auxiliares
        String expn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        boolean erro = false;

        if(strEmail.length() > 0 && strSenha.length() > 0 && strEmail.matches(expn)){
            //remover espaços em branco
            strEmail.trim();
            BancoController crud = new BancoController(getBaseContext());
            Cursor cursor = crud.fazerLogin(strEmail,strSenha);

            //se não houver no banco
            if(cursor == null || cursor.getCount() == 0){
                erro = true;
            }else {
                //se houver verifica se são iguais
                String resEmail = cursor.getString(cursor.getColumnIndex("email"));
                String resSenha = cursor.getString(cursor.getColumnIndex("senha"));

                if (strEmail.equals(resEmail) && strSenha.equals(resSenha)) {
                    Toast.makeText(getApplicationContext(), "Seja bem-vindo.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, home.class);
                    startActivity(intent);
                } else {
                    erro = true;
                }
            }
        }else{
            erro = true;
        }

        if(erro)
            Toast.makeText(getApplicationContext(), "Credenciais incorretas.", Toast.LENGTH_LONG).show();
    }

    public void recuperarSenha(View view){
        Intent intent = new Intent(this,esqueceu_a_senha.class);
        startActivity(intent);
    }

    public void novoCadastro(View view) {
        Intent intent = new Intent(this, cadastro.class);
        startActivity(intent);
    }
}
