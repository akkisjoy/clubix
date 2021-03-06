package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Akki on 10/26/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class City {

    private String message;

    private List<CityData> postData;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public List<CityData> getPostData ()
    {
        return postData;
    }

    public void setPostData (List<CityData> postData)
    {
        this.postData = postData;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", postData = "+postData+", status = "+status+"]";
    }
}
