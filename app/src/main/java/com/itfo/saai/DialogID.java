package com.itfo.saai;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itfo.saai.AddContactos;

import com.android.volley.Request;
import com.android.volley.Response;
import com.loopj.android.http.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

public class DialogID extends AppCompatDialogFragment {
    private EditText ID;
    private AsyncHttpClient usuario;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        usuario = new AsyncHttpClient();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle("Ingresa el e-mail del Contacto").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ID.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Ingresa el e-mail", Toast.LENGTH_SHORT).show();
                        } else {
                            String email = ID.getText().toString();
                            new DialogID.DescargarImagen(getContext()).execute(email);
                        }
                    }
                });
        ID = view.findViewById(R.id.ID);
        return builder.create();
    }

    public class DescargarImagen extends AsyncTask<String, Void, String> {

        private WeakReference<Context> context;

        public DescargarImagen(Context context) {
            this.context = new WeakReference<>(context);
        }
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {
            String registro_url = "https://codingasftp.000webhostapp.com/contacto.php";
            String result;

            try {
                URL url = new URL(registro_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

                String email = strings[0];

                String data = URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


            } catch (MalformedURLException e) {
                Log.d("SAAI", "Se ha utilizado una URL con formato incorrecto.");
                result = "Se ha producido un error.";
            } catch (IOException e) {
                Log.d("SAAI", "Error inesperado, posibles problemas de conexion de red");
                result = "Se ha producido un Error, comprueba tu conexion a Internet";
            }
            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context.get(), s, Toast.LENGTH_LONG).show();
        }

    }

}
