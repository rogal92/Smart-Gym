package com.example.smartgym.dao;

public class Equipment {
    private long id;
    private String name;
    private String description;
    private String gifOrVideo = "??";

    public Equipment(long id, String name, String description, String gifOrVideo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gifOrVideo = gifOrVideo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGifOrVideo() {
        return gifOrVideo;
    }

    public void setGifOrVideo(String gifOrVideo) {
        this.gifOrVideo = gifOrVideo;
    }
}
