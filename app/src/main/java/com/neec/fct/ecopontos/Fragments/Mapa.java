package com.neec.fct.ecopontos.Fragments;
/**
 * Copyright (c) 2015-2018 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.neec.fct.ecopontos.GPS.FunctionalExampleFragment;
import com.neec.fct.ecopontos.R;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.Icon;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.MarkerAnchor;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;

import timber.log.Timber;

public class Mapa extends Fragment implements FunctionalExampleFragment {

    private final String MAP_RESTORE_KEY = "MAP_RESTORED_ARG";
    public TomtomMap tomtomMap;


    Context thiscontext;
    private boolean isRestored;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        if (savedInstanceState != null) {
            isRestored = savedInstanceState.getBoolean(MAP_RESTORE_KEY, false);
        }

        view = inflater.inflate(R.layout.mapa, null);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        assignMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResume()");

        setActionBarTitle(R.string.app_name);

    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.d("onPause()");
    }

    @Override
    public void onMenuItemSelected() {

    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public boolean isMapRestored() {
        return isRestored;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getName();
    }

    @Override
    public void setActionBarTitle(@StringRes int titleId) {

    }

    @Override
    public void setActionBarSubtitle(@StringRes int subtitleId) {

    }

    public void assignMap() {
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        FloatingActionButton nearby = (FloatingActionButton) view.findViewById(R.id.nearby);
        FloatingActionButton search = (FloatingActionButton) view.findViewById(R.id.search);

        //listeners


        nearby.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB","nearby");
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Perform action on click
            Log.d("FAB","search");
             }
        });

        //mapa


        mapFragment.getAsyncMap(new OnMapReadyCallback() {
            @Override
            public void onMapReady(TomtomMap map) {
                tomtomMap = map;
                System.out.println("Entrou!!");

                LatLng FCTUNL = new LatLng( 38.661050 , -9.205007);

                //pontos
                //para uso na demo
                LatLng ponto1 = new LatLng( 38.661950 , -9.205797);
                LatLng ponto2 = new LatLng( 38.661150 , -9.205737);
                LatLng ponto3 = new LatLng( 38.661550 , -9.205797);

                tomtomMap.zoomTo(17);
                tomtomMap.centerOn(FCTUNL);

                MarkerBuilder markerBuilder = new MarkerBuilder(FCTUNL)
                    //    .icon(Icon.Factory.fromResources(getContext(), R.drawable.ponto))
                        .icon(Icon.Factory.fromResources(getContext(), R.drawable.allgarbage))
                        .markerBalloon(new SimpleMarkerBalloon( "Zona do Bar"))
                        .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                        .decal(true); //By default is false
                tomtomMap.addMarker(markerBuilder);


                //demos
                 markerBuilder = new MarkerBuilder(ponto1)
                        //    .icon(Icon.Factory.fromResources(getContext(), R.drawable.ponto))
                        .icon(Icon.Factory.fromResources(getContext(), R.drawable.glassbin))
                        .markerBalloon(new SimpleMarkerBalloon( "Fundo Corredor -Norte"))
                        .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                        .decal(true); //By default is false
                tomtomMap.addMarker(markerBuilder);

                 markerBuilder = new MarkerBuilder(ponto2)
                        //    .icon(Icon.Factory.fromResources(getContext(), R.drawable.ponto))
                        .icon(Icon.Factory.fromResources(getContext(), R.drawable.paperbin))
                        .markerBalloon(new SimpleMarkerBalloon( "Fundo Corredor -sul"))
                        .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                        .decal(true); //By default is false
                tomtomMap.addMarker(markerBuilder);

                 markerBuilder = new MarkerBuilder(ponto3)
                        //    .icon(Icon.Factory.fromResources(getContext(), R.drawable.ponto))
                        .icon(Icon.Factory.fromResources(getContext(), R.drawable.plasticbin))
                        .markerBalloon(new SimpleMarkerBalloon( "Entrada Principal"))
                        .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                        .decal(true); //By default is false
                tomtomMap.addMarker(markerBuilder);




                alignCurrentLocationButton(thiscontext, tomtomMap);
                if (!isMapRestored()) {
                    showMyPosition();
                }
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(MAP_RESTORE_KEY, true);
        super.onSaveInstanceState(outState);
    }

    void alignCurrentLocationButton(@NonNull Context context, @NonNull TomtomMap tomtomMap) {
        int currentLocationButtonBottomMargin = context.getResources().getDimensionPixelSize(R.dimen.current_location_default_margin_bottom);
        int currentLocationButtonLeftMargin = context.getResources().getDimensionPixelSize(R.dimen.compass_default_margin_start);

        tomtomMap.getUiSettings().getCurrentLocationView().setMargins(
                currentLocationButtonLeftMargin, 0, 0, currentLocationButtonBottomMargin);
    }

    public void showMyPosition() {
        if (tomtomMap == null) {
            return;
        }
        tomtomMap.centerOnMyLocation();
    }

    @Override
    public void showInfoText(String text, long duration) {

    }

    @Override
    public void showInfoText(@StringRes int text, long duration) {
    }

    @Override
    public void enableOptionsView() {

    }

    @Override
    public void disableOptionsView() {

    }



    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(LatLng inicio , LatLng fim ) {

        double lat1 = inicio.getLatitude();
        double lat2 = fim.getLatitude();
        double lon1 = inicio.getLongitude();
        double lon2 = fim.getLongitude();

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
}