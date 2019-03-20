package com.neec.fct.ecopontos.Fragments;
/**
 * Copyright (c) 2015-2018 TomTom N.V. All rights reserved.
 * <p>
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.neec.fct.ecopontos.ContainersInit;
import com.neec.fct.ecopontos.EcoPointInit;
import com.neec.fct.ecopontos.Ecoponto;
import com.neec.fct.ecopontos.GPS.FunctionalExampleFragment;
import com.neec.fct.ecopontos.GPSTracker;
import com.neec.fct.ecopontos.R;
import com.neec.fct.ecopontos.ScannerActivity;
import com.neec.fct.ecopontos.TrashInit;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.Icon;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.MarkerAnchor;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;

import java.util.Iterator;

import timber.log.Timber;

public class Mapa extends Fragment implements FunctionalExampleFragment {

    public static final int MY_CAMERA_PERMISSION_CODE = 2;
    private final String MAP_RESTORE_KEY = "MAP_RESTORED_ARG";
    public TomtomMap tomtomMap;
    Context thiscontext;
    TrashInit paper;
    ContainersInit containers;
    EcoPointInit ecopontos;
    private boolean isRestored;
    private View view;

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * @returns Distance in Meters
     */

    public double getDistance(LatLng pos1, LatLng pos2) {

        int R = 6371; // km
        double x = (pos2.getLongitude() - pos1.getLongitude()) * Math.cos((pos1.getLatitude() + pos2.getLatitude()) / 2);
        double y = (pos2.getLatitude() - pos1.getLatitude());
        return Math.sqrt(x * x + y * y) * R;

    }


    public LatLng getEcoPontos() {


        GPSTracker gps = new GPSTracker(getContext());

        LatLng position = new LatLng(gps.getLatitude(), gps.getLongitude());


        Iterator<Ecoponto> it = ecopontos.getEcopont().iterator();
        System.out.println( "TAM: " + ecopontos.getEcopont().size());
        LatLng best = it.next().getLocation();
        double distance = getDistance(position, best);

        while (it.hasNext()) {
            LatLng location = it.next().getLocation();
            double current = getDistance(position, location);
            if (current < distance) {
                distance = current;
                best = location;
            }

        }
        System.out.println("BEST: " + best.toString());
        return best;
    }

    public LatLng getContainer() {


        GPSTracker gps = new GPSTracker(getContext());

        LatLng position = new LatLng(gps.getLatitude(), gps.getLongitude());


        Iterator<Ecoponto> it = containers.getEcopont().iterator();
        LatLng best = it.next().getLocation();
        double distance = getDistance(position, best);

        while (it.hasNext()) {
            LatLng location = it.next().getLocation();
            double current = getDistance(position, location);
            if (current < distance) {
                distance = current;
                best = location;
            }

        }
        System.out.println("BEST: " + best.toString());
        return best;
    }

    public LatLng getTrash() {


        GPSTracker gps = new GPSTracker(getContext());

        LatLng position = new LatLng(gps.getLatitude(), gps.getLongitude());


        Iterator<Ecoponto> it = paper.getEcopont().iterator();
        LatLng best = it.next().getLocation();
        double distance = getDistance(position, best);

        while (it.hasNext()) {
            LatLng location = it.next().getLocation();
            double current = getDistance(position, location);
            if (current < distance) {
                distance = current;
                best = location;
            }

        }
        System.out.println("BEST: " + best.toString());
        return best;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        paper = new TrashInit();
        containers = new ContainersInit();
        ecopontos = new EcoPointInit();


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
        FloatingActionButton recycling = (FloatingActionButton) view.findViewById(R.id.recycling);
        FloatingActionButton search = (FloatingActionButton) view.findViewById(R.id.ecopontoback);
        FloatingActionButton lixo = (FloatingActionButton) view.findViewById(R.id.lixo);
        FloatingActionButton qrcode = (FloatingActionButton) view.findViewById(R.id.qrgame);



        lixo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB", "nearby  ");
                LatLng position = getContainer();
                tomtomMap.centerOn(new LatLng(position.getLatitude(), position.getLongitude()));

            }
        });



        recycling.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB", "nearby  ");
                LatLng position = getEcoPontos();
                tomtomMap.centerOn(new LatLng(position.getLatitude(), position.getLongitude()));

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d("FAB", "search");
                LatLng position = getTrash();
                tomtomMap.centerOn(new LatLng(position.getLatitude(), position.getLongitude()));
            }
        });


        //listeners
        qrcode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                    Log.v("STORAGE", "Permission is granted");
                    if (getContext().checkSelfPermission(android.Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                                MY_CAMERA_PERMISSION_CODE);
                        Toast.makeText(getActivity(), "Tenta novamente", Toast.LENGTH_LONG).show();

                    } else {
                        Intent intent = new Intent(getContext(), ScannerActivity.class);
                        startActivity(intent);
                    }


                } else {

                    Intent intent = new Intent(getContext(), ScannerActivity.class);
                    startActivity(intent);

                }


            }
        });


        //mapa


        mapFragment.getAsyncMap(new OnMapReadyCallback() {
            @Override
            public void onMapReady(TomtomMap map) {
                tomtomMap = map;
                System.out.println("Entrou!!");


                //Center on FCT
                tomtomMap.zoomTo(17);
                tomtomMap.centerOn(new LatLng(38.661050, -9.205007));







                //Contentores
                Iterator<Ecoponto> itEcoponts = ecopontos.getEcopont().iterator();
                while (itEcoponts.hasNext()) {
                    Ecoponto ecoContainer = itEcoponts.next();
                    MarkerBuilder markerBuilder = new MarkerBuilder(ecoContainer.getLocation())
                            //    .icon(Icon.Factory.fromResources(getContext(), R.drawable.ponto))
                            .icon(Icon.Factory.fromResources(getContext(), R.drawable.recycling))
                            .markerBalloon(new SimpleMarkerBalloon(ecoContainer.getDescricao()))
                            .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                            .decal(true); //By default is false
                    tomtomMap.addMarker(markerBuilder);
                }

                //Contentores
                Iterator<Ecoponto> itContainers = containers.getEcopont().iterator();
                while (itContainers.hasNext()) {
                    Ecoponto ecoContainer = itContainers.next();
                    MarkerBuilder markerBuilder = new MarkerBuilder(ecoContainer.getLocation())

                            .icon(Icon.Factory.fromResources(getContext(), R.drawable.ecoponto))
                            .markerBalloon(new SimpleMarkerBalloon(ecoContainer.getDescricao()))
                            .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                            .decal(true); //By default is false
                    tomtomMap.addMarker(markerBuilder);
                }



                //Papeleiras
                Iterator<Ecoponto> itPaper = paper.getEcopont().iterator();
                while (itPaper.hasNext()) {
                    Ecoponto ecopaper = itPaper.next();
                    MarkerBuilder markerBuilder = new MarkerBuilder(ecopaper.getLocation())
                            //    .icon(Icon.Factory.fromResources(getContext(), R.drawable.ponto))
                            .icon(Icon.Factory.fromResources(getContext(), R.drawable.trash64))
                            .markerBalloon(new SimpleMarkerBalloon(ecopaper.getDescricao()))
                            .tag("more information in tag").iconAnchor(MarkerAnchor.Bottom)
                            .decal(true); //By default is false
                    tomtomMap.addMarker(markerBuilder);
                }


                alignCurrentLocationButton(thiscontext, tomtomMap);
                if (!isMapRestored()) {
                    showMyPosition();
                }


                SharedPreferences settings = getActivity().getSharedPreferences("FRAG", 0);
                String ordem = settings.getString("Abrir", "");
                if(ordem.contains("lixo")){
                    //apaga
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("Abrir", "");
                    editor.commit();

                    LatLng position = getTrash();
                    tomtomMap.centerOn(new LatLng(position.getLatitude(), position.getLongitude()));


                }

                else if(ordem.contains("Ecoponto")){
                    //apaga
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("Abrir", "");
                    editor.commit();

                    LatLng position = getEcoPontos();
                    tomtomMap.centerOn(new LatLng(position.getLatitude(), position.getLongitude()));


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
}