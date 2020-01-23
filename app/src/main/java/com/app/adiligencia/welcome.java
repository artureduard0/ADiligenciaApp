package com.app.adiligencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class welcome extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void nextScreen(View view){
        BancoController crud = new BancoController(getBaseContext());
        if(crud.isLogado()){
            intent = new Intent(this, MapsActivity.class);
        }else{
            intent = new Intent(this, login.class);
        }
        startActivity(intent);
    }
}
