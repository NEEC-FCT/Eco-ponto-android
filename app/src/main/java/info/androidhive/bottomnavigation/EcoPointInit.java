package info.androidhive.bottomnavigation;



import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.LinkedList;

public class EcoPointInit {

    LinkedList<Ecoponto> ecopont = new LinkedList<Ecoponto>();
    private String descricao = "Ecopontos";

    public EcoPointInit() {
        //Adiciona os Pontos
        //Contentories

        LatLng ponto = new LatLng(38.6627, -9.207313);
        Ecoponto eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661447, -9.206615);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660423, -9.204666);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661895, -9.204598);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660599, -9.206441);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);


    }


    public LinkedList<Ecoponto> getEcopont() {
        return ecopont;
    }

}
