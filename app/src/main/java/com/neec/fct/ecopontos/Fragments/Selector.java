package com.neec.fct.ecopontos.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
        arrayList.add(new DataModel("Plástico ou Metal", R.drawable.bag , "#faff00"));
        arrayList.add(new DataModel("Vidro", R.drawable.fragile, "#05af21"  ));
        arrayList.add(new DataModel("Resíduo Perigoso", R.drawable.dangerouscan, "#4BAA50"));
        arrayList.add(new DataModel("Resíduo de Elétricos", R.drawable.circuitboard, "#F94336"));
        arrayList.add(new DataModel("Lâmpada", R.drawable.lamp, "#09A9FF"));
        arrayList.add(new DataModel("Óleo lubrificante", R.drawable.diesel, "#e5f71b"));
        arrayList.add(new DataModel("Mobiliário", R.drawable.couch, "#673BB7"));
        arrayList.add(new DataModel("Resíduo Orgânico", R.drawable.apple, "#995710"));


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), 500);
        recyclerView.setLayoutManager(layoutManager);


        return  view;
    }

    @Override
    public void onItemClick(DataModel item) {

        System.out.println("item: " + item.text.toString());

        if( item.text.toString().equals("Papel ou Cartão")){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new PaperChoosed())
                    .commit();
        }

        //Residuo perigoso
        //done
        else if( item.text.toString().equals("Resíduo Perigoso")){
            String url = "https://bd2.fct.unl.pt/v7/downloads/dat/ef025_03_entrega_res_perigosos_0118.pdf";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if( item.text.toString().equals("Óleo lubrificante")){
            //i.	Deposite num recipiente estanque e fechado
            String url = "https://bd2.fct.unl.pt/v7/downloads/dat/ef025_03_entrega_res_perigosos_0118.pdf";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        else if( item.text.toString().equals("Mobiliário")){

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Indicações");
            builder.setMessage("Solicite à Direção o abate do equipamento\n" +
                    "Após autorização para o abate solicite a recolha à DAT (pedido de assistência), através do email\n" +
                    "div.at.sg.helpdesk@fct.unl.pt");

            builder.setPositiveButton("Já tenho", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog


                    dialog.dismiss();

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","div.at.sg.helpdesk@fct.unl.pt", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mobiliário abate");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Corpo");
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }
            });

            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

    }

    }





