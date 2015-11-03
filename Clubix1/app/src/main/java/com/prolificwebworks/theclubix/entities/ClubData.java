package com.prolificwebworks.theclubix.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by vaibhav on 10/10/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClubData {

    private String Bookingstarting_time;

    private String phone_number;

    private String happyending_time;

    private String featured;

    private RestaurantType[] restaurant_type;

    private String geolocation_formatted_address;

    private String city;

    private String connect_gallery_from_drop_down_menu;

    private String post_content;

    private String[] followers;

    private String longitude;


    private String[] restaurant_cuisine_type;

    private String application;

    private String geolocated;

    private String restaurant_expires;

    private String postId;

    private String happystarting_time;

    private String Bookingending_time;

    private String post_author;

    private String restaurant_tagline;

    private String latitude;

    private String geolocation_country_long;

    private String geolocation_country_short;

    private String restaurant_twitter;

    private String restaurant_logo;

    private String email_custom;

    private String post_title;

    private String restaurant_website;

    private String restaurant_name;

    private String post_date;

    public String getBookingstarting_time ()
    {
        return Bookingstarting_time;
    }

    public void setBookingstarting_time (String Bookingstarting_time)
    {
        this.Bookingstarting_time = Bookingstarting_time;
    }

    public String getPhone_number ()
    {
        return phone_number;
    }

    public void setPhone_number (String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getHappyending_time ()
    {
        return happyending_time;
    }

    public void setHappyending_time (String happyending_time)
    {
        this.happyending_time = happyending_time;
    }

    public String getFeatured ()
    {
        return featured;
    }

    public void setFeatured (String featured)
    {
        this.featured = featured;
    }

    public RestaurantType[] getRestaurant_type ()
    {
        return restaurant_type;
    }

    public void setRestaurant_type (RestaurantType[] restaurant_type)
    {
        this.restaurant_type = restaurant_type;
    }

    public String getGeolocation_formatted_address ()
    {
        return geolocation_formatted_address;
    }

    public void setGeolocation_formatted_address (String geolocation_formatted_address)
    {
        this.geolocation_formatted_address = geolocation_formatted_address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getConnect_gallery_from_drop_down_menu ()
    {
        return connect_gallery_from_drop_down_menu;
    }

    public void setConnect_gallery_from_drop_down_menu (String connect_gallery_from_drop_down_menu)
    {
        this.connect_gallery_from_drop_down_menu = connect_gallery_from_drop_down_menu;
    }

    public String getPost_content ()
    {
        return post_content;
    }

    public void setPost_content (String post_content)
    {
        this.post_content = post_content;
    }

    public String[] getFollowers ()
    {
        return followers;
    }

    public void setFollowers (String[] followers)
    {
        this.followers = followers;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String[] getRestaurant_cuisine_type ()
    {
        return restaurant_cuisine_type;
    }

    public void setRestaurant_cuisine_type (String[] restaurant_cuisine_type)
    {
        this.restaurant_cuisine_type = restaurant_cuisine_type;
    }

    public String getApplication ()
    {
        return application;
    }

    public void setApplication (String application)
    {
        this.application = application;
    }

    public String getGeolocated ()
    {
        return geolocated;
    }

    public void setGeolocated (String geolocated)
    {
        this.geolocated = geolocated;
    }

    public String getRestaurant_expires ()
    {
        return restaurant_expires;
    }

    public void setRestaurant_expires (String restaurant_expires)
    {
        this.restaurant_expires = restaurant_expires;
    }

    public String getPostId ()
    {
        return postId;
    }

    public void setPostId (String postId)
    {
        this.postId = postId;
    }

    public String getHappystarting_time ()
    {
        return happystarting_time;
    }

    public void setHappystarting_time (String happystarting_time)
    {
        this.happystarting_time = happystarting_time;
    }

    public String getBookingending_time ()
    {
        return Bookingending_time;
    }

    public void setBookingending_time (String Bookingending_time)
    {
        this.Bookingending_time = Bookingending_time;
    }

    public String getPost_author ()
    {
        return post_author;
    }

    public void setPost_author (String post_author)
    {
        this.post_author = post_author;
    }

    public String getRestaurant_tagline ()
    {
        return restaurant_tagline;
    }

    public void setRestaurant_tagline (String restaurant_tagline)
    {
        this.restaurant_tagline = restaurant_tagline;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getGeolocation_country_long ()
    {
        return geolocation_country_long;
    }

    public void setGeolocation_country_long (String geolocation_country_long)
    {
        this.geolocation_country_long = geolocation_country_long;
    }

    public String getGeolocation_country_short ()
    {
        return geolocation_country_short;
    }

    public void setGeolocation_country_short (String geolocation_country_short)
    {
        this.geolocation_country_short = geolocation_country_short;
    }

    public String getRestaurant_twitter ()
    {
        return restaurant_twitter;
    }

    public void setRestaurant_twitter (String restaurant_twitter)
    {
        this.restaurant_twitter = restaurant_twitter;
    }

    public String getRestaurant_logo ()
    {
        return restaurant_logo;
    }

    public void setRestaurant_logo (String restaurant_logo)
    {
        this.restaurant_logo = restaurant_logo;
    }

    public String getEmail_custom ()
    {
        return email_custom;
    }

    public void setEmail_custom (String email_custom)
    {
        this.email_custom = email_custom;
    }

    public String getPost_title ()
    {
        return post_title;
    }

    public void setPost_title (String post_title)
    {
        this.post_title = post_title;
    }

    public String getRestaurant_website ()
    {
        return restaurant_website;
    }

    public void setRestaurant_website (String restaurant_website)
    {
        this.restaurant_website = restaurant_website;
    }

    public String getRestaurant_name ()
    {
        return restaurant_name;
    }

    public void setRestaurant_name (String restaurant_name)
    {
        this.restaurant_name = restaurant_name;
    }

    public String getPost_date ()
    {
        return post_date;
    }

    public void setPost_date (String post_date)
    {
        this.post_date = post_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Bookingstarting_time = "+Bookingstarting_time+", phone_number = "+phone_number+", happyending_time = "+happyending_time+", featured = "+featured+", restaurant_type = "+restaurant_type+", geolocation_formatted_address = \"+geolocation_formatted_address+\", city = \"+city+\", connect_gallery_from_drop_down_menu = \"+connect_gallery_from_drop_down_menu+\", post_content = \"+post_content+\", followers = \"+followers+\", longitude = \"+longitude+\", restaurant_additional_info = \"+restaurant_additional_info+\", restaurant_cuisine_type = "+restaurant_cuisine_type+", application = "+application+", geolocated = "+geolocated+", restaurant_expires = "+restaurant_expires+", postId = "+postId+", happystarting_time = "+happystarting_time+", Bookingending_time = "+Bookingending_time+", post_author = "+post_author+", restaurant_tagline = "+restaurant_tagline+", latitude = "+latitude+", geolocation_country_long = "+geolocation_country_long+", geolocation_country_short = "+geolocation_country_short+", restaurant_twitter = "+restaurant_twitter+", restaurant_logo = "+restaurant_logo+", email_custom = "+email_custom+", post_title = "+post_title+", restaurant_website = "+restaurant_website+", restaurant_name = "+restaurant_name+", post_date = "+post_date+"]";
    }
}
