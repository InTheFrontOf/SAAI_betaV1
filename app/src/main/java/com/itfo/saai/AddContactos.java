package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContactos extends AppCompatActivity {
     private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contactos);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog(){
        DialogID dialogID = new DialogID();
        dialogID.show(getSupportFragmentManager(),"ID dialog");
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
