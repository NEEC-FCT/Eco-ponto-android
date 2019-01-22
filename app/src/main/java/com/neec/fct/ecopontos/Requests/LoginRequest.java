package com.neec.fct.ecopontos.Requests;

/**
 * Created by joaoveloso on 29/01/17.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class LoginRequest extends StringRequest{

    private static final String REGISTER_LOGIN_URL = "https://ecoapp.neec-fct.com/Login.php";
    private Map<String,String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Method.POST,REGISTER_LOGIN_URL,listener,null);
        params = new HashMap<>();

        params.put("username",username);
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
