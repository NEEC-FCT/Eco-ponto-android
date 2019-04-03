package info.androidhive.bottomnavigation;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.LinkedList;

public class AmareloInit {

    LinkedList<Ecoponto> ecopont = new LinkedList<Ecoponto>();
    private String descricao = "Ecopontos";

    public AmareloInit() {
        //Adiciona os Pontos
        //Contentories

        LatLng ponto = new LatLng(36.663213, -9.206909);
        Ecoponto eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.663065, -9.207025);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.663053, -9.207214);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.662532, -9.207467);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661874, -9.207785);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661599, -9.206665);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661947, -9.206347);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.662153, -9.205929);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.662586, -9.205320);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.662008, -9.204093);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661789, -9.204689);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661470, -9.205313);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.6622796, -9.204981);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661242, -9.205155);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661150, -9.204850);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661459, -9.204640);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661501, -9.203496);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.661064, -9.203010);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660306, -9.204131);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660806, -9.204810);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660773, -9.205819);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660438, -9.205685);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660363, -9.206723);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);
        ponto = new LatLng(38.660292, -9.206993);
        eco = new Ecoponto(this.descricao, false, false, true, ponto);
        ecopont.add(eco);


    }


    public LinkedList<Ecoponto> getEcopont() {
        return ecopont;
    }

}