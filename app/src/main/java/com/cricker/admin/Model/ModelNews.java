package com.cricker.admin.Model;

public class ModelNews {

    private String news;
    private String ads;


    public ModelNews() {

    }

    public ModelNews(String news, String ads) {
        this.news = news;
        this.ads = ads;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }
}
