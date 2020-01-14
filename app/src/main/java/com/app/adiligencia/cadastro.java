package com.app.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
        //Intent intent = new Intent(this, login.class);

        Button btCadastrar = (Button)findViewById(R.id.bt_cadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                EditText senha = (EditText)findViewById(R.id.tiCampoSenha);
                EditText confSenha = (EditText)findViewById(R.id.tiCampoConfSenha);
                String strSenha = senha.getText().toString();
                String strConfSenha = confSenha.getText().toString();

                if(strSenha.equals(strConfSenha)){
                    BancoController crud = new BancoController(getBaseContext());
                    EditText nome = (EditText)findViewById(R.id.tiCadastroNome);
                    EditText email = (EditText)findViewById(R.id.tiCadastroEmail);

                    String strNome = nome.getText().toString();
                    String strEmail = email.getText().toString();

                    String resultado = crud.inserirDados(strNome,strEmail,strSenha);
                    Toast.makeText(getApplicationContext(),"Cadastro realizado.",Toast.LENGTH_LONG).show();
                    //startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"As senhas não conferem.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
