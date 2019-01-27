package com.neec.fct.ecopontos.Requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class GetStatus extends StringRequest {

    private static final String REGISTER_LOGIN_URL = "https://ecoapp.neec-fct.com/getPergunta.php";
    private Map<String,String> params;
    public static final String MY_PREFS_NAME = "DATA";

    public GetStatus( Response.Listener<String> listener , Context context){
        super(Request.Method.POST,REGISTER_LOGIN_URL,listener,null);
        params = new HashMap<>();


        //oauth
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Log.d("Oauth",prefs.getString("email", null));
        Log.d("Oauth",prefs.getString("token", null));
        params.put("email", prefs.getString("email", null) );
        params.put("token", prefs.getString("token", null));


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
