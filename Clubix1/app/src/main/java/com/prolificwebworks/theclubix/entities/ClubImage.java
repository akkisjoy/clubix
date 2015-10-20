package com.prolificwebworks.theclubix.entities;

import java.util.List;

/**
 * Created by Akki on 10/11/2015.
 */
public class ClubImage {

    private String message;

    private List<ClubImageData> postData;

    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClubImageData> getPostData() {
        return postData;
    }

    public void setPostData(List<ClubImageData> postData) {
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
