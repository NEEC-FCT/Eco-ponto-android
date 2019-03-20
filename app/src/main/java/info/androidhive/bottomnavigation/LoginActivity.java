package info.androidhive.bottomnavigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import info.androidhive.bottomnavigation.Requests.LoginRequest;


public class LoginActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "DATA";
    ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences editorr = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        if (editorr.contains("token")) {
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(myIntent);
        }


        setContentView(R.layout.login);

        TextView signup = findViewById(R.id.btn_signup);
        final TextView email = findViewById(R.id.email);
        final TextView passw = findViewById(R.id.password);
        Button Blogin = findViewById(R.id.btn_login);
        Button reset = findViewById(R.id.btn_reset_password);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(LoginActivity.this, Forgot.class);
                startActivity(myIntent);

            }
        });

        Blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailS = email.getText().toString();
                String passS = passw.getText().toString();
                if (emailS.isEmpty() || passS.isEmpty()) {

                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("title")
                            .setMessage("message")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();

                } else {

                    //enviar pedido
                    progress = ProgressDialog.show(LoginActivity.this, "Loading..",
                            "Verificando", true);

                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                Log.d("JSON", jsonResponse.toString());
                                boolean success = jsonResponse.getBoolean("success");
                                progress.dismiss();

                                if (success) {


                                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                    editor.putString("token", jsonResponse.getString("token"));
                                    editor.putString("email", jsonResponse.getString("email"));
                                    editor.apply();

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    LoginActivity.this.startActivity(intent);

                                } else {
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Login Falhou")
                                            .setNegativeButton("Tentar Novamente", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(emailS, passS, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(loginRequest);

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(LoginActivity.this, Register.class);
                startActivity(myIntent);

            }
        });


    }
}