package edu.paraicmcdonagh.discoverypage;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    GoogleMap mMap;
    SearchView searchView;
    SupportMapFragment mapFragment;
    Spinner spType;
    Button btnPage1;
    Button btnPage2;
    Button btnPage3;
    Button btnPage4;
    Button viewClubs;
    ArrayList arrayList=new ArrayList<>();
    LatLng sligoMMA = new LatLng(54.2768489886374, -8.49854217874541 );
    LatLng sligoBC = new LatLng(54.2763679, -8.4850367 );
    LatLng sligoGym = new LatLng(54.268991, -8.4669556 );
    LatLng sligoCollegeGym = new LatLng(54.2786689, -8.4623746 );
    LatLng sligoKarate = new LatLng(54.2694084, -8.4845797 );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        //assign variables
        spType = findViewById(R.id.sp_type);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        btnPage1 = findViewById(R.id.btnSMS);
        btnPage2 = findViewById(R.id.btnSMSLog);
        btnPage3 = findViewById(R.id.btnBMI);
        btnPage4 = findViewById(R.id.btnProfile);
        viewClubs = findViewById(R.id.btnViewClubs);

        searchView = findViewById(R.id.sv_location);
        arrayList.add(sligoMMA);
        arrayList.add(sligoBC);
        arrayList.add(sligoGym);
        arrayList.add(sligoCollegeGym);
        arrayList.add(sligoKarate);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                    //initialize array of place type
                    String[] placeTypeList = {"boxing_clubs", "mma_clubs", "karate_clubs", "mixed_martial_arts_clubs", "gyms"};
                    //initialize array of place name
                    String[] placeNameList = {"Boxing Clubs", "MMA Clubs", "Karate Clubs", "Mixed Martial Arts Clubs", "Gyms"};

                    //set adapter on spinner

                    spType.setAdapter(new ArrayAdapter<>(MainActivity.this
                            , android.R.layout.simple_spinner_dropdown_item, placeNameList));

                    //check permission
                    if (ActivityCompat.checkSelfPermission(MainActivity.this
                            , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        //When permission granted
                        //call method
                        getCurrentLocation();


                    }

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }

    private void getCurrentLocation() {
        //Initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                } else {
                    //permission denied, boo!
                    // functionality that depends on this permission
                }
                return;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
            mMap.addMarker(new MarkerOptions().position(sligoBC).title("Sligo Boxing Club")
                    .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_boxing)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sligoBC));
            mMap.addMarker(new MarkerOptions().position(sligoKarate).title("Sligo Karate Club")
                    .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_karate)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sligoKarate));
            mMap.addMarker(new MarkerOptions().position(sligoGym).title("Sligo Gyms")
                    .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_gym)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sligoGym));
            mMap.addMarker(new MarkerOptions().position(sligoCollegeGym).title("Sligo College Gym")
                    .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_college_gym)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sligoCollegeGym));
            mMap.addMarker(new MarkerOptions().position(sligoMMA).title("Sligo MMA Club")
                    .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_mma)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sligoMMA));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        final double latitude = getIntent().getDoubleExtra("latitude", -1);
        final double longitude = getIntent().getDoubleExtra("longitude", -1);

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng latlng = new LatLng(/*location.getLatitude() */ longitude, latitude /*location.getLongitude()*/);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latlng);

                markerOptions.title("My Marker");
                mMap.clear();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 15);
                mMap.animateCamera(cameraUpdate);
                mMap.addMarker(markerOptions);
            }
        });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawabe=ContextCompat.getDrawable(context, vectorResId);
        vectorDrawabe.setBounds(0,0,vectorDrawabe.getIntrinsicWidth(),
                vectorDrawabe.getIntrinsicHeight());
        Bitmap bitmap=Bitmap.createBitmap(vectorDrawabe.getIntrinsicWidth(),
                vectorDrawabe.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        vectorDrawabe.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public void doTimerPage(View view) {
        Intent StepTimer = new Intent(view.getContext(), StepTimer.class);
        startActivity(StepTimer);
    }
    public void doSMSPage(View view) {
        Intent SMSPage = new Intent(view.getContext(), Messagesboard.class);
        startActivity(SMSPage);
    }
    public void doBMIPage(View view) {
        Intent BMIMain = new Intent(view.getContext(), BMIMain.class);
        startActivity(BMIMain);
    }

    public void doProfilePage(View view) {
        Intent changetoprofile = new Intent(view.getContext(), signinorlogin.class);
        startActivity(changetoprofile);
    }

    public void doListClubs(View view) {
        Intent listClubs = new Intent(view.getContext(), VenuesDB.class);
        startActivity(listClubs);
    }
}
