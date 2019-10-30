package com.cricker.admin.Model;

public class ModelSquad {

    private String name;
    private String role;
    private String image;
    private float points;
    private boolean isPlaying;
    private String ads;


    public ModelSquad() {

    }


    public ModelSquad(String name, String role, String image, float points, boolean isPlaying) {
        this.name = name;
        this.role = role;
        this.image = image;
        this.points = points;
        this.isPlaying = isPlaying;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }
}
