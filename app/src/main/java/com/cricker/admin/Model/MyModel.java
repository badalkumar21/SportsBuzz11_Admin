package com.cricker.admin.Model;

public class MyModel {

    private String title;
    private String desc;
    private String id;
    private String image1;
    private String image2;
    private String t1;
    private String t2;
    private String team1;
    private String team2;
    private String status_r;
    private String status_g;
    private String url;
    private String target;
    private String ads;


    public MyModel() {

    }


    public MyModel(String title, String desc, String id, String image1, String image2, String t1, String t2, String team1, String team2, String status_r, String status_g, String url, String target, String ads) {
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.image1 = image1;
        this.image2 = image2;
        this.t1 = t1;
        this.t2 = t2;
        this.team1 = team1;
        this.team2 = team2;
        this.status_r = status_r;
        this.status_g = status_g;
        this.url = url;
        this.target = target;
        this.ads = ads;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getStatus_r() {
        return status_r;
    }

    public void setStatus_r(String status_r) {
        this.status_r = status_r;
    }

    public String getStatus_g() {
        return status_g;
    }

    public void setStatus_g(String status_g) {
        this.status_g = status_g;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }
}
