package com.itfo.saai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Alerta extends AppCompatActivity {
    private LocationManager ubicacion;
    TextView Coordenadas;
    private ImageView AlertView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);
        localizacion();
        registrarLocalizacion();
        AlertView = findViewById(R.id.AlertView);
        AlertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Alerta.this,MapsActivity.class) ;
                startActivity(intent);
            }
        });
       
    }

    private void localizacion(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,}, 1);
        }
        Coordenadas = findViewById(R.id.TvUbicacion);
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (ubicacion != null) {
            Log.d("Latitud", String.valueOf(location.getLatitude()));
            Log.d("Longitud", String.valueOf(location.getLongitude()));
            //Coordenadas.setText(String.valueOf(" " + location.getLatitude() + " " + location.getLongitude()));
        }
    }

    private void registrarLocalizacion() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new milocalizacionListener());
    }
    private class milocalizacionListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
           // String lat = "" + location.getLatitude() + "" + location.getLongitude();
            Geocoder geocoder = new Geocoder(getApplicationContext(),Locale.getDefault());
            try {
                List<Address> direc = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                Coordenadas.setText(direc.get(0).getAddressLine(0) );
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
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
    public void porfile(View v){
        Intent porfile = new Intent(this,PorfileActivity.class);
        startActivity(porfile);
    }
}
