package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by vaibhav on 9/10/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllEvents
{
    private String message;

    private List<EventData> postData;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public List<EventData> getPostData ()
    {
        return postData;
    }

    public void setPostData (List<EventData> postData)
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
			
			
