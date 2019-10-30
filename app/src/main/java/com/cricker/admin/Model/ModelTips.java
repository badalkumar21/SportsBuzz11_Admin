package com.cricker.admin.Model;

public class ModelTips {

    private String tips;
    private String ads;


    public ModelTips() {

    }

    public ModelTips(String tips, String ads) {
        this.tips = tips;
        this.ads = ads;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }
}
