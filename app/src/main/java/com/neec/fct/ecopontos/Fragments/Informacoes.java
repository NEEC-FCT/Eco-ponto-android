package com.neec.fct.ecopontos.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.neec.fct.ecopontos.R;

public class Informacoes extends Fragment {


    Context thiscontext;
    WebView wb;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();

        View view =  inflater.inflate(R.layout.informacoes, null);

        wb=(WebView)view.findViewById(R.id.webview);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setBuiltInZoomControls(false);
        wb.loadUrl("http://neec-fct.com/ecoinfo/");

        return view;

    }




}
