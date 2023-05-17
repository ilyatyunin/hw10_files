package ru.betboom.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty("название")
    private String title;
    @JsonProperty("автор")
    private String author;
    @JsonProperty("год")
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



}
