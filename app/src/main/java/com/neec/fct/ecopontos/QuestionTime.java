package com.neec.fct.ecopontos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.neec.fct.ecopontos.Requests.GetPergunta;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionTime extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String QRCODE = getIntent().getStringExtra("QRCODE");

        setContentView(R.layout.activity_home_actibity);

        //pedido do servidor
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    Log.d("JSON", jsonResponse.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        GetPergunta getPergunta = new GetPergunta(QRCODE, responseListener, getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(QuestionTime.this);
        queue.add(getPergunta);


    }
}
