package info.androidhive.bottomnavigation;

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

import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.bottomnavigation.Requests.GetPergunta;
import info.androidhive.bottomnavigation.Requests.ResponderPergunta;

public class QuestionTime extends AppCompatActivity {

    JSONObject jsonResponse;
    private String QRCODE;
    private String idPergunta;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QRCODE = getIntent().getStringExtra("QRCODE");

        setContentView(R.layout.question);

        final TextView question = findViewById(R.id.question);
        final Button A = findViewById(R.id.button1);
        final Button B = findViewById(R.id.button3);
        final Button C = findViewById(R.id.button2);
        final Button D = findViewById(R.id.button4);

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


    public void ResponderPergunta(String resposta) {
        //pedido do servidor
        // Response received from the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("JSON", response);
                    jsonResponse = new JSONObject(response);
                    Log.d("JSON", jsonResponse.toString());
                    if (jsonResponse.getBoolean("sucess")) {
                        System.out.println("CORRECTO!!!!");
                        Intent intent = new Intent(QuestionTime.this, Correct.class);
                        startActivity(intent);
                    } else {
                        System.out.println("ERRADO!!!!");
                        Intent intent = new Intent(QuestionTime.this, Wrong.class);
                        startActivity(intent);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ResponderPergunta responderPergunta = new ResponderPergunta(QRCODE, resposta, idPergunta, getApplicationContext(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(QuestionTime.this);
        queue.add(responderPergunta);
    }


}
