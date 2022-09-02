package com.example.smartgym.dao;

//TODO  remove?
public class GymEquipment {
    private final long id;
    private final String name;
    private final String description;
    private String gifOrVideo = "??";

    public GymEquipment(long id, String name, String description, String gifOrVideo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gifOrVideo = gifOrVideo;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGifOrVideo() {
        return gifOrVideo;
    }
}
