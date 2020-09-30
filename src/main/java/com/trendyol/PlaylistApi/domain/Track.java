package com.trendyol.PlaylistApi.domain;


import java.util.UUID;

public class Track {


    private String id= UUID.randomUUID().toString();
    private String name;
    private String length;
    private String artist;

    public Track(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
