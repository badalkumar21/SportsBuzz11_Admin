package com.cricker.admin.Model;

public class ModelInfo {

    private long avg_score_1st_inns;
    private long avg_score_2nd_inns;
    private long avg_score_3rd_inns;
    private long avg_score_4th_inns;
    private long capacity;
    private String city;
    private String date;
    private String duration;
    private String match;
    private String series;
    private String time;
    private String venue;
    private String weather;


    public ModelInfo() {

    }

    public ModelInfo(long avg_score_1st_inns, long avg_score_2nd_inns, long avg_score_3rd_inns, long avg_score_4th_inns, long capacity, String city, String date, String duration, String match, String series, String time, String venue, String weather) {
        this.avg_score_1st_inns = avg_score_1st_inns;
        this.avg_score_2nd_inns = avg_score_2nd_inns;
        this.avg_score_3rd_inns = avg_score_3rd_inns;
        this.avg_score_4th_inns = avg_score_4th_inns;
        this.capacity = capacity;
        this.city = city;
        this.date = date;
        this.duration = duration;
        this.match = match;
        this.series = series;
        this.time = time;
        this.venue = venue;
        this.weather = weather;
    }

    public long getAvg_score_1st_inns() {
        return avg_score_1st_inns;
    }

    public void setAvg_score_1st_inns(long avg_score_1st_inns) {
        this.avg_score_1st_inns = avg_score_1st_inns;
    }

    public long getAvg_score_2nd_inns() {
        return avg_score_2nd_inns;
    }

    public void setAvg_score_2nd_inns(long avg_score_2nd_inns) {
        this.avg_score_2nd_inns = avg_score_2nd_inns;
    }

    public long getAvg_score_3rd_inns() {
        return avg_score_3rd_inns;
    }

    public void setAvg_score_3rd_inns(long avg_score_3rd_inns) {
        this.avg_score_3rd_inns = avg_score_3rd_inns;
    }

    public long getAvg_score_4th_inns() {
        return avg_score_4th_inns;
    }

    public void setAvg_score_4th_inns(long avg_score_4th_inns) {
        this.avg_score_4th_inns = avg_score_4th_inns;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}


