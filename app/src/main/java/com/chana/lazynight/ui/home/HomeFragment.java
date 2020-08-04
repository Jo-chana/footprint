package com.chana.lazynight.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.chana.lazynight.R;
import com.chana.lazynight.data.GPSTracker;
import com.chana.lazynight.ui.LocationViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private LocationViewModel locationViewModel;
    private ImageView btn_addLoc;
    private EditText et_feel, et_name;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        locationViewModel =
                ViewModelProviders.of(this).get(LocationViewModel.class);
        locationViewModel.setContext(getContext());
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findViews();
        setAddLocation();
        return view;
    }

    private void findViews(){
        btn_addLoc = view.findViewById(R.id.btn_addLoc);
        et_feel = view.findViewById(R.id.et_placeFeel);
        et_name = view.findViewById(R.id.et_placeName);
    }

    private void setAddLocation() {
        btn_addLoc.setOnClickListener(v -> {
            String feel = et_feel.getText().toString();
            String name = et_name.getText().toString();
            String time = getTime();
            if(name.equals(""))
                name = "이름없는 곳";
            if(feel.equals(""))
                feel = "어떤 곳이었을까요?";
            GPSTracker gpsTracker = new GPSTracker(getContext());
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            String location = String.valueOf(latitude) + ":" + String.valueOf(longitude);
            String info = name+"_"+feel+"_"+time;
            Toast.makeText(getContext(), "발자취를 남겼습니다", Toast.LENGTH_SHORT).show();
            locationViewModel.addLocation(location, info);
            locationViewModel.setLocationUpdate(true);
        });
    }

    private String getTime(){
        Date date = new Date(System.currentTimeMillis());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}