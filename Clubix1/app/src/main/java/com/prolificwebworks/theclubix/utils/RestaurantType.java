package com.prolificwebworks.theclubix.utils;

/**
 * Created by vaibhav on 25/10/15.
 */
public enum  RestaurantType {

    CLUB("Club"),
    LOUNGE("Lounge"),
    PUB("Pub");


    private String event;

    RestaurantType(String eventTime) {
        event = eventTime.toLowerCase();
    }
}
