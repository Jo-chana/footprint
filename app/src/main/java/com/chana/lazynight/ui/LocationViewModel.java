package com.chana.lazynight.ui;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chana.lazynight.data.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class LocationViewModel extends ViewModel {

    private MutableLiveData<List<String>> locations;
    private Context context;
    private boolean isMapReady = false;
    private boolean locationUpdate = false;

    public LocationViewModel() {
    }

    public void setContext(Context context){
        this.context = context;
    }

    public LiveData<List<String>> getLocation(){
        if(locations==null){
            locations = new MutableLiveData<>();
            loadLocations();
        }else if(locationUpdate){
            loadLocations();
        }
        return locations;
    }

    public void addLocation(String location, String info){
        ArrayList<String> loc = PreferenceManager.getStringArrayList(context,"Location");
        loc.add(location);
        PreferenceManager.setStringArrayList(context,"Location",loc);

        ArrayList<String> locInfo = PreferenceManager.getStringArrayList(context,location);
        locInfo.add(info);
        PreferenceManager.setStringArrayList(context,location,locInfo);
    }

    private void loadLocations(){
        ArrayList<String> loc = PreferenceManager.getStringArrayList(context,"Location");
        locations.setValue(loc);
        locationUpdate = false;
    }

    public boolean getMapReady(){
        return isMapReady;
    }

    public void setMapReady(boolean isMapReady){
        this.isMapReady = isMapReady;
    }

    public void setLocationUpdate(boolean update){
        locationUpdate = update;
    }

    public void testOption(){
        PreferenceManager.clear(context);
        addLocation("37.526960:126.932762","여의나루_수능끝나고 추위에 떨면서 라면먹고 야경도 보고 좋았던곳 ㅎ_2015-12-12 23:32");
        addLocation("37.512736:127.102007","잠실 롯데 타워_3일동안 work flex 체험 해봤는데 넘좋았음_2020-07-23 15:30");
        addLocation("37.546650:126.993477","남산 타워_더웠지만 불꽃놀이도 보고 너무 좋았다_2018-08-12 21:12");
        addLocation("37.624492:127.098704","육군사관학교_친구 면회가서 배터지게 먹여주고 옴_2019-05-05 14:10");
        addLocation("37.549486:126.915117","합정동_추억 참 많은 곳. 여자친구랑도 와보고, 스터디고 하고_2020-04-12 13:40");
    }
}