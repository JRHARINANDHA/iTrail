package com.example.user.locate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by USER on 8/9/2016.
 */
public class maps extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Double latitude=0.0;
    Double longitude=0.0;
    Firebase ref;
    String lat;
    String lon;
    int flag=1;
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmaps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        settings= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }


// Attach an listener to read the data at our posts reference

        // Attach an listener to read the data at our posts reference



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
         lat=settings.getString("latitude", "11.0");

         lon=settings.getString("longitude", "11.0");
        Log.e(lon+"dichuku",lat);

            latitude=Double.valueOf(lat);
            longitude=Double.valueOf(lon);

            LatLng sydney = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));






    }

}
