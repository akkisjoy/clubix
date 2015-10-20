package com.prolificwebworks.theclubix.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.prolificwebworks.theclubix.fragment.EventSubSection;
import com.prolificwebworks.theclubix.utils.EventTime;

/**
 * Created by Akki on 10/7/2015.
 */
public class EventPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{EventTime.TODAY.name(), EventTime.TOMORROW.name(), EventTime.LATER.name()};
    Context context;

    public EventPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        //need to change here to add seperate fragment class
        EventSubSection fragment;

        switch (position) {
            case 0:
                fragment = new EventSubSection();
                fragment.setEventTime(EventTime.TODAY);

                break;
            case 1:
                fragment = new EventSubSection();
                fragment.setEventTime(EventTime.TOMORROW);
                break;
            case 2:
                fragment = new EventSubSection();
                fragment.setEventTime(EventTime.LATER);
                break;
            default:
                fragment = new EventSubSection();
                fragment.setEventTime(EventTime.TODAY);
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
