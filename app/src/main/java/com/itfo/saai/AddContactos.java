package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AddContactos extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    List<Contacto> contactos;
    private static String JSON_URL = "http://starlord.hackerearth.com/studio";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contactos);

        recyclerView = findViewById(R.id.contactList);
        contactos = new ArrayList<>();
        extraerContactos();
    }

    public void openDialog(View v){
        DialogID dialogID = new DialogID();
        dialogID.show(getSupportFragmentManager(),"ID dialog");
    }

    public void in(View v) {
        Intent i = new Intent(this,Alerta.class);
        startActivity(i);
    }

    public void p(View v) {
        Intent p = new Intent(this, PorfileActivity.class);
        startActivity(p);
    }

    private void extraerContactos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject contactoObject = response.getJSONObject(i);

                        Contacto contacto = new Contacto();
                        contacto.setNombre(contactoObject.getString("song").toString() + contactoObject.getString("artists".toString()));
                        contacto.setEmail(contactoObject.getString("url".toString()));
                        contacto.setUserImg(contactoObject.getString("user_image"));
                        contactos.add(contacto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),contactos);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }
}
