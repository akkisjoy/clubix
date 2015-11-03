package com.prolificwebworks.theclubix.entities;

/**
 * Created by Akki on 10/31/2015.
 */
public class EventArtist {

    private String artist_title;

    private String artist_id;

    public String getArtist_title ()
    {
        return artist_title;
    }

    public void setArtist_title (String artist_title)
    {
        this.artist_title = artist_title;
    }

    public String getArtist_id ()
    {
        return artist_id;
    }

    public void setArtist_id (String artist_id)
    {
        this.artist_id = artist_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [artist_title = "+artist_title+", artist_id = "+artist_id+"]";
    }
}
