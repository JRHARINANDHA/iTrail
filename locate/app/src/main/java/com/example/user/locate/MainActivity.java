package com.example.user.locate;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import com.firebase.client.Firebase;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    public double currentLatitude;
    public double currentLongitude;
    Location location;
    private static final int REQUEST_CODE_LOCATION = 2;
    Firebase postRef;
    Double latitude;
    Double longitude;
    Firebase reflat;
    Firebase reflon;
    String lat;
    String lon;
    int flag=1;
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }
    public void getLoc() {
        //Firebase ref = new Firebase("https://bus-project-b2173.firebaseio.com/latitude");
        reflat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lat = (String) snapshot.getValue();
                Log.e(lat, lon);

                // Writing data to SharedPreferences
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("latitude", lat);
                editor.commit();

                //latitude=Double.valueOf(lat);
                //longitude=Double.valueOf(lon);
                Toast.makeText(MainActivity.this, lat + " " + lon, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //ref = new Firebase("https://bus-project-b2173.firebaseio.com/longitude");
        reflon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                lon = (String) snapshot.getValue();

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("longitude", lon);
                editor.commit();


            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public void maps(View v)
    {
        switch (v.getId())
        {
            case R.id.route1:
            reflat = new Firebase("https://bus-project-b2173.firebaseio.com/route1/latitude");
            reflon =  new Firebase("https://bus-project-b2173.firebaseio.com/route1/longitude");
                break;
            case R.id.route2:
                reflat = new Firebase("https://bus-project-b2173.firebaseio.com/route2/latitude");
                reflon =  new Firebase("https://bus-project-b2173.firebaseio.com/route2/longitude");
                break;
            case R.id.route3:
                reflat = new Firebase("https://bus-project-b2173.firebaseio.com/route3/latitude");
                reflon =  new Firebase("https://bus-project-b2173.firebaseio.com/route3/longitude");
                break;
            case R.id.route4:
                reflat = new Firebase("https://bus-project-b2173.firebaseio.com/route4/latitude");
                reflon =  new Firebase("https://bus-project-b2173.firebaseio.com/route4/longitude");
                break;
            case R.id.route5:
                reflat = new Firebase("https://bus-project-b2173.firebaseio.com/route5/latitude");
                reflon =  new Firebase("https://bus-project-b2173.firebaseio.com/route5/longitude");
                break;

        }
        getLoc();

        Intent intent = new Intent(MainActivity.this, maps.class);
        startActivity(intent);
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:8608648420"));
        startActivity(intent);
    }
}
