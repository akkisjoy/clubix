package com.prolificwebworks.theclubix.entities;

import java.util.List;

/**
 * Created by vaibhav on 9/10/15.
 */
public class Artist {
    private String message;

    private List<ArtistData> postData;

    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ArtistData> getPostData() {
        return postData;
    }

    public void setPostData(List<ArtistData> postData) {
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