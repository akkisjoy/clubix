package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by vaibhav on 25/10/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    private String message;

    List<RestaurantData> postData;

    public List<RestaurantData> getPostData() {
        return postData;
    }

    public void setPostData(List<RestaurantData> postData) {
        this.postData = postData;
    }

    private String status;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
