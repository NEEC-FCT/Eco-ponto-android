package com.neec.fct.ecopontos;

import com.tomtom.online.sdk.common.location.LatLng;

import java.util.LinkedList;

public class ContainersInit {

    LinkedList<Ecoponto> ecopont = new LinkedList<Ecoponto>();
    private String descricao = "Contentores";

    public ContainersInit() {
        //Adiciona os Pontos
        //Contentories

        LatLng ponto = new LatLng( 38.661978 , - 9.207133);
        Ecoponto eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);

        ponto = new LatLng( 38.66196 , - 9.205482);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng( 38.661901, - 9.204865);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng( 38.661894 ,- 9.204522);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng( 38.659691, - 9.203315);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng( 38.660602, - 9.206335);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660057, - 9.20642);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
    }


    public LinkedList<Ecoponto> getEcopont() {
            return ecopont;
    }

}
