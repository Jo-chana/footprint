package com.chana.lazynight.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chana.lazynight.R;
import com.chana.lazynight.data.PlaceItem;
import com.chana.lazynight.data.PreferenceManager;
import com.chana.lazynight.ui.LocationViewModel;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    LocationViewModel viewModel;
    RecyclerView rv_place;
    View view;
    ArrayList<PlaceItem> placeItems = new ArrayList<>();
    Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                ViewModelProviders.of(this).get(LocationViewModel.class);
        viewModel.setContext(getContext());
        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        findViews();
        setRecyclerView();
        return view;
    }

    private void findViews(){
        rv_place = view.findViewById(R.id.rv_place);
    }

    private void setRecyclerView(){
        for(String loc : PreferenceManager.getStringArrayList(getContext(),"Location")){
            for(String info : PreferenceManager.getStringArrayList(getContext(), loc)){
                PlaceItem item = new PlaceItem();
                item.setLatitude(Double.parseDouble(loc.split(":")[0]));
                item.setLongitude(Double.parseDouble(loc.split(":")[1]));
                item.setPlaceName(info.split("_")[0]);
                item.setPlaceFeel(info.split("_")[1]);
                item.setVisitTime(info.split("_")[2]);
                placeItems.add(0,item);
            }
        }
        adapter = new Adapter(getContext(),placeItems);
        rv_place.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        rv_place.setAdapter(adapter);
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder>{

        ArrayList<PlaceItem> items;
        Context context;

        public Adapter(Context context, ArrayList<PlaceItem> items){
            this.items = items;
            this.context = context;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(context,R.layout.item_foot_print,null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            PlaceItem item = items.get(position);
            holder.name.setText(item.getPlaceName());
            holder.feel.setText(item.getPlaceFeel());
            holder.time.setText(item.getVisitTime());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, feel, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_place_name);
            feel = itemView.findViewById(R.id.tv_place_feel);
            time = itemView.findViewById(R.id.tv_time);
        }
    }
}