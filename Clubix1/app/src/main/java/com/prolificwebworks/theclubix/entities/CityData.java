package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Akki on 10/26/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityData {

    private String city_id;

    private String city_name;

    private String city_state;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_state() {
        return city_state;
    }

    public void setCity_state(String city_state) {
        this.city_state = city_state;
    }

    @Override
    public String toString() {
        return "ClassPojo [city_id = " + city_id + ", city_name = " + city_name + ", city_state = " + city_state + "]";
    }
}
