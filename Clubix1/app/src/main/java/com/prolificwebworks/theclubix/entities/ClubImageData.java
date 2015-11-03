package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Akki on 10/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubImageData {


    private String club_image;

    public String getClub_image() {
        return club_image;
    }

    public void setClub_image(String club_image) {
        this.club_image = club_image;
    }

    @Override
    public String toString() {
        return "ClassPojo [Club_image = " + club_image + "]";
    }
}
