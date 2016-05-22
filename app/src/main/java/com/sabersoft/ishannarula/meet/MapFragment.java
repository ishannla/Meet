package com.sabersoft.ishannarula.meet;

import android.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MapFragment extends Fragment{

    LocationManager locationManager;
    LocationListener locationListener;
    static double latitude;
    static double longitude;
    int counter;

    SupportMapFragment mapFragment;
    FragmentManager manager;
    static GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        manager = getChildFragmentManager();
        mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                getLocation();
            }
        });

        return rootView;
    }

    public void getLocation() {

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                updateLocation(locationManager, locationListener);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                if (status == 2)
                    updateLocation(locationManager, locationListener);
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        updateLocation(locationManager, locationListener);

    }

    private void updateLocation(LocationManager locationManager, LocationListener locationListener) {

        Location location;

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Please grant the application location access!", Toast.LENGTH_LONG).show();
            return;
        }

        else {

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location == null) {
                    Toast toast = Toast.makeText(getActivity(), "GPS Location null", Toast.LENGTH_LONG);
                    toast.show();
                }

                else {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Toast toast = Toast.makeText(getActivity(), "G - Latitude: " + latitude + ", Longitude: " + longitude + " (" + counter + ")", Toast.LENGTH_SHORT);
                    toast.show();
                    counter++;
                }

                Log.d("MapLog", "used GPS provider");
            }

            else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (location == null) {
                    Toast toast = Toast.makeText(getActivity(), "Network Location null", Toast.LENGTH_LONG);
                    toast.show();
                }

                else {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Toast toast = Toast.makeText(getActivity(), "N - Latitude: " + latitude + ", Longitude: " + longitude  + " (" + counter + ")", Toast.LENGTH_LONG);
                    toast.show();
                    counter++;
                }
            }

            LatLng variable = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(variable).title("Current location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(variable));

        }


    }



}
