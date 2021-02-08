package com.travmedia.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.travmedia.R;

import java.util.Objects;

public class NavigatorActivity extends AppCompatActivity implements OnMapReadyCallback {
    BottomNavigationView bottomNavigationView;
    GoogleMap apps_map;
    EditText navigateTo;
    ImageView submit;
    Location currentLocation, destination; // TODO: 1, 2
    FusedLocationProviderClient fusedLocationProviderClient;
    public SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.apps_map);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Travmedia navigation map");
        if(smf != null)
            smf.getMapAsync(this);
        navigateTo = findViewById(R.id.navigate_to);
        submit = findViewById(R.id.btn_navigate);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationStr = navigateTo.getText().toString();
                if (locationStr.length() > 1){
                    // Look for location and set it as destination on the map
                    // TODO 1: Make it work dynamically
                    apps_map.addMarker(new MarkerOptions()
                                    .position(new LatLng(29.9730, 34.7925))
                                    .title("Destination - " + locationStr)
                                    .icon(BitmapDescriptorFactory.defaultMarker(195))
                            );
                }
                else{
                    // For debugging
                    Log.d("System error: ", "Location string is too short");
                }
            }
        });
        bottomNavigationView = findViewById(R.id.navigation_menu);
        bottomNavigationView.setSelectedItemId(R.id.navigation_activity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Navigation between this page to other connected-user session pages
                if (item.getItemId() == R.id.home_activity) {
                    startActivity(new Intent(NavigatorActivity.this, HomeActivity.class));
                    finish();
                    return true;
                }

                else if (item.getItemId() == R.id.upload_activity) {
                        startActivity(new Intent(NavigatorActivity.this, UploadPostActivity.class));
                        finish();
                        return true;
                }

                // If selected the current page (HomeActivity)
                else return item.getItemId() == R.id.navigation_activity;
            }
        });
        // findMyLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // --- Current location setting ---
        // TODO 2: Implement the location getting and marker dynamic set
        apps_map = googleMap;
    }

    /*
    /**
     * Function for finding the current location

    public void findMyLocation(){
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            String[] requests = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, requests, 101);
        }
        else {
            Task<Location> getLast = fusedLocationProviderClient.getLastLocation();
            getLast.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        currentLocation = location;
                        smf.getMapAsync(NavigatorActivity.this);
                        LatLng myLocationLatLng = new LatLng(
                                currentLocation.getLatitude(),
                                currentLocation.getLongitude()
                        );
                        apps_map.addMarker(new MarkerOptions()
                                .position(myLocationLatLng))
                                .setTitle("Current location - " + myLocationLatLng
                        );
                    }
                }
            });
        }
    }*/
}
