package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddContactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contactos);
    }

    public void in(View v)
    {
        Intent i = new Intent(this,Alerta.class);
        startActivity(i);
    }
    public void acerca(View v)
    {
        Toast.makeText(this, "Acerca de...", Toast.LENGTH_LONG).show();
    }
    public void cont(View v)
    {
        Toast.makeText(this,"Email: itfo.0126@gmail.com",Toast.LENGTH_LONG).show();
    }
    public void p(View v) {
        Intent p = new Intent(this, PorfileActivity.class);
        startActivity(p);
    }
}
