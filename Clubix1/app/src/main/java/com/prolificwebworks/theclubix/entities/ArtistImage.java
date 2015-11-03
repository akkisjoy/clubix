package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Akki on 10/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ArtistImage {

    private String message;

    private List<ArtistImageData> postData;

    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ArtistImageData> getPostData() {
        return postData;
    }

    public void setPostData(List<ArtistImageData> postData) {
        this.postData = postData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", postData = " + postData + ", status = " + status + "]";
    }
}
