package com.example.asm_mob403.model;

import java.io.Serializable;
import java.util.List;

public class Comic implements Serializable {
    private String title;
    private String description;
    private String author;
    private int year;
    private String coverImage;
    private List<String> images;
    private Object _id;

    public Comic(String title, String description, String author, int year, String coverImage, List<String> images, Object _id) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.year = year;
        this.coverImage = coverImage;
        this.images = images;
        this._id = _id;
    }

    public Comic(String title, String description, String author, int year, String coverImage, List<String> images) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.year = year;
        this.coverImage = coverImage;
        this.images = images;
    }

    public Comic(String title, String description, String author, int year, String coverImage) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.year = year;
        this.coverImage = coverImage;
    }

    public Comic() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }
}
