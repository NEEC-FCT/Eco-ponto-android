package com.neec.fct.ecopontos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.neec.fct.ecopontos.R;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.MapView;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;


/**
 * Created by user on 12/31/15.
 */
public class FirstFragment extends Fragment implements OnMapReadyCallback {

    View myView;
    private TomtomMap map;
    private MapView  mMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_layout, container, false);

        mMapView = (MapView) myView.findViewById(R.id.mapView2);
        mMapView.addOnMapReadyCallback(this);

        return myView;
    }



    @Override
    public void onMapReady(@NonNull TomtomMap tomtomMap) {
        this.map = tomtomMap;

        LatLng amsterdam = new LatLng(52.37, 4.90);
        SimpleMarkerBalloon balloon = new SimpleMarkerBalloon("Amsterdam");
        tomtomMap.addMarker(new MarkerBuilder(amsterdam).markerBalloon(balloon));
        tomtomMap.centerOn(CameraPosition.builder(amsterdam).zoom(7).build());
    }

}
