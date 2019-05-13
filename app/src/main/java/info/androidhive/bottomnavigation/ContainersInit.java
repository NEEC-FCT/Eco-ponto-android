package info.androidhive.bottomnavigation;


import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.LinkedList;

public class ContainersInit {

    LinkedList<Ecoponto> ecopont = new LinkedList<Ecoponto>();
    private String descricao = "Contentores";

    public ContainersInit() {
        //Adiciona os Pontos
        //Contentories

        LatLng ponto = new LatLng(38.661978, -9.207133);
        Ecoponto eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);

        ponto = new LatLng(38.66196, -9.205482);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661901, -9.204865);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661894, -9.204522);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);



    }


    public LinkedList<Ecoponto> getEcopont() {
        return ecopont;
    }

}
