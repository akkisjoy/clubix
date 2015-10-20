package com.prolificwebworks.theclubix.utils;

import com.prolificwebworks.theclubix.entities.EventData;

import java.util.List;
import java.util.Set;


/**
 * Created by vaibhav on 10/10/15.
 */
public enum MyEnum {


    INSTANCE;

    public List<EventData> today, tomorrow, later;
    public Set<String> club,artist;

    public List<EventData> getToday() {
        return today;
    }

    public void setToday(List<EventData> today) {
        this.today = today;
    }

    public List<EventData> getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(List<EventData> tomorrow) {
        this.tomorrow = tomorrow;
    }

    public List<EventData> getLater() {
        return later;
    }

    public void setLater(List<EventData> later) {
        this.later = later;
    }

    public Set<String> getClub() {
        return club;
    }

    public void setClub(Set<String> club) {
        this.club = club;
    }

    public Set<String> getArtist() {
        return artist;
    }

    public void setArtist(Set<String> artist) {
        this.artist = artist;
    }
}
