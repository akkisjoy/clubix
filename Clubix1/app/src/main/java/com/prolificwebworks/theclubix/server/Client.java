package com.prolificwebworks.theclubix.server;

import android.telecom.Call;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.prolificwebworks.theclubix.entities.AllEvents;
import com.prolificwebworks.theclubix.entities.Artist;
import com.prolificwebworks.theclubix.entities.ArtistImage;
import com.prolificwebworks.theclubix.entities.City;
import com.prolificwebworks.theclubix.entities.Club;
import com.prolificwebworks.theclubix.entities.ClubImage;
import com.prolificwebworks.theclubix.entities.FacebookRegister;
import com.prolificwebworks.theclubix.entities.HomeImage;
import com.prolificwebworks.theclubix.entities.Restaurant;
import com.prolificwebworks.theclubix.entities.Songs;
import com.prolificwebworks.theclubix.utils.EventTime;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.JacksonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by vaibhav on 9/10/15.
 */
public enum Client {

    INSTANCE;
    public Requests requests;


    public interface Requests {

        @GET("/all_event_listing.php")
        void getAllEvents(@Query("page") int pageNo, Callback<AllEvents> allEventsCallback);

        @GET("/today_events.php")
        void getTodaysEvents(@Query("eventlist") String eventTime, Callback<AllEvents> allEventsCallback);

        @GET("/tomorrow_events.php")
        void getTomorrowEvents(@Query("eventlist") String eventTime, Callback<AllEvents> allEventsCallback);

        @GET("/later_events.php")
        void getLaterEvents(@Query("eventlist") String eventTime, Callback<AllEvents> allEventsCallback);

        @GET("/single_event.php")
        void getSingleEvent(@Query("eventID") String eventID, Callback<AllEvents> allEventsCallback);

        @GET("/all_artist_listing.php")
        void getAllArtist(Callback<Artist> artistCallback);

        @GET("/single_artist.php")
        void getSingleArtist(@Query("artistID") int artistId, Callback<Artist> artistCallback);

        @GET("/all_songs_listing.php")
        Songs getAllSongs();

        @GET("/single_songs.php")
        Songs getSingleSongs(@Query("songID") int songId);

        @POST("/facebook_register_user")
        void registerUser(@Body FacebookRegister facebookRegister, Callback<FacebookRegister> facebookRegisterCallback);

        @GET("/header_slider_images.php")
        void getHomePageImage(Callback<HomeImage> homeImageCallback);

        @GET("/get_artist_images.php")
        void getArtistImage(Callback<ArtistImage> artistImageCallback);

        @GET("/get_culb_images.php")
        void getClubImage(Callback<ClubImage> clubImageCallback);

        @GET("/cub_service_listing.php")
        void getAllClubs(Callback<Club> allEventsCallback);

        @GET("/cub_service_listing.php")
        Club getSingleClub(@Query("cubID") int clubId);

        @GET("/artistWithidArray.php")
        void submitArtistId(@Query("artistID") String commaSeparatedIds, Callback<Response> responseCallback);

        @GET("/clubWithidArray.php")
        void submitClubIds(@Query("clubID") String clubIds, Callback<Response> responseCallback);

        @GET("/all_restaurant_listing.php")
        void getAllRestaurants(Callback<Restaurant> responseCallback);

        @GET("/single_restaurant.php")
        void getSingleRestaurants(@Query("restaurantID") String restaurantId, Callback<Restaurant> responseCallback);

        @GET("/cities_listing.php")
        void getAllCities(Callback<City> responseCallback);

        @GET("/search_restaurant_by_city.php")
        void getCityRestaurants(@Query("city_id") String cityId, Callback<City> cityCallback);

        @GET("/follow_post.php")
        void followPost(@Query("post_id") String post_id,@Query("user_id") String user_id,Callback<AllEvents>responseCallback);


    }

    Client() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());

        JacksonConverter jacksonConverter = new JacksonConverter(objectMapper);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.event.embedinfosoft.com/webservice")
                .setConverter(new JacksonConverter())
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.i("clubix", message);
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        requests = restAdapter.create(Requests.class);
    }

    public void getAllEvents(int pageNo, Callback<AllEvents> allEventsCallback) {
        requests.getAllEvents(pageNo, allEventsCallback);
    }


    public void getSingleEvent(String eventId, Callback<AllEvents> allEventsCallback) {
        requests.getSingleEvent(eventId, allEventsCallback);
    }

    public void getTodaysEvents(Callback<AllEvents> allEventsCallback) {
        requests.getTodaysEvents(EventTime.TODAY.name().toLowerCase(), allEventsCallback);
    }

    public void getTomorrowEvents(Callback<AllEvents> allEventsCallback) {
        requests.getTomorrowEvents(EventTime.TOMORROW.name().toLowerCase(), allEventsCallback);
    }

    public void getLaterEvents(Callback<AllEvents> allEventsCallback) {
        requests.getLaterEvents(EventTime.LATER.name().toLowerCase(), allEventsCallback);
    }

    public void getAllArtist(Callback<Artist> artistCallback) {
        requests.getAllArtist(artistCallback);
    }

    public void getSingleArtist(int artistId, Callback<Artist> artistCallback) {
        requests.getSingleArtist(artistId, artistCallback);
    }

    public Songs getAllSongs() {
        return requests.getAllSongs();
    }

    public Songs getSingleSong(int songId) {
        return requests.getSingleSongs(songId);
    }

    public void registerUser(FacebookRegister facebookRegister, Callback<FacebookRegister> facebookRegisterCallback) {
        requests.registerUser(facebookRegister, facebookRegisterCallback);
    }

    public void getHomePageImage(Callback<HomeImage> homeImageCallback) {
        requests.getHomePageImage(homeImageCallback);
    }

    public void getArtistImage(Callback<ArtistImage> artistImageCallback) {
        requests.getArtistImage(artistImageCallback);
    }

    public void getClubImage(Callback<ClubImage> clubImageCallback) {
        requests.getClubImage(clubImageCallback);
    }

    public void getAllClubs(Callback<Club> allEventsCallback) {
        requests.getAllClubs(allEventsCallback);
    }

    public Club getSingleClub(int clubId) {
        return requests.getSingleClub(clubId);
    }

    public void submitArtistIds(String artistIds, Callback<Response> responseCallback) {
        requests.submitArtistId(artistIds, responseCallback);
    }

    public void submitClubIds(String clubIds, Callback<Response> responseCallback) {
        requests.submitClubIds(clubIds, responseCallback);
    }

    public void getAllRestaurants(Callback<Restaurant> responseCallback) {
        requests.getAllRestaurants(responseCallback);
    }

    public void getSingleRestaurants(String id, Callback<Restaurant> responseCallback) {
        requests.getSingleRestaurants(id, responseCallback);
    }

    public void getAllCities(Callback<City> responseCallback){
        requests.getAllCities(responseCallback);
    }

    public void getCityRestaurants(String cityId, Callback<City> cityCallback){
        requests.getCityRestaurants(cityId, cityCallback);
    }

    public void followPost(String postId,String userId,Callback<AllEvents> responseCallback){
        requests.followPost(postId,userId,responseCallback);
    }
}
