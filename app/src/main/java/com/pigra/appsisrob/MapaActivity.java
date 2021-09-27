package com.pigra.appsisrob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pigra.appsisrob.entidades.Almacen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    float latitud, longitud;
    String titulo;

    String tipo;
    List<Almacen> lstAlmacen = new ArrayList<>();

    private Map<Marker, Almacen> markersMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        recuperarDatos();

        if(tipo.equals("FALLA")) {
            LatLng marcador = new LatLng(latitud, longitud);
            mMap.addMarker(new MarkerOptions().position(marcador).title(titulo));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marcador, 16));
        }
        if(tipo.equals("REPUESTO")) {
            obtenerAlmacenes(googleMap);
        }
    }

    private void obtenerAlmacenes(GoogleMap googleMap) {
        String url = "http://pruebaupc.atwebpages.com/index.php/almacenes";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arreglo = new JSONArray( response );
                    lstAlmacen.clear();
                    for(int i=0; i<arreglo.length();i++){
                        JSONObject objeto = arreglo.getJSONObject(i);
                        Almacen almacen= new Almacen();
                        almacen.setIdAlmacen(objeto.getInt("IDALMACEN"));
                        almacen.setNombre(objeto.getString("NOMBRE"));
                        almacen.setLatitud(Float.parseFloat( objeto.getString("LATITUD")));
                        almacen.setLongitud(Float.parseFloat( objeto.getString("LONGITUD")));
                        almacen.setStock( objeto.getInt("STOCK"));

                        lstAlmacen.add(almacen);
                    }
                    seteaPuntos(googleMap);
                }
                catch (JSONException e)
                {
                    Log.d("==>",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("==>", error.getMessage());
            }
        });
        RequestQueue cola = Volley.newRequestQueue(this);
        cola.add(peticion);
    }

    private void seteaPuntos(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng punto = null;
        for(int i =0; i< lstAlmacen.size();i++ ){
            Almacen ent = lstAlmacen.get(i);
            punto = new LatLng( ent.getLatitud(), ent.getLongitud());

            Marker marca = mMap.addMarker(new MarkerOptions()
                    .position(punto)
                    .title( ent.getNombre())
            );
            markersMap.put(marca, ent);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(punto, 17));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                Almacen almData = markersMap.get(marker);
                Intent intent = new Intent();
                intent.putExtra("nombreAlmacen", almData.getNombre());
                intent.putExtra("stockAlmacen", almData.getStock()+"" );
                setResult(RESULT_OK, intent);
                finish();
                return false;
            }
        });
    }

    private void recuperarDatos(){
        tipo ="FALLA";
        if (getIntent().hasExtra("tipoMapa"))
            tipo = getIntent().getStringExtra("tipoMapa");
        else {
            latitud = Float.parseFloat(getIntent().getStringExtra("latitud"));
            longitud = Float.parseFloat(getIntent().getStringExtra("longitud"));
            titulo = getIntent().getStringExtra("titulo");
        }
    }

}