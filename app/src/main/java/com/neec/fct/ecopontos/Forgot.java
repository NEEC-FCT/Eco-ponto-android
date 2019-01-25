package com.neec.fct.ecopontos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.neec.fct.ecopontos.Requests.EsqueceuRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class Forgot extends AppCompatActivity {



    ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgot);

        Button back = findViewById(R.id.btn_back);
        Button rest = findViewById(R.id.btn_reset_password);
        EditText email =  findViewById(R.id.email);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailS =email.getText().toString();

                if(!emailS.isEmpty()){
                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            progress = ProgressDialog.show(Forgot.this, getString(R.string.loading),
                                    getString(R.string.verificando), true);

                            JSONObject jsonResponse = new JSONObject(response);
                            Log.d("JSON", jsonResponse.toString());
                            boolean success = jsonResponse.getBoolean("success");
                            progress.dismiss();

                            if (success) {


                                Intent intent = new Intent(Forgot.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                Forgot.this.startActivity(intent);

                            } else {
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Forgot.this);
                                builder.setMessage(R.string.email_inexistente)
                                        .setNegativeButton(R.string.tente_novamente, null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EsqueceuRequest esqueceu = new EsqueceuRequest( emailS,  responseListener);
                RequestQueue queue = Volley.newRequestQueue(Forgot.this);
                queue.add(esqueceu);

            }
                else{
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Forgot.this);
                    builder.setMessage(R.string.Email_empty)
                            .setNegativeButton(R.string.tente_novamente, null)
                            .create()
                            .show();
                }

            }

        });


    }
}