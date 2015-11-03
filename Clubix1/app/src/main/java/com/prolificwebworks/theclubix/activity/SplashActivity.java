package com.prolificwebworks.theclubix.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.fragment.Splash1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Akki on 10/7/2015.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);

        try{
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.prolificwebworks.theclubix", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

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
