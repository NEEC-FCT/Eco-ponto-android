package com.neec.fct.ecopontos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Loading extends AppCompatActivity {


    private static  int SPLASH_TIME_OUT = 1700;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_actibity);



        new Handler().postDelayed(new  Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(Loading.this,MainActivity.class);
                startActivity(homeIntent);
                this.finish();
            }
            protected  void finish(){

            }
        },SPLASH_TIME_OUT);





    }
}
