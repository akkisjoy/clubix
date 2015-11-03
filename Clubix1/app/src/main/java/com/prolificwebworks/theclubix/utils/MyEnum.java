package com.prolificwebworks.theclubix.utils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.prolificwebworks.theclubix.entities.EventData;
import com.prolificwebworks.theclubix.entities.RestaurantData;

import java.util.List;
import java.util.Set;


/**
 * Created by vaibhav on 10/10/15.
 */
public enum MyEnum {


    INSTANCE;

    public List<EventData> today, tomorrow, later;
    public List<RestaurantData> clubRestaurant, loungeRestuarant, pubRestaurant, allRestaurant;

    public Set<String> club, artist;

    public List<EventData> getToday() {
        return today;
    }

    public void setToday(List<EventData> today) {
        this.today = today;
    }

    public List<EventData> getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(List<EventData> tomorrow) {
        this.tomorrow = tomorrow;
    }

    public List<EventData> getLater() {
        return later;
    }

    public void setLater(List<EventData> later) {
        this.later = later;
    }

    public Set<String> getClub() {
        return club;
    }

    public void setClub(Set<String> club) {
        this.club = club;
    }

    public Set<String> getArtist() {
        return artist;
    }

    public void setArtist(Set<String> artist) {
        this.artist = artist;
    }

    public void setRestaurant(List<RestaurantData> restaurant) {
        allRestaurant = restaurant;
        clubRestaurant = Lists.newArrayList(Iterables.filter(restaurant, clubRestaurantPredicate));
        loungeRestuarant = Lists.newArrayList(Iterables.filter(restaurant, loungeRestaurantPredicate));
        pubRestaurant = Lists.newArrayList(Iterables.filter(restaurant, pubRestaurantPredicate));


    }

    private Predicate<RestaurantData> clubRestaurantPredicate = new Predicate<RestaurantData>() {
        @Override
        public boolean apply(RestaurantData input) {

            return input.getRestaurant_type().get(0).getName().contains("Club");
        }
    };

    private Predicate<RestaurantData> loungeRestaurantPredicate = new Predicate<RestaurantData>() {
        @Override
        public boolean apply(RestaurantData input) {

            return input.getRestaurant_type().get(0).getName().equalsIgnoreCase(RestaurantType.LOUNGE.name());
        }
    };

    private Predicate<RestaurantData> pubRestaurantPredicate = new Predicate<RestaurantData>() {
        @Override
        public boolean apply(RestaurantData input) {

            return input.getRestaurant_type().get(0).getName().equalsIgnoreCase(RestaurantType.PUB.name());
        }
    };


    public List<RestaurantData> getRestaurant(RestaurantType restaurantType) {
        if (restaurantType == RestaurantType.CLUB)
            return clubRestaurant;
        else if (restaurantType == RestaurantType.LOUNGE)
            return loungeRestuarant;
        else if (restaurantType == RestaurantType.PUB)
            return pubRestaurant;
        else
            return null;


    }

}
