package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Akki on 10/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeImageData {


    private List<String> header_image;

    public List<String> getHeader_image() {
        return header_image;
    }

    public void setHeader_image(List<String> header_image) {
        this.header_image = header_image;
    }

    @Override
    public String toString() {
        return "ClassPojo [header_image = " + header_image + "]";
    }
}
