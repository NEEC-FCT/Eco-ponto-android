package info.androidhive.bottomnavigation.Requests;

/**
 * Created by Veloso on 14/08/2017.
 */


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class EsqueceuRequest extends StringRequest {

    private static final String REGISTER_LOGIN_URL = "https://ecoapp.neec-fct.com/recoverRequest.php";
    private Map<String,String> params;

    public EsqueceuRequest(String email, Response.Listener<String> listener){
        super(Method.POST,REGISTER_LOGIN_URL,listener,null);
        params = new HashMap<>();

        params.put("email",email);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
