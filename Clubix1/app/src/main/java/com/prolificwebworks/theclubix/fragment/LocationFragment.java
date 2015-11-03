package com.prolificwebworks.theclubix.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
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
import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.HomeActivity;
import com.prolificwebworks.theclubix.entities.AllEvents;
import com.prolificwebworks.theclubix.entities.City;
import com.prolificwebworks.theclubix.entities.CityData;
import com.prolificwebworks.theclubix.server.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Akki on 10/26/2015.
 */
public class LocationFragment extends Fragment  implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult>,
        LocationListener {

    Spinner spinLocation;
    TextView submitCity;
    ProgressDialog progressDialog;
    String from, cityName, cityId;
    int pos = 0;
    private static final int RESOLUTION_REQUEST_ID=1337;
    static final int SETTINGS_REQUEST_ID=1338;
    private GoogleApiClient client=null;
    private LocationRequest request=null;
    private ImageView manualLocation;
    List<CityData> cityData;

    public static LocationFragment newInstance(String from) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString("from", from);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from = getArguments() != null ? getArguments().getString("from") : "";

        request=new LocationRequest()
                .setNumUpdates(1)
                .setExpirationDuration(60000)
                .setInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onAttach(Activity host) {
        super.onAttach(host);

        client=new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (client!=null && !client.isConnected()) {
            client.connect();
        }
    }

    @Override
    public void onPause() {
        LocationServices.FusedLocationApi.removeLocationUpdates(client, this);

        if (client!=null && client.isConnected()) {
            client.disconnect();
        }

        super.onPause();
    }

    @Override
    public void onConnected(Bundle undocumented) {
        requestSettings();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(getClass().getSimpleName(),
                "onConnectionSuspended() called, whatever that means");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        boolean anyLuck=false;

        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(getActivity(), RESOLUTION_REQUEST_ID);
                anyLuck=true;
            }
            catch (IntentSender.SendIntentException e) {
                Log.e(((Object)this).getClass().getSimpleName(),
                        "Exception trying to startResolutionForResult()", e);
            }
        }

        if (!anyLuck) {
            Toast.makeText(getActivity(), "No location found",
                    Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }

    @Override
    public void onResult(LocationSettingsResult result) {
        boolean thingsPlumbBusted=true;

        switch(result.getStatus().getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                requestLocations();
                thingsPlumbBusted=false;
                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    result
                            .getStatus()
                            .startResolutionForResult(getActivity(),
                                    SETTINGS_REQUEST_ID);
                    thingsPlumbBusted=false;
                }
                catch (IntentSender.SendIntentException e) {
                    // oops
                }
                break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // more oops
                break;
        }

        if (thingsPlumbBusted) {
            Toast
                    .makeText(getActivity(),
                            "Unable to locate",
                            Toast.LENGTH_LONG)
                    .show();
//            getActivity().finish();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
//        new FetchForecastTask()
//                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, location);

        Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0)
            cityName = addresses.get(0).getLocality();
    }

    private void requestSettings() {
        LocationSettingsRequest.Builder b=
                new LocationSettingsRequest.Builder()
                        .addLocationRequest(request);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(client,
                        b.build());

        result.setResultCallback(this);
    }

    private void requestLocations() {
        PendingResult<Status> result=
                LocationServices.FusedLocationApi
                        .requestLocationUpdates(client, request, this);

        result.setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    Toast
                            .makeText(getActivity(),
                                    "Location found",
                                    Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast
                            .makeText(getActivity(), status.getStatusMessage(),
                                    Toast.LENGTH_LONG)
                            .show();
                    getActivity().finish();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        if (from.equalsIgnoreCase("menu")) {
            ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_location));
        }

        manualLocation = (ImageView) view.findViewById(R.id.manualLocation);
        spinLocation = (Spinner) view.findViewById(R.id.spinLocation);
        submitCity = (TextView) view.findViewById(R.id.submitCity);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        getCityData();

        manualLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityData!= null){
                    if (cityData.size() > 0){
                        for (int i=0; i<cityData.size(); i++){
                            if (cityName.equalsIgnoreCase(cityData.get(i).getCity_name())){
                              cityId = cityData.get(i).getCity_id();
                            }
                        }
                    }
                }
            }
        });

        return view;
    }

    void getCityData() {
        Client.INSTANCE.getAllCities(new Callback<City>() {
            @Override
            public void success(City city, Response response) {
                cityData = city.getPostData();
                List<String> cityNames = new ArrayList<>();

                for (int i = 0; i < cityData.size(); i++) {
                    cityNames.add(cityData.get(i).getCity_name());
                }

                Log.e("CitySize", cityNames.size() + "");
                cityId = cityData.get(0).getCity_id();

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_item, cityNames);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinLocation.setAdapter(dataAdapter);
                spinLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cityId = cityData.get(position).getCity_id();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                submitCity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Client.INSTANCE.getCityRestaurants(cityId, new Callback<City>() {
                            @Override
                            public void success(City city, Response response) {

                                if (from.equalsIgnoreCase("menu")) {
                                    getFragmentManager().beginTransaction().replace(R.id.container_body, new HomeFragment()).commit();
                                } else {
                                    getFragmentManager().beginTransaction().replace(R.id.frameSplash, new FirstClubFragment()).commit();
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Log.e("RetrofitError", error.getMessage());
                            }
                        });
                    }
                });

                progressDialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }


}
