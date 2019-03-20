package info.androidhive.bottomnavigation.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import info.androidhive.bottomnavigation.ContainersInit;
import info.androidhive.bottomnavigation.EcoPointInit;
import info.androidhive.bottomnavigation.Ecoponto;
import info.androidhive.bottomnavigation.GPSTracker;
import info.androidhive.bottomnavigation.MainActivity;
import info.androidhive.bottomnavigation.R;
import info.androidhive.bottomnavigation.ScannerActivity;
import info.androidhive.bottomnavigation.TrashInit;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;


public class GiftsFragment extends Fragment {

    private MapView mapView;

    public static final int MY_CAMERA_PERMISSION_CODE = 2;
    private final String MAP_RESTORE_KEY = "MAP_RESTORED_ARG";
    Context thiscontext;
    TrashInit paper;
    ContainersInit containers;
    EcoPointInit ecopontos;
    MapboxMap mapboxMap;
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







    public GiftsFragment() {
        // Required empty public constructor
    }

    public static GiftsFragment newInstance(String param1, String param2) {
        GiftsFragment fragment = new GiftsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Mapbox.getInstance(getContext(), "pk.eyJ1Ijoic2lydmVsb3NvIiwiYSI6ImNqdGdrcW12YjF6YmkzeXA4N3h4bXFnYm4ifQ.NZwcdW2cDxU5S4bz7jg1zA");

        thiscontext = container.getContext();
        paper = new TrashInit();
        containers = new ContainersInit();


        ecopontos = new EcoPointInit();
         view = inflater.inflate(R.layout.fragment_gifts, container, false);



        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {


                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        //Contentores
                        List<Feature> markerCoordinates = new ArrayList<>();
                        Iterator<Ecoponto> itEcoponts = ecopontos.getEcopont().iterator();
                        while (itEcoponts.hasNext()) {
                            Ecoponto ecoContainer = itEcoponts.next();
                            markerCoordinates.add(Feature.fromGeometry(
                                    Point.fromLngLat(ecoContainer.getLocation().getLongitude(), ecoContainer.getLocation().getLatitude()))); // Boston Common Park
                        }
                        // Add the marker image to map

                        style.addSource(new GeoJsonSource("marker-source",
                                FeatureCollection.fromFeatures(markerCoordinates)));

                        style.addImage("my-marker-image", BitmapFactory.decodeResource(
                                view.getResources(), R.drawable.recycling));

                        style.addLayer(new SymbolLayer("marker-layer", "marker-source")
                                .withProperties(PropertyFactory.iconImage("my-marker-image"),
                                        iconOffset(new Float[]{0f, -9f})));

                        style.addSource(new GeoJsonSource("selected-marker"));
                        style.addLayer(new SymbolLayer("selected-marker-layer", "selected-marker")
                                .withProperties(PropertyFactory.iconImage("my-marker-image"),
                                        iconOffset(new Float[]{0f, -9f})));
                        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                .target(new LatLng(38.661050, -9.205007))
                                .zoom(18)
                                .build());

                        SharedPreferences settings = getActivity().getSharedPreferences("FRAG", 0);
                        String ordem = settings.getString("Abrir", "");
                        if(ordem.contains("lixo")){
                            //apaga
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("Abrir", "");
                            editor.commit();

                            LatLng position = getTrash();
                            mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                    .target(new LatLng(position.getLatitude(), position.getLongitude()))
                                    .zoom(18)
                                    .build());


                        }

                        else if(ordem.contains("Ecoponto")){
                            //apaga
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("Abrir", "");
                            editor.commit();

                            LatLng position = getEcoPontos();
                            mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                    .target(new LatLng(position.getLatitude(), position.getLongitude()))
                                    .zoom(18)
                                    .build());


                        }

                        //adiciona
                        //Contentores
                        //Contentores
                        markerCoordinates = new ArrayList<>();
                        Iterator<Ecoponto> itContainers = containers.getEcopont().iterator();
                        while (itContainers.hasNext()) {
                            Ecoponto ecoContainer = itContainers.next();
                            markerCoordinates.add(Feature.fromGeometry(
                                    Point.fromLngLat(ecoContainer.getLocation().getLongitude(), ecoContainer.getLocation().getLatitude()))); // Boston Common Park
                        }
                        // Add the marker image to map

                        style.addSource(new GeoJsonSource("marker-source-ecoponto",
                                FeatureCollection.fromFeatures(markerCoordinates)));

                        style.addImage("my-marker-image-ecoponto", BitmapFactory.decodeResource(
                                view.getResources(), R.drawable.ecoponto));

                        style.addLayer(new SymbolLayer("marker-layer-ecoponto", "marker-source-ecoponto")
                                .withProperties(PropertyFactory.iconImage("my-marker-image-ecoponto"),
                                        iconOffset(new Float[]{0f, -9f})));

                        style.addSource(new GeoJsonSource("selected-marker-ecoponto"));
                        style.addLayer(new SymbolLayer("selected-marker-layer-ecoponto", "selected-marker-ecoponto")
                                .withProperties(PropertyFactory.iconImage("my-marker-image-ecoponto"),
                                        iconOffset(new Float[]{0f, -9f})));



                        markerCoordinates = new ArrayList<>();
                        Iterator<Ecoponto> itPaper = paper.getEcopont().iterator();
                        while (itPaper.hasNext()) {
                            Ecoponto ecoContainer = itPaper.next();
                            markerCoordinates.add(Feature.fromGeometry(
                                    Point.fromLngLat(ecoContainer.getLocation().getLongitude(), ecoContainer.getLocation().getLatitude()))); // Boston Common Park
                        }
                        // Add the marker image to map

                        style.addSource(new GeoJsonSource("marker-source-trash",
                                FeatureCollection.fromFeatures(markerCoordinates)));

                        style.addImage("my-marker-image-trash", BitmapFactory.decodeResource(
                                view.getResources(), R.drawable.trash64));

                        style.addLayer(new SymbolLayer("marker-layer-trash", "marker-source-trash")
                                .withProperties(PropertyFactory.iconImage("my-marker-image-trash"),
                                        iconOffset(new Float[]{0f, -9f})));

                        style.addSource(new GeoJsonSource("selected-marker-trash"));
                        style.addLayer(new SymbolLayer("selected-marker-layer-trash", "selected-marker-trash")
                                .withProperties(PropertyFactory.iconImage("my-marker-image-trash"),
                                        iconOffset(new Float[]{0f, -9f})));



                        mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                .target(new LatLng(38.661050, -9.205007))
                                .zoom(18)
                                .build());






                        FloatingActionButton recycling = (FloatingActionButton) view.findViewById(R.id.recycling);
                        FloatingActionButton search = (FloatingActionButton) view.findViewById(R.id.ecopontoback);
                        FloatingActionButton lixo = (FloatingActionButton) view.findViewById(R.id.lixo);
                        FloatingActionButton qrcode = (FloatingActionButton) view.findViewById(R.id.qrgame);



                        lixo.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                // Perform action on click
                                Log.d("FAB", "nearby  ");
                                LatLng position = getContainer();
                                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                        .target(position)
                                        .zoom(17)
                                        .build());

                            }
                        });



                        recycling.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                // Perform action on click
                                Log.d("FAB", "nearby  ");
                                LatLng position = getEcoPontos();
                                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                        .target(position)
                                        .zoom(17)
                                        .build());

                            }
                        });


                        search.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                // Perform action on click
                                Log.d("FAB", "search");
                                LatLng position = getTrash();
                                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                                        .target(position)
                                        .zoom(17)
                                        .build());


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


                    }
                });
            }
        });

        return  view;
    }

}
