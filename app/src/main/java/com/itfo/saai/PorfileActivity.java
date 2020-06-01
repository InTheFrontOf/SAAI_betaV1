package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PorfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porfile);
    }
    public void Contactos (View v) {
        Intent contact = new Intent(this,AddContactos.class);
        startActivity(contact);
    }
    public void Home (View v){
        Intent home = new Intent(this,Alerta.class);
        startActivity(home);
    }


}
