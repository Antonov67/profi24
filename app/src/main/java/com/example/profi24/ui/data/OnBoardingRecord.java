package com.example.profi24.ui.data;

public class OnBoardingRecord {
    private int image;
    private String title;
    private String text;

    public OnBoardingRecord(int image, String title, String text) {
        this.image = image;
        this.title = title;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
