package com.example.assignment.DataModel;

public class AppointmentSlotDataModel {
    private String date, eight, ten, twelve, two, four;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEight() {
        return eight;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTwelve() {
        return twelve;
    }

    public void setTwelve(String twelve) {
        this.twelve = twelve;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public AppointmentSlotDataModel(String date, String eight, String ten, String twelve, String two, String four) {
        this.date = date;
        this.eight = eight;
        this.ten = ten;
        this.twelve = twelve;
        this.two = two;
        this.four = four;
    }
}
