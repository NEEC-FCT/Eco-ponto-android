package com.neec.fct.ecopontos.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neec.fct.ecopontos.R;

public class PaperChoosed extends Fragment {


    Context thiscontext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();

        View view =  inflater.inflate(R.layout.choicepaper, null);

        return view;

    }


}