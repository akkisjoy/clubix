package com.prolificwebworks.theclubix.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.prolificwebworks.theclubix.fragment.RestaurantList;
import com.prolificwebworks.theclubix.utils.RestaurantType;

/**
 * Created by vaibhav on 25/10/15.
 */
public class RestaurantPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{RestaurantType.CLUB.name(), RestaurantType.LOUNGE.name(), RestaurantType.PUB.name()};
    Context context;


    public RestaurantPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {

        RestaurantList fragment;

        switch (position) {
            case 0:
                fragment = new RestaurantList();
                fragment.setRestaurantType(RestaurantType.CLUB);

                break;
            case 1:
                fragment = new RestaurantList();
                fragment.setRestaurantType(RestaurantType.LOUNGE);
                break;
            case 2:
                fragment = new RestaurantList();
                fragment.setRestaurantType(RestaurantType.PUB);
                break;
            default:
                fragment = new RestaurantList();
                fragment.setRestaurantType(RestaurantType.CLUB);
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
