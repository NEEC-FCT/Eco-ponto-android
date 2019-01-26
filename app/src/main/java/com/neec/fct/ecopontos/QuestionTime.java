package com.neec.fct.ecopontos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.neec.fct.ecopontos.Requests.GetPergunta;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionTime extends AppCompatActivity {

    JSONObject jsonResponse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String QRCODE = getIntent().getStringExtra("QRCODE");

        setContentView(R.layout.question);

        TextView question = findViewById(R.id.question);
        Button A = findViewById(R.id.button1);
        Button B = findViewById(R.id.button3);
        Button C = findViewById(R.id.button2);
        Button D = findViewById(R.id.button4);

        //pedido do servidor
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonResponse = new JSONObject(response);
                    Log.d("JSON", jsonResponse.toString());

                    question.setText(jsonResponse.getString("pergunta"));
                    A.setText(jsonResponse.getString("A"));
                    B.setText(jsonResponse.getString("B"));
                    C.setText(jsonResponse.getString("C"));
                    D.setText(jsonResponse.getString("D"));

                    A.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                         System.out.println("Clicado em A");

                        }
                    });


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
