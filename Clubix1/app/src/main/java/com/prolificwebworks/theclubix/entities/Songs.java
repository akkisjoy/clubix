package com.prolificwebworks.theclubix.entities;

import java.util.List;

/**
 * Created by vaibhav on 9/10/15.
 */
public class Songs
{
    private String message;

    private List<SongsData> postData;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public List<SongsData> getPostData ()
    {
        return postData;
    }

    public void setPostData (List<SongsData> postData)
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
