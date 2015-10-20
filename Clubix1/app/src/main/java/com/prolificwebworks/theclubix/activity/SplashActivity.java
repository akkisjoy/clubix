package com.prolificwebworks.theclubix.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.fragment.Splash1;
import com.prolificwebworks.theclubix.fragment.Splash2;
import com.prolificwebworks.theclubix.fragment.Splash3;
import com.prolificwebworks.theclubix.intraface.OnClickInterface;

/**
 * Created by Akki on 10/7/2015.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);


        Splash1 fragment = new Splash1();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameSplash, fragment);
        fragmentTransaction.commit();
//        fragment.setInterface(new OnClickInterface() {
//            @Override
//            public void buttonClicked(View v) {
//                Splash2 splash2 = new Splash2();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frameSplash, splash2);
//                transaction.addToBackStack(null);
//                transaction.commit();
//                splash2.setInterface(new OnClickInterface() {
//                    @Override
//                    public void buttonClicked(View v) {
//                        Splash3 splash3 = new Splash3();
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.frameSplash, splash3);
//                        transaction.addToBackStack(null);
//                        transaction.commit();
//                    }
//                });
//            }
//        });
    }
}
