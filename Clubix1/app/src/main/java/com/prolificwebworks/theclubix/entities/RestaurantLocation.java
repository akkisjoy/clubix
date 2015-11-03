package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vaibhav on 27/10/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantLocation {

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
