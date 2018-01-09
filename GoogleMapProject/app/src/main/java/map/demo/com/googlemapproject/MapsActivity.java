package map.demo.com.googlemapproject;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import map.demo.com.network.Api;
import map.demo.com.pojos.GoogleSearch;
import map.demo.com.pojos.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("ALL")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, android.location.LocationListener,SearchView.OnQueryTextListener {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private Location mylocation;
    private GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 100;
    private final static int REQUEST_ID_GPS_PERMISSIONS = 0x2;
    private SearchView mapSearchView;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    public int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapSearchView = findViewById(R.id.mapSearchView);
        mapSearchView.setOnQueryTextListener(this);
        checkPermissions();
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
              //  Log.i(TAG, "Place: " + place.getName());
                Toast.makeText(MapsActivity.this, ""+place.getName(), Toast.LENGTH_SHORT).show();
               /* mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                //   mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 16.0f), 2500, null);*/
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Toast.makeText(MapsActivity.this, ""+status, Toast.LENGTH_SHORT).show();
            }
        });

        View childView=(LinearLayout) autocompleteFragment.getView();
        EditText editText = (EditText) childView.findViewById(R.id.place_autocomplete_search_input);
        editText.setOnKeyListener(new EditText.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction()== KeyEvent.KEYCODE_SEARCH){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_SEARCH:
                            Toast.makeText(MapsActivity.this, "holla" , Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void checkPermissions() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //getMyLocation();
            setUpGClient();
            return;
        }
        int permissionLocation = ContextCompat.checkSelfPermission(MapsActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_GPS_PERMISSIONS);
            }
        } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ID_GPS_PERMISSIONS);
            }
        }

    private void getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(MapsActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

                    final LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(3000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, this);
                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(googleApiClient, builder.build());
                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                        @Override
                        public void onResult(@NonNull LocationSettingsResult result) {
                            final Status status = result.getStatus();
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(MapsActivity.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(googleApiClient);
                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(MapsActivity.this,
                                                REQUEST_CHECK_SETTINGS_GPS);
                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied.
                                    // However, we have no way
                                    // to fix the
                                    // settings so we won't show the dialog.
                                    // finish();
                                    break;
                            }
                        }
                    });
                }
            }
        }
    }


    private synchronized void setUpGClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, 0, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            googleApiClient.connect();
        } else {
            getMyLocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_GPS_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setUpGClient();
                } else {
                    Toast.makeText(this, "LOCATION PERMISSION DENIED", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == REQUEST_CHECK_SETTINGS_GPS) {
                if (resultCode == Activity.RESULT_OK) {
                    getMyLocation();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

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
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(true);

            View locationButton = ((View) findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            // position on right bottom
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.setMargins(0, 0, 20, 200);
            if (mylocation != null) {
                LatLng currentLocation = new LatLng(mylocation.getLatitude(), mylocation.getLongitude());
                List<Address> addressesList = null;
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    addressesList = geocoder.getFromLocation(mylocation.getLatitude(), mylocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //mMap.clear();
                if (value == 0) {
                    //mMap.addMarker(new MarkerOptions().position(currentLocation).title(addressesList.get(0).getFeatureName()));
                    value = 100;
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16.0f), 2500, null);
                }
            }
           /* LatLng location = new LatLng(mylocation.getLatitude(), mylocation.getLongitude());

            List<Address> addresses = null;
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (IOException e) {
                e.printStackTrace();
            }


            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            mMap.addMarker(new MarkerOptions().position(location).title(addresses.get(0).getAddressLine(0)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            //   mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f), 2500, null);
*/
        }



    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkPermissions();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        mapFragment.getMapAsync(MapsActivity.this);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.API_SEARCH_LINK).addConverterFactory(GsonConverterFactory.create()).build();

        final Api api = retrofit.create(Api.class);

        Call<GoogleSearch> call = api.searchNearByPlaces(mylocation.getLatitude() + "," + mylocation.getLongitude(), 2000, s, "AIzaSyBGDP9imdJXwQPXCqhjXC8-K-FvElbJn2I");

        String str = call.request().url().toString();
        System.out.print(str);

        call.enqueue(new Callback<GoogleSearch>() {
            @Override
            public void onResponse(Call<GoogleSearch> call, Response<GoogleSearch> response) {
                GoogleSearch apiFixer = response.body();
                System.out.print("api result = " + response.body());
                mMap.clear();
                if (apiFixer.getStatus().equalsIgnoreCase("OK")) {
                    for (Result result : apiFixer.getResults()) {
                        /*View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                        TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                        numTxt.setText("27");*/
                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng = new LatLng(result.getGeometry().getLocation().getLat(), result.getGeometry().getLocation().getLng());
                        markerOptions.position(latLng);
                        markerOptions.title(result.getName());
                        Marker m = mMap.addMarker(markerOptions);
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f), 2500, null);
                        MarkerOptions marker = new MarkerOptions();
                        marker.position(new LatLng(result.getGeometry().getLocation().getLng(), result.getGeometry().getLocation().getLng()));
                        marker.title(result.getName());
                        mMap.addMarker(marker);
                        mMap.setInfoWindowAdapter(new InfoWindowCustom(MapsActivity.this, result));
                    }
                }
            }

            @Override
            public void onFailure(Call<GoogleSearch> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "No Result found \nReason : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return false;
    }

    public class InfoWindowCustom implements GoogleMap.InfoWindowAdapter {
        Context context;
        LayoutInflater inflater;
        Result result;

        public InfoWindowCustom(Context context, Result result) {
            this.context = context;
            this.result = result;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.custom_marker_layout, null);

            //TextView tvPlaceId = (TextView) v.findViewById(R.id.tvPlaceId);
            TextView tvPlaceName = (TextView) v.findViewById(R.id.tvPlaceName);
            TextView tvPlaceVicinity = (TextView) v.findViewById(R.id.tvPlaceVicinity);
            RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);


            tvPlaceName.setText(result.getName());
            // tvPlaceId.setText(result.getPlaceId());
            tvPlaceVicinity.setText(result.getVicinity());
            ratingBar.setRating((float) result.getRating());
            return v;
        }
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}

