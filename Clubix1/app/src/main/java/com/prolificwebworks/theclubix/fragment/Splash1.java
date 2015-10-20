package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.intraface.OnClickInterface;

/**
 * Created by Akki on 10/7/2015.
 */
public class Splash1 extends Fragment{

//    OnClickInterface clickInterface1;
    TextView splashSkip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_1,container,false);
        splashSkip = (TextView) v.findViewById(R.id.splashSkip);
        splashSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                clickInterface1.buttonClicked(v);
                Splash2 splash2 = new Splash2();
                getFragmentManager().beginTransaction().replace(R.id.frameSplash, splash2 ).commit();
            }
        });
        return v;
    }

//    public void setInterface(OnClickInterface clickInterface2){
//        this.clickInterface1 = clickInterface2;
//    }

}
