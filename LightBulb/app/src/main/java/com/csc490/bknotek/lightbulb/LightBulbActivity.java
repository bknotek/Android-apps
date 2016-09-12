package com.csc490.bknotek.lightbulb;


import android.content.res.Configuration;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.Camera;




public class LightBulbActivity extends AppCompatActivity  {



    private Button onButton, offButton;
    private ImageView lightbulbView,lightswitchView;
    private int bulb_ID = R.drawable.lightbulb_off;
    private int switch_ID = R.drawable.switch_off;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_bulb_vertical);


                if(getResources().getConfiguration().orientation==
                Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_light_bulb_vertical);


        }
        else{
            setContentView(R.layout.activity_light_bulb_horizontal);
        }

        lightbulbView = (ImageView) findViewById(R.id.lightBulb);
        lightswitchView = (ImageView) findViewById(R.id.lightSwitch);



        if (savedInstanceState != null) {
            bulb_ID = savedInstanceState.getInt("bulbID");
            lightbulbView.setImageResource(bulb_ID);
            switch_ID = savedInstanceState.getInt("switchID");
            lightswitchView.setImageResource(switch_ID);

        }
        else{
            lightbulbView.setImageResource(bulb_ID);
            lightswitchView.setImageResource(switch_ID);
            }


        lightswitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_ID==R.drawable.switch_off){
                    turnOn();
                    Toast.makeText(getApplication().getBaseContext(),"ON",Toast.LENGTH_SHORT).show();
                }
                else{
                    turnOff();
                    Toast.makeText(getApplication().getBaseContext(),"OFF",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void turnOn() {
        switch_ID = R.drawable.switch_on;
        lightswitchView.setImageResource(switch_ID);
        bulb_ID = R.drawable.lightbulb_on;
        lightbulbView.setImageResource(bulb_ID);
    }

    private void turnOff() {
        switch_ID = R.drawable.switch_off;
        lightswitchView.setImageResource(switch_ID);
        bulb_ID = R.drawable.lightbulb_off;
        lightbulbView.setImageResource(bulb_ID);
    }

    //saves data and re renders
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("bulbID", bulb_ID);
            outState.putInt("switchID",switch_ID);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        turnOff();
    }



}





