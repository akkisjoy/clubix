package com.prolificwebworks.theclubix.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.activity.HomeActivity;
import com.prolificwebworks.theclubix.intraface.OnClickInterface;
import com.prolificwebworks.theclubix.utils.StaticValue;

/**
 * Created by Akki on 20/7/2025.
 */
public class Splash2 extends Fragment {

    private TextView splashGroove;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_2, container, false);

        splashGroove = (TextView) v.findViewById(R.id.splashGroove);
        splashGroove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getActivity().getSharedPreferences(StaticValue.MyPref, Context.MODE_PRIVATE);
                Log.e("sharedPreferences", sharedPreferences.getString(StaticValue.FbName, ""));
                if (sharedPreferences.getString(StaticValue.FbName, null) != null) {
                    Intent i = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                    getActivity().startActivity(i);
                    getActivity().overridePendingTransition(android.R.anim.fade_in, 0);
                    getActivity().finish();
                } else {
                    Splash3 splash3 = new Splash3();
                    getFragmentManager().beginTransaction().replace(R.id.frameSplash, splash3).commit();
                }
            }
        });
        return v;
    }
}
