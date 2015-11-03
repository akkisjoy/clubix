package com.prolificwebworks.theclubix.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificwebworks.theclubix.R;
import com.prolificwebworks.theclubix.adapter.RestaurantPagerAdapter;


public class RestaurantViewPager extends Fragment {

    public static RestaurantViewPager newInstance(String param1, String param2) {
        RestaurantViewPager fragment = new RestaurantViewPager();

        return fragment;
    }

    public RestaurantViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.event_main, container, false);

        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        viewPager.setAdapter(new RestaurantPagerAdapter(getActivity().getSupportFragmentManager(),
                getActivity()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);


        return v;
    }


}
