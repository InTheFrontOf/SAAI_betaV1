package com.itfo.saai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText eTemail, eTnames, eTlast_name, eTpass;
    Button btnReg;
    ProgressBar pb;

    public void OpLog(View v)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        eTemail = findViewById(R.id.et_email);
        eTnames = findViewById(R.id.etNames);
        eTlast_name = findViewById(R.id.et_last_name);
        eTpass = findViewById(R.id.etpass);
        btnReg = findViewById(R.id.btnRegistrar);
        pb = findViewById(R.id.progressBar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnReg.setVisibility(View.INVISIBLE);
                pb.setVisibility(View.VISIBLE);
                String name = eTnames.getText().toString();
                String last_name = eTlast_name.getText().toString();
                String email = eTemail.getText().toString();
                String password = eTpass.getText().toString();
                new DescargarImagen(RegisterActivity.this).execute(name,last_name,email,password);
            }
        });


    }

    public class DescargarImagen extends AsyncTask<String, Void, String> {

        private WeakReference<Context> context;

        public DescargarImagen(Context context) {
            this.context = new WeakReference<>(context);
        }
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {
            String registro_url = "https://codingasftp.000webhostapp.com/registro.php";
            String result;

            try {
                URL url = new URL(registro_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

                String name = strings[0];
                String last_name = strings[1];
                String email = strings[2];
                String password = strings[3];

                String data = URLEncoder.encode("nombre","UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                        + "&" + URLEncoder.encode("apellido","UTF-8") + "=" + URLEncoder.encode(last_name, "UTF-8")
                        + "&" + URLEncoder.encode("email","UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                        + "&" + URLEncoder.encode("contrasena","UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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
            findViewById(R.id.btnRegistrar).setVisibility(View.VISIBLE);
            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        }

    }
}
