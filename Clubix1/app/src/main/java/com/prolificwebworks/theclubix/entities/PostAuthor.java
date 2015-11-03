package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Akki on 10/27/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostAuthor {


    private String authour_name;

    private String ID;

    public String getAuthour_name ()
    {
        return authour_name;
    }

    public void setAuthour_name (String authour_name)
    {
        this.authour_name = authour_name;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [authour_name = "+authour_name+", ID = "+ID+"]";
    }
}
