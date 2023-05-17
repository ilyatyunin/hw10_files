package ru.betboom.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Library {
    @JsonProperty("книги")
    private ArrayList<Book> books;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
