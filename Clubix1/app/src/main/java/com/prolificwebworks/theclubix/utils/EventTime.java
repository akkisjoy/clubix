package com.prolificwebworks.theclubix.utils;

/**
 * Created by vaibhav on 10/10/15.
 */
public enum EventTime {

    TODAY("Today"),
    TOMORROW("Tomorrow"),
    LATER("Later");


    private String event;

    EventTime(String eventTime) {
        event = eventTime.toLowerCase();
    }

}
