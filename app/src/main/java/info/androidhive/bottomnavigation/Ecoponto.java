package info.androidhive.bottomnavigation;

import com.mapbox.mapboxsdk.geometry.LatLng;


public class Ecoponto {

    private String descricao;
    private  boolean verde;
    private boolean amarelo;
    private  boolean azul;
    private LatLng location;

    public  Ecoponto(String descricao , boolean verde , boolean amarelo , boolean azul , LatLng location){
        this.descricao= descricao;
        this.verde = verde;
        this.amarelo = amarelo;
        this.azul = azul;
        this.location = location;

    }

    public boolean isAmarelo() {
        return amarelo;
    }

    public boolean isVerde() {
        return verde;
    }

    public boolean isAzul() {
        return azul;
    }

    public String getDescricao() {
        return descricao;
    }

    public LatLng getLocation() {
        return location;
    }
}
