package com.neec.fct.ecopontos.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joaoveloso on 29/01/17.
 */

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "https://ecoapp.neec-fct.com/Register.php";
    private Map<String,String> params;

    public RegisterRequest(String username, String password, Response.Listener<String> listener){
            super(Method.POST,REGISTER_REQUEST_URL,listener,null);
            params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
