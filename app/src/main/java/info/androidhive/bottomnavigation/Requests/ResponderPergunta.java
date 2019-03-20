package info.androidhive.bottomnavigation.Requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ResponderPergunta extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://ecoapp.neec-fct.com/responderPergunta.php";
    private Map<String,String> params;
    public static final String MY_PREFS_NAME = "DATA";

    public ResponderPergunta(String QRCode, String resposta , String idPergunta , Context context, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        //oauth
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Log.d("Oauth",prefs.getString("email", null));
        Log.d("Oauth",prefs.getString("token", null));
        params.put("email", prefs.getString("email", null) );
        params.put("token", prefs.getString("token", null));
        params.put("QRCode", QRCode);
        params.put("resposta", resposta);
        params.put("idPergunta" , idPergunta);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
