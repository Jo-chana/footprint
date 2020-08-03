package com.chana.lazynight.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.chana.lazynight.R;
import com.chana.lazynight.data.GPSTracker;
import com.chana.lazynight.ui.home.HomeViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DashboardFragment extends Fragment implements OnMapReadyCallback {

    private HomeViewModel viewModel;
    private MapView mapView = null;
    GoogleMap map;
    CameraUpdate cameraUpdate;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.setContext(getContext());
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        GPSTracker gpsTracker = new GPSTracker(getContext());
        LatLng current = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        viewModel.getLocation().observe(this, location -> {
            for(String loc : location){
                double latitude = Double.parseDouble(loc.split(":")[0]);
                double longitude = Double.parseDouble(loc.split(":")[1]);
                LatLng position = new LatLng(latitude,longitude);
                MarkerOptions options = new MarkerOptions();
                options.position(position);
                map.addMarker(options);
            }
        });
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(current,15));
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mapView.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }
}