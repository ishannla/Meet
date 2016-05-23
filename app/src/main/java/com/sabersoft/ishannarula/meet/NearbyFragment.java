package com.sabersoft.ishannarula.meet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;


public class NearbyFragment extends Fragment {

    TextView locationText;
    Button friendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_nearby, container, false);

        locationText = (TextView) rootview.findViewById(R.id.locationText);
        friendButton = (Button) rootview.findViewById(R.id.friendButton);

        Firebase.setAndroidContext(getActivity());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        final String id = auth.getCurrentUser().getUid();

        Firebase userLocation = new Firebase("https://meet-2332f.firebaseio.com/locations").child(id);
        Toast.makeText(getActivity(), id, Toast.LENGTH_LONG).show();

        userLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double lat = (Double) dataSnapshot.child("latitude").getValue();
                Double lon = (Double) dataSnapshot.child("longitude").getValue();
                locationText.setText(lat + ", " + lon);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getActivity(), "An error has occured.", Toast.LENGTH_LONG).show();
            }
        });

        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FriendsActivity.class);
                startActivity(intent);
            }
        });

        return rootview;
    }

}
