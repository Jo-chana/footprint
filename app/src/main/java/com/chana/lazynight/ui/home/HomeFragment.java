package com.chana.lazynight.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.chana.lazynight.R;
import com.chana.lazynight.data.GPSTracker;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button btn_addLoc;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.setContext(getContext());
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findViews();
        setAddLocation();
        return view;
    }

    private void findViews(){
        btn_addLoc = view.findViewById(R.id.btn_addLoc);
    }

    private void setAddLocation() {
        btn_addLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gpsTracker = new GPSTracker(getContext());
                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();
                String location = String.valueOf(latitude) + ":" + String.valueOf(longitude);
                Toast.makeText(getContext(), location, Toast.LENGTH_SHORT).show();
                homeViewModel.addLocation(location);
            }
        });
    }
}