package com.neec.fct.ecopontos.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.neec.fct.ecopontos.R;
import com.neec.fct.ecopontos.recyclerviewgridlayoutmanager.AutoFitGridLayoutManager;
import com.neec.fct.ecopontos.recyclerviewgridlayoutmanager.DataModel;
import com.neec.fct.ecopontos.recyclerviewgridlayoutmanager.RecyclerViewAdapter;

import java.util.ArrayList;

public class Selector extends Fragment implements RecyclerViewAdapter.ItemListener {


    Context thiscontext;


    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();
        View view =  inflater.inflate(R.layout.selector, null);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel("Papel ou Cartão", R.drawable.textdocuments, "#09A9FF"));
        arrayList.add(new DataModel("Plástico ou Metal", R.drawable.bag , "#3E51B1"));
        arrayList.add(new DataModel("Vidro", R.drawable.fragile, "#673BB7"  ));
        arrayList.add(new DataModel("Resíduo Perigoso", R.drawable.dangerouscan, "#4BAA50"));
        arrayList.add(new DataModel("Resíduo de Elétricos", R.drawable.circuitboard, "#F94336"));
        arrayList.add(new DataModel("Lâmpada", R.drawable.lamp, "#09A9FF"));
        arrayList.add(new DataModel("Óleo lubrificante", R.drawable.diesel, "#3E51B1"));
        arrayList.add(new DataModel("Mobiliário", R.drawable.couch, "#673BB7"));
        arrayList.add(new DataModel("Resíduo Orgânico", R.drawable.apple, "#4BAA50"));


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), 500);
        recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        /*GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);*/
        return  view;
    }

    @Override
    public void onItemClick(DataModel item) {

        System.out.println(item.toString());

    }

    }





