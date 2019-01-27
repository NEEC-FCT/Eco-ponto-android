package com.neec.fct.ecopontos;

import android.content.Context;
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
import com.neec.fct.ecopontos.Requests.ResponderPergunta;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionTime extends AppCompatActivity {

    JSONObject jsonResponse;
    private String QRCODE;
    private String idPergunta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         QRCODE = getIntent().getStringExtra("QRCODE");

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
                    Log.d("JSON", response);
                    jsonResponse = new JSONObject(response);
                    Log.d("JSON", jsonResponse.toString());

                    question.setText(jsonResponse.getString("pergunta"));
                    A.setText(jsonResponse.getString("A"));
                    B.setText(jsonResponse.getString("B"));
                    C.setText(jsonResponse.getString("C"));
                    D.setText(jsonResponse.getString("D"));
                    idPergunta = jsonResponse.getString("id");

                    A.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ResponderPergunta("1");

                        }
                    });

                    B.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ResponderPergunta("2");

                        }
                    });
                    C.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ResponderPergunta("3");

                        }
                    });
                    D.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            ResponderPergunta("4");

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



    public void ResponderPergunta(String resposta){
        //pedido do servidor
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("JSON", response);
                    jsonResponse = new JSONObject(response);
                    Log.d("JSON", jsonResponse.toString());
                    if(jsonResponse.getBoolean("sucess")){
                        System.out.println("CORRECTO!!!!");
                        Intent intent = new Intent(QuestionTime.this, Correct.class);
                        startActivity(intent);
                    }
                    else {
                        System.out.println("ERRADO!!!!");
                        Intent intent = new Intent(QuestionTime.this, Wrong.class);
                        startActivity(intent);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ResponderPergunta responderPergunta = new ResponderPergunta( QRCODE ,resposta, idPergunta , getApplicationContext(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(QuestionTime.this);
        queue.add(responderPergunta);
    }


}
