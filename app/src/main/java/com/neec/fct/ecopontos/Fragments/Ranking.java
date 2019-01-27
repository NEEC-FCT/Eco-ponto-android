package com.neec.fct.ecopontos.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.neec.fct.ecopontos.Correct;
import com.neec.fct.ecopontos.ListAdapter;
import com.neec.fct.ecopontos.QuestionTime;
import com.neec.fct.ecopontos.R;
import com.neec.fct.ecopontos.Requests.GetStatus;
import com.neec.fct.ecopontos.Requests.ResponderPergunta;
import com.neec.fct.ecopontos.Wrong;

import org.json.JSONException;
import org.json.JSONObject;

public class Ranking extends Fragment {


    Context thiscontext;
    int[] images = {R.drawable.first, R.drawable.second , R.drawable.medal , R.drawable.medal, R.drawable.medal, R.drawable.medal, R.drawable.medal, R.drawable.medal, R.drawable.medal, R.drawable.medal};

    String[] version = new String[10];

    ListView lView;

    ListAdapter lAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thiscontext = container.getContext();

        View view =  inflater.inflate(R.layout.ranking, null);

        //listview
        lView = (ListView) view.findViewById(R.id.androidList);
        TextView name = view.findViewById(R.id.name);
        TextView pontos = view.findViewById(R.id.pontos);
        TextView posicao = view.findViewById(R.id.posicao);



        //pedido do servidor
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("JSON", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    Log.d("JSON", jsonResponse.toString());
                    name.setText( getString(R.string.name) + jsonResponse.getString("nome"));
                    pontos.setText( getString(R.string.pontos) + jsonResponse.getString("pontos"));
                    posicao.setText(getString(R.string.posicao) + jsonResponse.getString("pos"));


                    version[0] = jsonResponse.getString("1");
                    version[1] = jsonResponse.getString("2");
                    version[2] = jsonResponse.getString("3");
                    version[3] = jsonResponse.getString("4");
                    version[4] = jsonResponse.getString("5");
                    version[5] = jsonResponse.getString("6");
                    version[6] = jsonResponse.getString("7");
                    version[7] = jsonResponse.getString("8");
                    version[8] = jsonResponse.getString("9");
                    version[9] = jsonResponse.getString("10");

                    lAdapter = new ListAdapter(getContext(), version, images);

                    lView.setAdapter(lAdapter);

                    lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Toast.makeText(getContext(), version[i], Toast.LENGTH_SHORT).show();

                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        GetStatus responderPergunta = new GetStatus(  responseListener  , getContext()) ;
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(responderPergunta);



        return view;

    }

}