package com.example.asm_mob403.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Comment implements Serializable {
    private String comicId;
    private String userId;
    private String content;
    private String date;

//    private Object _id;

//    public Object get_id() {
//        return _id;
//    }
//
//    public void set_id(Object _id) {
//        this._id = _id;
//    }


    public Comment(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment(String comicId, String userId, String content, String date) {
        this.comicId = comicId;
        this.userId = userId;
        this.content = content;
        this.date = date;
    }

    public Comment() {
    }

    public String getComicId() {
        return comicId;
    }

    public void setComicId(String comicId) {
        this.comicId = comicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
