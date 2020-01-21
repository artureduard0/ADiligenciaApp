package com.app.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //ação para passar a tela login
        final Intent intent = new Intent(this, login.class);

        //identificar botão do cadastro e executar o método
        //ao clica-lo
        Button btCadastrar = findViewById(R.id.bt_cadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //pegar os dados da tela
                EditText nome = findViewById(R.id.tiCadastroNome);
                EditText email = findViewById(R.id.tiCadastroEmail);
                EditText senha = findViewById(R.id.tiCampoSenha);
                EditText confSenha = findViewById(R.id.tiCampoConfSenha);

                //transforma-los em texto
                String strNome = nome.getText().toString();
                String strEmail = email.getText().toString();
                String strSenha = senha.getText().toString();
                String strConfSenha = confSenha.getText().toString();

                //caracteres para verificação do e-mail
                String expn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"

                                +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."

                                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"

                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"

                                +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                //verificar se ha campos em branco
                if(strNome.length() > 0 && strEmail.length() > 0 && strSenha.length() > 0 && strConfSenha.length() > 0) {
                    BancoController crud = new BancoController(getBaseContext());
                    strEmail.trim();

                    //verificar e-mail válido e se já existe
                    if(strEmail.matches(expn)) {
                       if(crud.isCadastrado(strEmail)) {
                            Toast.makeText(getApplicationContext(), "O e-mail já está cadastrado.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "O e-mail é inválido.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    //verificar senhas iguais
                    if (strSenha.equals(strConfSenha)) {
                        crud.inserirDados(strNome, strEmail, strSenha);
                        Toast.makeText(getApplicationContext(), "Pronto! Você já pode entrar.", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "As senhas não conferem.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Existem campos em branco.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
