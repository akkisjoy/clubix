package com.prolificwebworks.theclubix.entities;

import java.util.List;

/**
 * Created by vaibhav on 10/10/15.
 */
public class Club {

    private String message;

    private List<ClubData> postData;

    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClubData> getPostData() {
        return postData;
    }

    public void setPostData(List<ClubData> postData) {
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
