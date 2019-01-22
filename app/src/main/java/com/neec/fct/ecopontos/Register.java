package com.neec.fct.ecopontos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.neec.fct.ecopontos.R;
import com.neec.fct.ecopontos.Requests.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.PatternSyntaxException;

public class Register extends AppCompatActivity {


    ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);
        EditText username = findViewById(R.id.email);
        EditText passwrod = findViewById(R.id.password);
        CheckBox privacy = findViewById(R.id.ch)
        Button registar = findViewById(R.id.sign_up_button);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = username.getText().toString();
                String pass = passwrod.getText().toString();


                 if(!privacy.isChecked()){

                    AlertDialog.Builder bulder = new AlertDialog.Builder(Register.this);

                    bulder.setMessage("Tem de aceitar a Política de privacidade")
                            .setNegativeButton("OK", null)
                            .create()
                            .show();

                }

                else{

                if (pass.length() <= 5) {
                    AlertDialog.Builder burlder = new AlertDialog.Builder(Register.this);

                    burlder.setMessage("A Password tem de ter no minimo 6 letras")
                            .setNegativeButton("Tenta novamente", null)
                            .create()
                            .show();


                } else {

                    if (pass.isEmpty() || email.isEmpty()  ) {

                        AlertDialog.Builder burrlder = new AlertDialog.Builder(Register.this);

                        burrlder.setMessage("Não pode existir campos vazios")
                                .setNegativeButton("Tenta novamente", null)
                                .create()
                                .show();

                    } else {

                        try {


                                progress = ProgressDialog.show(Register.this, "Loading..",
                                        "Verificando", true);


                                Response.Listener<String> responListerner = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {

                                            JSONObject jsonOResponse = new JSONObject(response);
                                            boolean sucess = jsonOResponse.getBoolean("success");
                                            progress.dismiss();
                                            if (sucess) {
                                                Toast.makeText(getApplicationContext(), "Registado com Sucesso", Toast.LENGTH_SHORT).show();
                                                SystemClock.sleep(730);
                                                Intent intent = new Intent(Register.this, LoginActivity.class);
                                                Register.this.startActivity(intent);
                                            } else {
                                                AlertDialog.Builder bulder = new AlertDialog.Builder(Register.this);

                                                bulder.setMessage("Falha no Registo,Email em uso")
                                                        .setNegativeButton("Tenta novamente", null)
                                                        .create()
                                                        .show();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };


                                RegisterRequest registerrequest = new RegisterRequest( email, pass ,  responListerner);
                                RequestQueue queue = Volley.newRequestQueue(Register.this);
                                queue.add(registerrequest);


                        } catch (PatternSyntaxException e) {
                            AlertDialog.Builder burlder = new AlertDialog.Builder(Register.this);

                            burlder.setMessage("Não pode existir Caracteres Especiais ")
                                    .setNegativeButton("Tenta novamente", null)
                                    .create()
                                    .show();


                        }


                    }
                }
            }
            }
        });





    }
}