package com.prolificwebworks.theclubix.entities;

/**
 * Created by Akki on 10/11/2015.
 */
public class ArtistImageData {

    private String artist_image;

    public String getArtist_image ()
    {
        return artist_image;
    }

    public void setArtist_image (String artist_image)
    {
        this.artist_image = artist_image;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [artist_image = "+artist_image+"]";
    }
}
