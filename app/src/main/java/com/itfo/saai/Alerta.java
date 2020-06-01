package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Alerta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);
    }

    public void crrar(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void contac(View v)
    {
        Intent a = new Intent(this, AddContactos.class);
        startActivity(a);
    }
    public void ini(View v)
    {
        Intent j = new Intent(this, Alerta.class);
        startActivity(j);
    }
}
