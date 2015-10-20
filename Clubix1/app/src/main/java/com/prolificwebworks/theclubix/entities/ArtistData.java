package com.prolificwebworks.theclubix.entities;

import java.util.List;

/**
 * Created by vaibhav on 9/10/15.
 */
public class ArtistData
{
    private String background_image;

    private String artist_events;

    private List<String> followers;

    private String featured_image;

    private String post_author;

    private String artist_albums;

    private String post_title;

    private String postId;

    private String post_date;

    private String post_content;

    public String getBackground_image ()
    {
        return background_image;
    }

    public void setBackground_image (String background_image)
    {
        this.background_image = background_image;
    }

    public String getArtist_events ()
    {
        return artist_events;
    }

    public void setArtist_events (String artist_events)
    {
        this.artist_events = artist_events;
    }

    public List<String> getFollowers ()
    {
        return followers;
    }

    public void setFollowers (List<String> followers)
    {
        this.followers = followers;
    }

    public String getFeatured_image ()
    {
        return featured_image;
    }

    public void setFeatured_image (String featured_image)
    {
        this.featured_image = featured_image;
    }

    public String getPost_author ()
    {
        return post_author;
    }

    public void setPost_author (String post_author)
    {
        this.post_author = post_author;
    }

    public String getArtist_albums ()
    {
        return artist_albums;
    }

    public void setArtist_albums (String artist_albums)
    {
        this.artist_albums = artist_albums;
    }

    public String getPost_title ()
    {
        return post_title;
    }

    public void setPost_title (String post_title)
    {
        this.post_title = post_title;
    }

    public String getPostId ()
    {
        return postId;
    }

    public void setPostId (String postId)
    {
        this.postId = postId;
    }

    public String getPost_date ()
    {
        return post_date;
    }

    public void setPost_date (String post_date)
    {
        this.post_date = post_date;
    }

    public String getPost_content ()
    {
        return post_content;
    }

    public void setPost_content (String post_content)
    {
        this.post_content = post_content;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [background_image = "+background_image+", artist_events = "+artist_events+", followers = "+followers+", featured_image = "+featured_image+", post_author = "+post_author+", artist_albums = "+artist_albums+", post_title = "+post_title+", postId = "+postId+", post_date = "+post_date+", post_content = "+post_content+"]";
    }
}
