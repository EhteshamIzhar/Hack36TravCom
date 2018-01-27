package com.android.parii.travcom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button b1,b2,b3;
    MarkerOptions p1,p2,p3,p4,p5,h1,h2,h3,h4,h5;
    public boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        int height = 100;
        int width = 100;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.index);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        b1 = (Button) findViewById(R.id.button3);
        b2 = (Button) findViewById(R.id.button5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    mMap.setIndoorEnabled(true);
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        b3 = (Button) findViewById(R.id.button4);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady)
                {
                    mMap.setIndoorEnabled(true);
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });
        p1 = new MarkerOptions().position(new LatLng(25.4965869,81.8689)).title("Sri Narayan Ashram").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        p2 = new MarkerOptions().position(new LatLng(25.4910055,81.8755)).title("Govindpur Police Chowki").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        p3 = new MarkerOptions().position(new LatLng(27.1139763,78.5595)).title("Police Station Kotwali").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        p4 = new MarkerOptions().position(new LatLng(25.4643,81.8743)).title("Chota Baghada police Station").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        p5 = new MarkerOptions().position(new LatLng(25.4292,81.8511)).title("Kydgang Police Station").icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        h1 = new MarkerOptions().position(new LatLng(25.4651,81.8243)).title("Asha Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp));
        h2 = new MarkerOptions().position(new LatLng(25.4394,81.8572)).title("Sharda Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp));
        h3 = new MarkerOptions().position(new LatLng(25.4659,81.8445)).title("Diwedi Medical and Research Centre").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp));
        h4 = new MarkerOptions().position(new LatLng(25.4660,81.8450)).title("Nirmal Nursing Home").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp));
        h5 = new MarkerOptions().position(new LatLng(25.4918,81.8675)).title("MNIT Dispensary").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    protected void sendSMS() {
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String ("8210110394"));
        smsIntent.putExtra("sms_body"  , " I NEED HELP, PLEASE TRACE MY LOCATION");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MapsActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }



    //int height = 100;
    //int width = 100;
   // BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.hospital);
    //Bitmap c=bitmapdraw.getBitmap();
    //Bitmap smallMarker1 = Bitmap.createScaledBitmap(c, width, height, false);


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
        mMap = googleMap;
        mapReady = true;

        // Add a marker in Sydney and move the camera
        LatLng current = new LatLng(25.4920, 81.8639);
      //  mMap.addMarker(new MarkerOptions().position(current).title("Marker in MNIT"));
        mMap.addMarker(p1);
        mMap.addMarker(p2);
        mMap.addMarker(p3);
        mMap.addMarker(p4);
        mMap.addMarker(p5);
        mMap.addMarker(h1);
        mMap.addMarker(h2);
        mMap.addMarker(h3);
        mMap.addMarker(h4);
        mMap.addMarker(h5);
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(current).tilt(65).zoom(12).bearing((float) 112.5).build()));
    }
}
