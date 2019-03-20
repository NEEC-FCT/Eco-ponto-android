package info.androidhive.bottomnavigation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import info.androidhive.bottomnavigation.Requests.EsqueceuRequest;

public class Forgot extends AppCompatActivity {


    private ProgressDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.forgot);
        dialog = new ProgressDialog(Forgot.this);
        Button back = findViewById(R.id.btn_back);
        Button rest = findViewById(R.id.btn_reset_password);
        final EditText email = findViewById(R.id.email);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setMessage("Verificando,seja paciente");
                dialog.show();
                String emailS = email.getText().toString();

                if (!emailS.isEmpty()) {
                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {


                                JSONObject jsonResponse = new JSONObject(response);
                                Log.d("JSON", jsonResponse.toString());
                                boolean success = jsonResponse.getBoolean("success");
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }

                                if (success) {


                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Forgot.this);
                                    builder.setMessage(R.string.emailenviado)
                                            .setNegativeButton(R.string.ok, null)
                                            .create()
                                            .show();


                                } else {
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Forgot.this);
                                    builder.setMessage(R.string.email_inexistente)
                                            .setNegativeButton(R.string.tente_novamente, null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                                e.printStackTrace();
                            }
                        }
                    };

                    EsqueceuRequest esqueceu = new EsqueceuRequest(emailS, responseListener);
                    esqueceu.setRetryPolicy(new DefaultRetryPolicy(
                            8000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    RequestQueue queue = Volley.newRequestQueue(Forgot.this);
                    queue.add(esqueceu);

                } else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
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