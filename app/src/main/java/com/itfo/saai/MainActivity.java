package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText user, password;
    Button btnLog;
    ProgressBar pb;
    CheckBox mantener;
    String usu, contra;

    public void OpRes(View v)
    {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void OpAlert(View v) {
        usu = user.getText().toString();
        contra = password.getText().toString();
        if(!usu.isEmpty() && !contra.isEmpty()) {
            btnLog.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.VISIBLE);
            validar("https://codingasftp.000webhostapp.com/validar.php");
        } else
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.et_email);
        password = findViewById(R.id.etpass);
        btnLog = findViewById(R.id.btnLogIn);
        mantener = findViewById(R.id.checkBox);
        pb = findViewById(R.id.progressBar2);
    }

    private void validar(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.isEmpty()) {
                    Intent i = new Intent(getApplicationContext(), Alerta.class);
                    startActivity(i);
                    btnLog.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    btnLog.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                btnLog.setVisibility(View.VISIBLE);
                pb.setVisibility(View.INVISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("email", user.getText().toString());
                parametros.put("contrasena", password.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
