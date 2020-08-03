package com.chana.lazynight.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chana.lazynight.data.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<String>> locations;
    private Context context;

    public HomeViewModel() {
    }

    public void setContext(Context context){
        this.context = context;
    }

    public LiveData<List<String>> getLocation(){
        if(locations==null){
            locations = new MutableLiveData<>();
            loadLocations();
        }
        return locations;
    }

    public void addLocation(String location){
        ArrayList<String> loc = PreferenceManager.getStringArrayList(context,"Location");
        loc.add(location);
        PreferenceManager.setStringArrayList(context,"Location",loc);
    }

    private void loadLocations(){
        ArrayList<String> loc = PreferenceManager.getStringArrayList(context,"Location");
        locations.setValue(loc);
    }
}